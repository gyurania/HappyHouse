<template>
  <b-container>
    <div v-if="viewType == 'detail'">
      <h4>{{ apt.아파트 }}</h4>
      <h6>거래금액 : {{ apt.거래금액 | moneyUnit }}원</h6>
      <h6>
        전용면적 : {{ Math.round(apt.전용면적 / 3.3058) }}평,
        {{ apt.전용면적 }}m<sup>2</sup>
      </h6>
      <h6>
        날짜 : <i>{{ apt.년 }}.</i>
        <i>{{ apt.월 }}.</i>
        <i>{{ apt.일 }}</i>
      </h6>
      <h6 v-on:click="dongClick(apt.법정동)" class="link">
        주소 : {{ apt.법정동 }} {{ apt.지번 }}
      </h6>
    </div>
    <div v-else>
      <h4 class="link" v-on:click="aptSelect(apt.아파트)">{{ apt.아파트 }}</h4>
      <h6
        class="link"
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
      price: 0,
    };
  },
  filters: {
    moneyUnit: function (value) {
      let arr = value.split(",");
      let str = "";
      for (let a of arr) {
        str += a;
      }
      let result = "";
      let last = str.slice(-4) == "0000" ? "" : str.slice(-4);
      last = last ? last + "만" : "";
      if (str.length > 4) {
        result = [str.slice(0, -4), "억", last].join("");
      } else {
        result = last;
      }
      return result;
    },
  },
  props: {
    apt: Object,
  },
  created() {
    eventBus.$on("aptMarkerSelect", (aptName) => {
      this.aptSelect(aptName);
    });
  },
  updated() {
    this.addRecentDetail();
  },
  computed: {
    ...mapState(houseStore, ["viewType", "apts"]),
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
      this.addRecentApt(name);
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
    addRecentApt(name) {
      let newList = [];
      if (this.apt.아파트 == name) {
        const aptList = JSON.parse(localStorage.getItem("recentApt"));
        if (aptList) {
          for (let apt of aptList) {
            if (
              !(
                apt.일련번호 === this.apt.일련번호 &&
                apt.년 === this.apt.년 &&
                this.apt.월 === apt.월
              )
            ) {
              newList.push(apt);
            }
          }
        }
        newList.unshift(this.apt);
        if (newList.length > 10) newList.pop();
        localStorage.setItem("recentApt", JSON.stringify(newList));
      }
    },
    addRecentDetail() {
      if (this.viewType == "detail") {
        let str = this.apt.아파트 + this.apt.년 + this.apt.월;
        let detailList = {};
        let recentList = JSON.parse(localStorage.getItem("recentList"));
        if (recentList) {
          for (let item of recentList) {
            if (item[str]) {
              return;
            }
          }
        } else {
          recentList = [];
        }

        detailList[str] = this.apts;
        recentList.push(detailList);

        localStorage.setItem("recentList", JSON.stringify(recentList));
      }
    },
  },
};
</script>

<style scoped>
.link:hover {
  background: silver;
  cursor: pointer;
}
</style>
