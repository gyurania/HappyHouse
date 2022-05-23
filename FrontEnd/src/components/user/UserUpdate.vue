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
              <b-form-input id="id" v-model="user.id" readonly></b-form-input>
            </b-form-group>

            <b-form-group
              label="이름:"
              label-for="name"
              style="font-weight: bold"
            >
              <b-form-input
                id="name"
                required
                v-model="user.name"
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
                v-model="user.email"
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="연락처:"
              label-for="phone"
              style="font-weight: bold"
            >
              <b-form-input
                id="phone"
                type="phone"
                required
                v-model="user.phone"
              ></b-form-input>
            </b-form-group>

            <!-- button -->
            <div align="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="updateUser"
                >수정</b-button
              >
              <b-button
                type="button"
                variant="danger"
                class="m-1"
                @click="moveMypage"
                >취소</b-button
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
  name: "UserUpdate",
  data() {
    return {
      user: {
        id: "",
        pass: "",
        name: "",
        email: "",
        phone: "",
      },
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
  // state:{}
  // }
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    updateUser() {
      http
        .put(`/user/mypage/${this.user.id}`, {
          id: this.user.id,
          pass: this.user.pass,
          name: this.user.name,
          email: this.user.email,
          phone: this.user.phone,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";

            this.$router.push({ name: "mypage" });
          }
          alert(msg);
        });
    },
    moveMypage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style></style>
