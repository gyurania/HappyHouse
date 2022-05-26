<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>게시글 보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          v-if="idCheck"
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >수정</b-button
        >
        <b-button
          v-if="idCheck"
          variant="outline-danger"
          size="sm"
          @click="deleteArticle"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.board_no}.
          ${article.title} </h3><div><h6>${article.userid}</div><div>${article.create_time}</h6></div>`"
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
  name: "board-detail",
  data() {
    return {
      article: {},
      idCheck: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/board/${this.$route.params.board_no}`).then(({ data }) => {
      this.article = data;

      if (this.userInfo != null && this.article.userid === this.userInfo.id) {
        this.idCheck = true;
      }
    });
  },

  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.board_no },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        http.delete(`/board/${this.article.board_no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/board");
        });
      }
    },
  },
};
</script>

<style></style>
