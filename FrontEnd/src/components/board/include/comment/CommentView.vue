<template>
  <div v-if="isModifyShow">
    <comment-write
      v-if="isModifyShow && this.comment != null"
      :modifyComment="this.comment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
  </div>
  <div v-else class="comment">
    <div class="comment-head">
      {{ comment.userid }} ({{ comment.create_time }})
    </div>
    <div class="comment-content" v-html="enterToBr(comment.comment)"></div>
    <div v-if="isShow" class="cbtn">
      <label @click="modifyCommentView">수정</label> |
      <label @click="deleteComment">삭제</label>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import CommentWrite from "@/components/board/include/comment/CommentWrite.vue";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "comment-view",
  data() {
    return {
      isModifyShow: false,
    };
  },
  components: {
    CommentWrite,
  },
  props: {
    comment: Object,
    isShow: Boolean,
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    modifyCommentView() {
      this.onModifyComment({
        comment_no: this.comment.comment_no,
        comment: this.comment.comment,
        isbn: this.comment.isbn,
      });
    },
    deleteComment() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        http.delete(`/comment/${this.comment.comment_no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          // 화면 새로고침.
          this.$router.go();
        });
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
};
</script>

<style>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px 20px;
  margin: 10px;
}
.comment-head {
  font-weight: bold;
  margin-bottom: 5px;
}
.comment-content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>
