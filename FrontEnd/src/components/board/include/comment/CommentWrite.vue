<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <textarea
        id="modicomment"
        name="modicomment"
        v-model="modicomment"
        cols="35"
        rows="2"
      ></textarea>
      <!-- <textarea id="comment" name="comment" v-text="modifyComment.comment" ref="comment" cols="35" rows="2">
      </textarea> -->
      <button class="small" @click="updateCommentCancel">취소</button>
      <button class="small" @click="updateComment">수정</button>
    </div>

    <div v-else class="regist_form">
      <textarea
        id="comment"
        name="comment"
        v-model="comment"
        cols="35"
        rows="2"
      ></textarea>
      <b-button @click="registComment">등록</b-button>
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
      userid: "", // 로그인한 사용자 아이디로 바꿈
      comment: "",
      modicomment: this.modifyComment?.comment, // 옵셔널체이닝 : modifyComment가 null 일 경우는 바인딩 안함, modifyComment는 반드시 선언되야 작동.
      // modicomment: this.modifyComment ? this.modifyComment.comment : {}, //props 는 직접 변경 X
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
          //  comment: this.modifyComment.comment, //에러나요 ~~
          //부모뷰에서 자식뷰로 전달한 데이터는 수정하지말고 따로 값을 변수화하여 사용
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
  padding: 20px;
}

textarea {
  width: 90%;
  padding: 10px 20px;
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
