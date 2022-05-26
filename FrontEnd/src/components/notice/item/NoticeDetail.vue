<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항 보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listNotice">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          v-if="idCheck"
          variant="outline-info"
          size="sm"
          @click="moveModifyNotice"
          class="mr-2"
          >수정</b-button
        >
        <b-button
          v-if="idCheck"
          variant="outline-danger"
          size="sm"
          @click="deleteNotice"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${notice.notice_no}.
          ${notice.title} </h3><div><h6>${notice.userid}</div><div>${notice.create_time}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import http from "@/util/http-common";

const userStore = "userStore";

export default {
  name: "NoticeDetail",
  data() {
    return {
      notice: {},
      idCheck: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/notice/${this.$route.params.notice_no}`).then(({ data }) => {
      this.notice = data;

      if (this.userInfo != null && this.notice.userid === this.userInfo.id) {
        this.idCheck = true;
      }
    });
  },

  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    listNotice() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeno: this.notice.notice_no },
      });
    },
    deleteNotice() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        http.delete(`/notice/${this.notice.notice_no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/notice");
        });
      }
    },
  },
};
</script>

<style></style>
