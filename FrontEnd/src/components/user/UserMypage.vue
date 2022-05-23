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
                placeholder="아이디"
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
                required
                placeholder="이름"
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
                placeholder="이메일"
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
                type="phone"
                required
                readonly
                placeholder="연락처"
              ></b-form-input>
            </b-form-group>

            <!-- button -->
            <div align="center">
              <b-button type="button" variant="primary" class="m-1"
                >정보수정</b-button
              >
              <b-button type="button" variant="danger" class="m-1"
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

export default {
  name: "UserMapage",
  data() {},
};
</script>

<style></style>
