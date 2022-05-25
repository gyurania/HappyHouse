<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내 정보</h3></b-alert>
      </b-col>
    </b-row>

    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group
              label="아이디:"
              label-for="id"
              style="font-weight: bold"
            >
              <b-form-input
                id="id"
                type="text"
                v-model="userInfo.id"
                readonly
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="이름:"
              label-for="name"
              style="font-weight: bold"
            >
              <b-form-input
                id="name"
                type="text"
                required
                v-model="userInfo.name"
                readonly
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="이메일:"
              label-for="email"
              style="font-weight: bold"
            >
              <b-form-input
                id="email"
                type="email"
                required
                v-model="userInfo.email"
                readonly
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="연락처:"
              label-for="phone"
              style="font-weight: bold"
            >
              <b-form-input
                id="phone"
                type="text"
                required
                readonly
                v-model="userInfo.phone"
              ></b-form-input>
            </b-form-group>

            <!-- button -->
            <div align="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="movePage"
                >정보수정</b-button
              >
              <b-button
                type="button"
                variant="danger"
                class="m-1"
                @click="deleteUser"
                >회원탈퇴</b-button
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
// import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "UserMyapage",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {},
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    // onClickLogout() {
    //   // console.log("memberStore : ", us);
    //   this.SET_IS_LOGIN(false);
    //   this.SET_USER_INFO(null);
    //   sessionStorage.removeItem("access-token");
    //   if (this.$route.path != "/") this.$router.push({ name: "Home" });
    // },
    movePage() {
      this.$router.push({ name: "checkpw" });
    },
    deleteUser() {
      this.$router.push({ name: "delete" });
    },
  },
};
</script>

<style scoped>
input {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
