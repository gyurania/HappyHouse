<template>
  <b-container>
    <div v-if="viewType == 'detail'">
      <h4>{{ apt.아파트 }}</h4>
      <h6>거래금액 : {{ apt.거래금액 }} 만원</h6>
      <h6>
        전용면적 : {{ Math.round(apt.전용면적 / 3.3058) }}평,
        {{ apt.전용면적 }}m<sup>2</sup>
      </h6>
      <h6>
        날짜 : <i>{{ apt.년 }}.</i>
        <i>{{ apt.월 }}.</i>
        <i>{{ apt.일 }}</i>
      </h6>
      <h6 v-on:click="dongClick(apt.법정동)">
        주소 : {{ apt.법정동 }} {{ apt.지번 }}
      </h6>
    </div>
    <div v-else>
      <h4 v-on:click="aptSelect(apt.아파트)">{{ apt.아파트 }}</h4>
      <h6
        v-on:click="
          dongClick(apt.법정동, apt.법정동시군구코드, apt.법정동읍면동코드)
        "
      >
        주소 : {{ apt.법정동 }} {{ apt.지번 }}
      </h6>
    </div>
    <div class="line"><hr /></div>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { eventBus } from "@/main.js";

const houseStore = "houseStore";
export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    apt: Object,
  },
  created() {
    eventBus.$on("aptMarkerSelect", (aptName) => {
      this.aptSelect(aptName);
    });
  },
  computed: {
    ...mapState(houseStore, ["viewType"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    ...mapMutations(houseStore, [
      "SET_DETAIL_HOUSE",
      "SET_VIEW_TYPE",
      "SET_BACK_TYPE",
      "SET_APT_LIST_DONG",
    ]),
    selectHouse() {
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    aptSelect(name) {
      this.SET_DETAIL_HOUSE(name.trim());
      this.SET_VIEW_TYPE("detail");
    },
    dongClick(dongName, gugunCode, dongCode10) {
      this.SET_APT_LIST_DONG(dongName);
      this.SET_BACK_TYPE(dongName);
      this.SET_VIEW_TYPE("apt");
      let dongCode = gugunCode + String(dongCode10).substr(0, 3);
      eventBus.$emit("dongClick", dongCode);
    },
  },
};
</script>

<style></style>
