<template>
  <b-card class="mb-1 p-0" style="min-width: 20em; max-width: 20em">
    <b-card-header class="pt-2"
      >{{ index }}
      <b-btn
        variant="danger"
        size="sm"
        class="float-right mt-0 pt-0 pb-0 pl-1 pr-1"
        @click="deleteClick()"
        v-if="!showDetail"
        >X</b-btn
      ></b-card-header
    >
    <b-card-text class="p-4 link" v-if="type == 'area'" @click="cardClick()">
      {{ item.sidoName }} {{ item.gugunName }}
      {{ item.dongName }}
    </b-card-text>
    <b-card-text v-if="type == 'apart'"
      ><h4 v-if="!showDetail" @click="detailClick()" class="link">
        {{ item.아파트 }}
      </h4>
      <h4 v-if="showDetail">{{ item.아파트 }}</h4>
      <h6 v-if="showDetail">거래금액 : {{ item.거래금액 | moneyUnit }}원</h6>
      <h6 v-if="showDetail">
        전용면적 : {{ Math.round(item.전용면적 / 3.3058) }}평,
        {{ item.전용면적 }}m<sup>2</sup>
      </h6>
      <h6>
        날짜 : <i>{{ item.년 }}.</i> <i>{{ item.월 }}.</i>
        <i v-if="showDetail">{{ item.일 }}</i>
      </h6>
      <h6>주소 : {{ item.법정동 }} {{ item.지번 }}</h6></b-card-text
    >
  </b-card>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { deleteInterest } from "@/util/address.js";
export default {
  props: {
    type: String,
    item: Object,
    index: Number,
    showDetail: Boolean,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  filters: {
    moneyUnit: function (value) {
      let arr = value.split(",");
      let str = "";
      for (let a of arr) {
        str += a;
      }
      str = str.replace(/ /g, "");
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
  methods: {
    ...mapActions("addressStore", [
      "getGugun",
      "getDong",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    ...mapActions("houseStore", ["getHouseList"]),
    async cardClick() {
      const sidoCode = this.item.dongCode.substr(0, 2);
      this.getGugun(sidoCode);
      const gugunCode = this.item.dongCode.substr(0, 5);
      this.getDong(gugunCode);
      const dongCode = this.item.dongCode.substr(0, 8);
      this.$router.push({
        name: "HouseSearch",
        params: {
          type: "houseSearch",
          sido_code: sidoCode,
          gugun_code: gugunCode,
          dong_code: dongCode,
        },
      });
    },
    deleteArea() {
      let params;
      if (this.userInfo) params = [this.userInfo.id, this.item.dongCode];
      deleteInterest(params, ({ data }) => {
        if (data != "삭제성공") console.log(data);
        this.$router.go();
      });
    },
    deleteRecent() {
      let newList = [];
      const aptList = JSON.parse(localStorage.getItem("recentApt"));
      if (aptList) {
        for (let apt of aptList) {
          if (!(apt.일련번호 === this.item.일련번호)) {
            newList.push(apt);
          }
        }
      }
      localStorage.setItem("recentApt", JSON.stringify(newList));
      let str = this.item.아파트 + this.item.년 + this.item.월;
      let recentList = JSON.parse(localStorage.getItem("recentList"));
      for (let i in recentList) {
        if (recentList[i][str]) {
          console.log(recentList[i][str]);
          recentList.splice(i, 1);
          localStorage.setItem("recentList", JSON.stringify(recentList));
          break;
        }
      }
      this.$router.go();
    },
    deleteClick() {
      if (this.type == "area") this.deleteArea();
      if (this.type == "apart" && this.$route.path == "/interest/recent") {
        this.deleteRecent();
      }
    },
    detailClick() {
      let str = this.item.아파트 + this.item.년 + this.item.월;
      this.$emit("show-detail-list", str);
    },
  },
};
</script>

<style scoped>
.card-body {
  padding: 0;
}
.link:hover {
  background: silver;
  cursor: pointer;
}
</style>
