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
            <b-form-group
              label="아이디:"
              label-for="userid"
              style="font-weight: bold"
            >
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력"
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
                placeholder="비밀번호 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>

            <b-alert
              show
              variant="danger"
              v-if="isLoginError"
              style="
                font-family: Avenir, Helvetica, Arial, sans-serif;
                font-weight: normal;
              "
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >

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

              <div class="text-center mt-3">
                <router-link to="/user/findpw">
                  비밀번호를 잊어버리셨나요?</router-link
                >
              </div>
              <!-- <div class="mt-3 mb-4 text-center">
                <span class="fw-normal">or</span>
              </div> -->
              <!-- <div class="d-flex justify-content-center my-4">
                <img
                  @click="KakaoLogin"
                  class="btn"
                  src="@/assets/kakao_login_medium_narrow.png"
                />
              </div> -->
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
  },
};
</script>

<style scoped>
input {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
