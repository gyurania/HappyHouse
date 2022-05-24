<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="userInfo.id"
            type="text"
            required
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="notice.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">취소</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeWriteForm",
  data() {
    return {
      notice: {
        notice_no: 0,
        userid: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/notice/${this.$route.params.notice_no}`).then(({ data }) => {
        this.notice = data;
      });
      this.isUserid = true;
    }
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      !this.notice.title && ((msg = "제목 입력해주세요"), (err = false));
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.noticeno = 0;
      this.notice.subject = "";
      this.notice.content = "";
      if (this.type === "modify") {
        this.$router.push({
          name: "noticeView",
          params: { notice_no: this.notice.notice_no },
        });
      } else {
        this.$router.push({ name: "NoticeList" });
      }
    },
    registNotice() {
      http
        .post(`/notice`, {
          userid: this.userInfo.id,
          title: this.notice.title,
          content: this.notice.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyNotice() {
      http
        .put(`/notice/${this.notice.notice_no}`, {
          notice_no: this.notice.notice_no,
          userid: this.notice.userid,
          title: this.notice.title,
          content: this.notice.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({
            name: "noticeView",
            params: { notice_no: this.notice.notice_no },
          });
        });
    },
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style></style>
