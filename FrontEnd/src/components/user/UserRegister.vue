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
            <b-form-group
              label="아이디:"
              label-for="id"
              style="font-weight: bold"
            >
              <b-form-input
                id="id"
                type="text"
                v-model="user.id"
                :state="idState"
                required
                placeholder="아이디 입력"
                @blur="idCheck"
              ></b-form-input>

              <b-form-invalid-feedback
                id="id-feedback"
                style="
                  font-family: Avenir, Helvetica, Arial, sans-serif;
                  font-weight: normal;
                "
                >이미 사용 중인 아이디입니다.
              </b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              label="비밀번호:"
              label-for="pass"
              style="font-weight: bold"
            >
              <b-form-input
                type="password"
                id="pass"
                v-model="user.pass"
                :state="passState"
                required
                placeholder="비밀번호 입력"
                @blur="passError"
              ></b-form-input>
              <b-form-text
                id="pass"
                style="font-family: Avenir, Helvetica, Arial, sans-serif"
                >비밀번호는 6자 이상, 하나의 문자 및 하나의 숫자를 포함해야
                합니다.</b-form-text
              ></b-form-group
            >

            <b-form-group
              label="비밀번호 확인:"
              label-for="checkpwd"
              style="font-weight: bold"
            >
              <b-form-input
                type="password"
                id="checkpwd"
                v-model="user.checkpwd"
                :state="passCoin"
                required
                placeholder="비밀번호 재입력"
                @blur="checkPw"
              ></b-form-input>
              <b-form-invalid-feedback
                id="checkpwd-feedback"
                style="
                  font-family: Avenir, Helvetica, Arial, sans-serif;
                  font-weight: normal;
                "
                >비밀번호가 일치하지 않습니다.
              </b-form-invalid-feedback></b-form-group
            >

            <b-form-group
              label="이름:"
              label-for="name"
              style="font-weight: bold"
            >
              <b-form-input
                id="name"
                type="text"
                v-model="user.name"
                :state="nameState"
                required
                placeholder="이름 입력"
                @blur="nameCheck"
              ></b-form-input>
              <b-form-invalid-feedback
                id="name-feedback"
                style="
                  font-family: Avenir, Helvetica, Arial, sans-serif;
                  font-weight: normal;
                "
                >이름을 입력해주세요.
              </b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              label="이메일:"
              label-for="email"
              style="font-weight: bold"
            >
              <b-form-input
                id="email"
                type="email"
                v-model="user.email"
                required
                placeholder="이메일 입력"
                @blur="emailCheck"
                :state="emailState"
              ></b-form-input>
              <b-form-invalid-feedback
                id="email-feedback"
                style="
                  font-family: Avenir, Helvetica, Arial, sans-serif;
                  font-weight: normal;
                "
                >이메일을 입력해주세요.
              </b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              label="연락처:"
              label-for="phone"
              style="font-weight: bold"
            >
              <b-form-input
                id="phone"
                type="text"
                v-model="user.phone"
                required
                placeholder="연락처 입력"
                @blur="phoneCheck"
                :state="phoneState"
              ></b-form-input>
              <b-form-invalid-feedback
                id="phone-feedback"
                style="
                  font-family: Avenir, Helvetica, Arial, sans-serif;
                  font-weight: normal;
                "
                >연락처를 입력해주세요.
              </b-form-invalid-feedback>
            </b-form-group>

            <div align="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="onSubmit"
                >회원가입</b-button
              >

              <b-button
                type="button"
                variant="danger"
                class="m-1"
                @click="onReset"
                >초기화</b-button
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
  name: "UserRegister",
  data() {
    return {
      isLoginError: false,
      user: {
        id: "",
        pass: "",
        checkpwd: "",
        name: "",
        email: "",
        phone: "",
      },
      users: [],
      passErr: null,
      useableId: null,
      passCoin: null,
      checkName: null,
      checkEmail: null,
      checkPhone: null,
    };
  },
  computed: {
    idState() {
      return this.useableId;
    },
    passState() {
      return this.passErr;
    },
    nameState() {
      return this.checkName;
    },
    emailState() {
      return this.checkEmail;
    },
    phoneState() {
      return this.checkPhone;
    },
  },
  methods: {
    nameCheck() {
      if (this.user.name == "") {
        this.checkName = false;
      } else this.checkName = true;
    },
    emailCheck() {
      if (this.user.email == "") {
        this.checkEmail = false;
      } else this.checkEmail = true;
    },
    phoneCheck() {
      if (this.user.phone == "") {
        this.checkPhone = false;
      } else this.checkPhone = true;
    },
    onSubmit() {
      // event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.id && ((msg = "아이디를 입력해주세요."), (err = false));
      err &&
        !this.user.pass &&
        ((msg = "비밀번호를 입력해주세요."), (err = false));
      err && !this.user.name && ((msg = "이름을 입력해주세요."), (err = false));
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요."), (err = false));
      err &&
        !this.user.phone &&
        ((msg = "연락처를 입력해주세요."), (err = false));

      if (!err) alert(msg);
      else {
        this.regist();
      }
    },
    // 비밀번호 확인
    checkPw() {
      if (this.passErr) {
        if (this.user.pass === this.user.checkpwd) {
          this.passCoin = true;
        } else {
          this.passCoin = false; // 비밀번호 일치하지 않음
        }
      }
    },
    passError() {
      var reg = /^[A-Za-z0-9]{6,}$/;
      if (this.user.pass != "") {
        if (!reg.test(this.user.pass)) {
          this.passErr = false;
        } else {
          this.passErr = true;
        }
      }
    },
    idCheck() {
      if (this.user.id) {
        http.get(`/user/idcheck/${this.user.id}`).then(({ data }) => {
          this.useableId = false; // 사용중인 아이디
          if (data === "success") {
            this.useableId = true; // 사용 가능한 아이디
          }
        });
      } else {
        this.useableId = false;
      }
    },
    onReset() {
      // event.preventDefault();
      this.user.id = "";
      this.user.pass = "";
      this.user.checkpwd = "";
      this.user.name = "";
      this.user.email = "";
      this.user.phone = "";

      this.passErr = null;
      this.useableId = null;
      this.passCoin = null;
      this.checkName = null;
      this.checkEmail = null;
      this.checkPhone = null;
    },
    regist() {
      if (this.duplicateId == true) {
        alert("아이디 중복확인을 해주세요.");
      } else if (!this.passErr) {
        this.passErr = false;
      } else {
        http
          .post(`/user/regist`, {
            id: this.user.id,
            pass: this.user.pass,
            name: this.user.name,
            email: this.user.email,
            phone: this.user.phone,
          })
          .then(({ data }) => {
            let msg = "회원가입 처리 중 문제가 발생했습니다.";
            if (data === "success") {
              msg = "회원가입이 완료되었습니다.";
              this.movePage();
            }
            alert(msg);
          });
      }
    },

    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
input {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
