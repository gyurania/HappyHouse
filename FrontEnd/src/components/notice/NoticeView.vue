<template>
  <b-container class="bv-example-row mt-3">
    <notice-detail />
  </b-container>
</template>

<script>
import NoticeDetail from "@/components/notice/item/NoticeDetail.vue";

export default {
  name: "NoticeView",
  data() {
    return {
      notice_no: "",
      isModifyShow: false,
    };
  },
  components: {
    NoticeDetail,
  },
  created() {
    this.board_no = Number(this.$route.params.board_no);
  },
};
</script>

<style></style>
