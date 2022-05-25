<template>
  <b-card-group deck class="ml-3">
    <b-jumbotron v-if="!recentList || !recentList.length"
      >최근 본 매물이 없습니다.</b-jumbotron
    >
    <b-row class="ml-3">
      <div v-if="showDetail" @click="back()">돌아가기</div>
    </b-row>
    <b-row>
      <interest-item
        v-for="(item, index) in recentList"
        :key="index"
        type="apart"
        :item="item"
        :index="index + 1"
        :showDetail="showDetail"
        @show-detail-list="showDetailList"
      ></interest-item>
    </b-row>
  </b-card-group>
</template>

<script>
import InterestItem from "@/components/interest/item/InterestItem.vue";
export default {
  name: "RecentApt",
  data() {
    return {
      showDetail: false,
      recentList: [],
    };
  },
  components: {
    InterestItem,
  },
  mounted() {
    this.recentList = JSON.parse(localStorage.getItem("recentApt"));
  },
  methods: {
    showDetailList(aptName) {
      let recentList = JSON.parse(localStorage.getItem("recentList"));
      recentList.forEach((element) => {
        if (element[aptName]) {
          this.recentList = element[aptName];
          this.showDetail = true;
        }
      });
    },
    back() {
      this.$router.go();
    },
  },
};
</script>

<style></style>
