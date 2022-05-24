<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항 작성</h3></b-alert>
      </b-col>
    </b-row>
    <notice-write-form type="register" />
  </b-container>
</template>

<script>
import NoticeWriteForm from "@/components/notice/item/NoticeWriteForm.vue";

export default {
  name: "NoticeCreate",
  components: {
    NoticeWriteForm,
  },
};
</script>

<style></style>
