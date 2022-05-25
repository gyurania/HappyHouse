<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 확인</h3></b-alert>
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
              ></b-form-input>
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
  name: "checkPass",
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
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {
    http.get(`/user/mypage/${this.userInfo.id}`).then(({ data }) => {
      this.user = data;
    });
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    checkPw() {
      // console.log(this.user.pass);
      // console.log(this.pwcheck);
      if (this.user.pass === this.pwcheck) {
        this.$router.push({ name: "update" });
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
