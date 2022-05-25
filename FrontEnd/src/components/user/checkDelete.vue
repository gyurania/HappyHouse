<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원 탈퇴</h3></b-alert>
      </b-col>
    </b-row>

    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group
              label="비밀번호:"
              label-for="pass"
              style="font-weight: bold"
            >
              <b-form-input
                id="pass"
                v-model="pwcheck"
                type="password"
                @keyup.enter="checkPw"
              ></b-form-input>
              <!-- 새로고침 방지 -->
              <b-form-input type="text" style="display: none"></b-form-input>
            </b-form-group>

            <!-- button -->
            <div align="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="checkPw"
                >확인</b-button
              >
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "checkDelete",
  data() {
    return {
      user: {
        id: "",
        pass: "",
        name: "",
        email: "",
        phone: "",
      },
      pwcheck: "",
    };
  },
  created() {
    http.get(`/user/mypage/${this.userInfo.id}`).then(({ data }) => {
      this.user = data;
    });
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    checkPw() {
      if (this.user.pass === this.pwcheck) {
        if (confirm("정말 탈퇴하시겠습니까?")) {
          http.delete(`/user/mypage/${this.user.id}`).then(({ data }) => {
            let msg = "탈퇴 처리 중 문제가 발생했습니다.";
            if (data === "success") {
              msg = "탈퇴 처리 되었습니다.";
              this.SET_IS_LOGIN(false);
              this.SET_USER_INFO(null);
              sessionStorage.removeItem("access-token");
            }
            alert(msg);
          });
          this.$router.push({ name: "Home" });
        } else {
          this.$router.push({ name: "mypage" });
        }
      } else {
        alert("비밀번호가 일치하지 않습니다.");
      }
    },
  },
};
</script>

<style scoped>
input {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
