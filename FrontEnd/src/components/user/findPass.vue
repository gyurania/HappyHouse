<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
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
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="이메일:"
              label-for="email"
              style="font-weight: bold"
            >
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>

            <div align="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="find"
                >비밀번호 찾기</b-button
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

export default {
  name: "findPass",
  data() {
    return {
      user: {
        userid: null,
        email: null,
      },
    };
  },
  methods: {
    find() {
      http
        .get(`/user/findpw`, {
          params: {
            userId: this.user.userid,
            email: encodeURI(this.user.email),
          },
        })
        .then(({ data }) => {
          if (data.pass != undefined) {
            alert(`비밀번호는 ${data.pass} 입니다.`);
          } else {
            alert("존재하지 않는 회원정보입니다.");
          }
        });
    },
  },
};
</script>

<style></style>
