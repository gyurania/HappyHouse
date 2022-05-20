<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>

    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력"
              ></b-form-input
            ></b-form-group>

            <b-form-group label="비밀번호 확인:" label-for="checkpwd">
              <b-form-input
                type="password"
                id="checkpwd"
                v-model="user.checkpwd"
                required
                placeholder="비밀번호 재입력"
              ></b-form-input
            ></b-form-group>

            <b-form-group label="이름:" label-for="name">
              <b-form-input
                id="name"
                v-model="user.name"
                required
                placeholder="이름 입력"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="이메일:" label-for="email">
              <b-form-input
                id="email"
                type="email"
                v-model="user.email"
                required
                placeholder="이메일 입력"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="연락처:" label-for="phone">
              <b-form-input
                id="phone"
                type="phone"
                v-model="user.phone"
                required
                placeholder="연락처 입력"
              ></b-form-input>
            </b-form-group>

            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >

            <b-button type="button" variant="danger" class="m-1"
              >초기화</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "UserRegister",
  data() {
    return {
      isLoginError: false,
      user: {
        userid: "",
        userpwd: "",
      },
      users: [],
    };
  },
  created() {
    http.get(`/user/idcheck`).then(({ data }) => {
      this.users = data;
    });
  },
  methods: {
    // 아이디 중복 체크
    checkId() {
      // console.log(user);
    },
    // 비밀번호 확인
    checkPw() {
      this.checkPass = this.userpwd == this.checkpwd ? true : false;
    },
    // 빈 칸 없는지 확인

    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>
