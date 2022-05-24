<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>

    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group
              label="아이디:"
              label-for="userid"
              style="font-weight: bold"
            >
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="비밀번호:"
              label-for="userpwd"
              style="font-weight: bold"
            >
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>

            <div align="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="confirm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1"
                @click="movePage"
                >회원가입</b-button
              >

              <!-- <div class="mt-3 mb-4 text-center">
                <span class="fw-normal">or</span>
              </div> -->
              <div class="d-flex justify-content-center my-4">
                <img
                  @click="KakaoLogin"
                  class="btn"
                  src="@/assets/kakao_login_medium_narrow.png"
                />
              </div>
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/util/http-common";

const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  mounted() {
    this.SET_IS_LOGIN_ERROR(false);
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    ...mapMutations(userStore, ["SET_IS_LOGIN_ERROR"]),
    async confirm() {
      // console.log("login enter");
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
    movePage() {
      this.$router.push({ name: "regist" });
    },

    KakaoLogin() {
      console.log(window.Kakao);
      window.Kakao.Auth.login({
        scope: "account_email, profile, gender",
        success: this.GetMe,
        fail: function (error) {
          console.log(error);
        },
      });
    },
    GetMe(authObj) {
      console.log(authObj);
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;

          http
            .post("/login", {
              userName: kakao_account.profile.nickname,
              userEmail: kakao_account.email,
              userPassword: "kakao",
            })
            .then(({ data }) => {
              console.log("LoginVue: data : ");
              console.log(data);

              // login 성공 전달
              this.$store.commit("SET_LOGIN", {
                isLogin: true,
                userName: data.userName,
                userEmail: data.userEmail,
                userPassword: data.userPassword,
                userPhone: data.userPhone,
              });
              // this.$alertify.success(
              //   this.$store.state.userInfo.userName + "님 환영합니다"
              // );

              this.$router.push("/Home");
            })
            .catch((error) => {
              console.log("KaKaoLoginVue: error : ");
              console.log(error);
              if (error.response.status == "404") {
                // => 카카오 회원가입

                http
                  .post("/user", {
                    userName: kakao_account.profile.nickname,
                    userEmail: kakao_account.email,
                    userProfileImageUrl:
                      kakao_account.profile.profile_image_url,
                    userRank: this.userRank,
                    userPassword: "kakao",
                  })
                  .then(({ data }) => {
                    console.log("JoinVue - data : ");
                    console.log(data);

                    // join 성공 전달
                    this.$store.commit("SET_LOGIN", {
                      isLogin: true,
                      userName: data.userName,
                      userEmail: data.userEmail,
                      userMessage: data.userMessage,
                      userPassword: data.userPassword,
                      userPhone: data.userPhone,
                    });

                    // this.$alertify.success(
                    //   this.$store.state.userInfo.userName + "님 환영합니다"
                    // );
                    // home 로 이동
                    this.$router.push("/Home");
                  })
                  .catch((error) => {
                    console.log("LoginVue: error : ");
                    console.log(error);
                    if (error.response.status == "404") {
                      this.$alertify.error("카카오 로그인에 실패했습니다.");
                    } else {
                      this.$alertify.error(
                        "Opps!! 서버에 문제가 발생했습니다."
                      );
                    }
                  });
              } else {
                this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
              }
            });
        },

        fail: (error) => {
          this.$router.push("/");
          console.log(error);
        },
      });
    },
  },
};
</script>

<style></style>
