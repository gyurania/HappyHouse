<template>
  <b-container v-if="apts && apts.length" class="list">
    <div v-if="viewType == 'detail'" v-on:click="back()">돌아가기</div>
    <div v-else-if="viewRange == 'dong'" v-on:click="showGugun()">전체보기</div>
    <house-list-item
      v-for="(apt, index) in apts"
      :key="index"
      :apt="apt"
      :type="type"
      @typeSelect="typeSelect"
    >
    </house-list-item>
  </b-container>
  <b-container v-else>
    <div v-if="viewRange == 'dong'" v-on:click="showGugun()">전체보기</div>
    <b-row
      ><b-col><b-alert show>거래 내역이 없습니다.</b-alert></b-col></b-row
    >
  </b-container>
</template>
<script>
import { mapState, mapMutations } from "vuex";
import HouseListItem from "@/components/house/HouseListItem.vue";

const houseStore = "houseStore";
export default {
  name: "HouseList",
  data() {
    return {};
  },
  props: {
    type: String,
  },
  components: {
    HouseListItem,
  },
  computed: {
    ...mapState(houseStore, ["apts", "backType", "viewType", "viewRange"]),
  },
  methods: {
    ...mapMutations(houseStore, [
      "SET_APT_LIST_DONG",
      "SET_VIEW_TYPE",
      "SET_APT_LIST",
    ]),
    typeSelect() {
      this.SET_VIEW_TYPE("apt");
    },
    back() {
      this.SET_VIEW_TYPE("apt");
      this.SET_APT_LIST_DONG(this.backType);
    },
    showGugun() {
      this.SET_APT_LIST();
    },
  },
};
</script>

<style>
.list {
  overflow: scroll;
  height: 650px;
  overflow-x: hidden;
}
</style>
