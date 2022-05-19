<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글수정</h3></b-alert>
      </b-col>
    </b-row>
    <board-write-form type="modify" />
  </b-container>
</template>

<script>
import BoardWriteForm from "@/components/board/item/BoardWriteForm.vue";

export default {
  name: "BoardModify",
  components: {
    BoardWriteForm,
  },
};
</script>

<style></style>
