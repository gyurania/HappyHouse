<template>
  <b-card-group deck class="ml-3">
    <b-jumbotron v-if="!recentList || !recentList.length"
      >최근 본 매물이 없습니다.</b-jumbotron
    >
    <interest-item
      v-for="(item, index) in recentList"
      :key="index"
      type="apart"
      :item="item"
      :index="index + 1"
    ></interest-item>
  </b-card-group>
</template>

<script>
import InterestItem from "@/components/interest/item/InterestItem.vue";
export default {
  name: "RecentApt",
  data() {
    return {
      recentList: [],
    };
  },
  components: {
    InterestItem,
  },
  mounted() {
    this.recentList = JSON.parse(localStorage.getItem("recentApt"));
  },
};
</script>

<style></style>
