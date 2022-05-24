<template>
  <b-card-group deck v-if="userInfo" class="ml-3">
    <b-jumbotron v-if="!itemList.length"
      >관심 매물이 없습니다. <br /><br />관심 매물을 등록해주세요</b-jumbotron
    >
    <b-card-group deck>
      <interest-item
        v-for="i in itemList"
        :key="i"
        type="apart"
      ></interest-item>
    </b-card-group>
  </b-card-group>
  <div v-else>관심매물을 사용하려면 로그인을 해주세요</div>
</template>

<script>
import { mapState } from "vuex";
import InterestItem from "@/components/interest/item/InterestItem.vue";

export default {
  name: "InterestArea",
  data() {
    return {
      itemList: 0,
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  components: {
    InterestItem,
  },
};
</script>

<style></style>
