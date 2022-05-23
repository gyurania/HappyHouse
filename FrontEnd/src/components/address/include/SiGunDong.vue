<template>
  <b-container>
    <b-row class="mt-4 mb-4 text-center">
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="dongSelect($event)"
        ></b-form-select>
      </b-col>
      <b-col v-if="type == 'houseSearch'" class="sm-3">
        <b-form-select v-model="year" :options="years"></b-form-select>
      </b-col>
      <b-col v-if="type == 'houseSearch'" class="sm-3">
        <b-form-select v-model="month" :options="months"></b-form-select>
      </b-col>
      <b-btn v-if="type == 'houseSearch'" class="sm-3 mt-0" @click="changeYM"
        >날짜 갱신</b-btn
      >
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import years from "@/components/address/include/years.js";
import months from "@/components/address/include/months.js";
import { eventBus } from "@/main.js";
const addressStore = "addressStore";
const houseStore = "houseStore";

export default {
  name: "SiGunDong",
  data() {
    return {
      year: "2022",
      month: "01",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      years: years,
      months: months,
    };
  },
  props: {
    type: String,
  },
  computed: {
    ...mapState(addressStore, [
      "sidos",
      "guguns",
      "dongs",
      "sido",
      "gugun",
      "dong",
    ]),
    ...mapState(houseStore, ["backType"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    eventBus.$on("dongClick", (dongCode) => {
      this.dongClick(dongCode);
    });
  },
  methods: {
    ...mapActions(addressStore, ["getSido", "getGugun", "getDong"]),
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapMutations(addressStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_SIDO",
      "SET_GUGUN",
      "SET_DONG",
    ]),
    ...mapMutations(houseStore, [
      "SET_APT_LIST_DONG",
      "SET_BACK_TYPE",
      "SET_VIEW_TYPE",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.gugunSelect();
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    async gugunSelect() {
      if (this.gugunCode) {
        let gugunCode = this.gugunCode;
        let YMD = this.year + this.month;
        await this.getHouseList({ gugunCode, YMD });
        this.SET_VIEW_TYPE("apt");
        this.SET_BACK_TYPE("");
      }
    },
    dongSelect(val) {
      if (this.dongCode) {
        let dongName = "";
        for (let dong of this.dongs) {
          if (dong.value == val) {
            this.SET_APT_LIST_DONG(dong.text);
            dongName = dong.text;
          }
        }
        this.SET_BACK_TYPE(dongName);
      }
      this.SET_DONG(this.dongCode);
      this.SET_VIEW_TYPE("apt");
    },
    dongClick(dongCode) {
      this.dongCode = dongCode;
    },
    async changeYM() {
      if (!this.gugunCode) {
        alert("시,군,동을 선택해주세요!");
      } else {
        this.gugunSelect().then(() => {
          if (this.dongCode) {
            this.dongSelect(this.dongCode);
          }
        });
      }
    },
  },
};
</script>

<style></style>
