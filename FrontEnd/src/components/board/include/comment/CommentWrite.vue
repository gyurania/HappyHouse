<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <b-row>
        <b-col sm="11">
          <textarea
            id="modicomment"
            name="modicomment"
            v-model="modicomment"
            cols="40"
            rows="2"
          ></textarea>
        </b-col>
        <b-col sm="auto">
          <b-row class="pt-3">
            <b-button size="sm" @click="updateComment">수정</b-button>
          </b-row>
          <b-row class="pt-1">
            <b-button size="sm" @click="updateCommentCancel">취소</b-button>
          </b-row>
        </b-col>
      </b-row>
    </div>

    <div v-else class="regist_form">
      <b-row>
        <b-col sm="11">
          <textarea
            id="comment"
            name="comment"
            v-model="comment"
            cols="35"
            rows="2"
          ></textarea>
        </b-col>
        <b-col sm="auto" class="pt-2 pl-0">
          <b-button size="sm" @click="registComment">등록</b-button>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "comment-write",
  data() {
    return {
      userid: "",
      comment: "",
      modicomment: this.modifyComment?.comment,
    };
  },
  props: {
    board_no: { type: Number },
    modifyComment: { type: Object },
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    registComment() {
      if (this.userInfo == null) {
        alert("로그인 후 이용 가능합니다.");
      } else {
        this.userid = this.userInfo.id;

        if (this.comment != "") {
          http
            .post("/comment/", {
              userid: this.userid,
              comment: this.comment,
              board_no: this.board_no,
            })
            .then(({ data }) => {
              let msg = "등록 처리시 문제가 발생했습니다.";
              if (data === "success") {
                msg = "등록이 완료되었습니다.";
              }
              alert(msg);

              // 화면 새로고침.
              this.$router.go();
            });
        } else {
          // 댓글 쓰지 않고 버튼 누르면 등록 안됨
          alert("댓글을 작성해주세요.");
        }
      }
    },
    updateComment() {
      http
        .put(`/comment`, {
          comment_no: this.modifyComment.comment_no,
          comment: this.modicomment,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);

          // 화면 새로고침..
          this.$router.go();
        });
    },
    updateCommentCancel() {
      this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>

<style scoped>
.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px;
}

textarea {
  width: 100%;
  padding: 10px 10px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

button {
  float: right;
}

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}
</style>
