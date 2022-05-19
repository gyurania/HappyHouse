<template>
  <b-container class="bv-example-row mt-3">
    <board-detail />
    <comment-write :board_no="this.board_no"></comment-write>
    <comment-view
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      :isModifyShow="isModifyShow"
    ></comment-view>
  </b-container>
</template>

<script>
import { mapGetters } from "vuex";
import BoardDetail from "@/components/board/item/BoardDetail.vue";
import CommentView from "@/components/board/include/comment/CommentView.vue";
import CommentWrite from "@/components/board/include/comment/CommentWrite.vue";

export default {
  name: "board-view",
  data() {
    return {
      board_no: "",
      isModifyShow: false,
      modifyComment: Object,
    };
  },
  computed: {
    ...mapGetters(["comments"]),
  },
  components: {
    BoardDetail,
    CommentView,
    CommentWrite,
  },
  created() {
    this.board_no = Number(this.$route.params.board_no);
    this.$store.dispatch("getComments", `/comment/${this.board_no}`);
  },
};
</script>

<style></style>
