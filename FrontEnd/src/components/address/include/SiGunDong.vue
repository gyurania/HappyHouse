<template>
  <b-container>
    <b-col class="text-center">
      <b-row class="justify-content-md-center">
        <b-col v-if="type == 'houseSearch'" md="3">
          <b-form-select v-model="year" :options="years"></b-form-select>
        </b-col>
        <b-col v-if="type == 'houseSearch'" md="3">
          <b-form-select v-model="month" :options="months"></b-form-select>
        </b-col>
        <b-col sm="auto" class="pl-0">
          <b-btn v-if="type == 'houseSearch'" class="mt-0" @click="changeYM"
            >날짜 갱신</b-btn
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col md="3" class="p-1">
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col md="3" class="p-1">
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
        </b-col>
        <b-col v-if="!(type == 'home')" md="3" class="p-1">
          <b-form-select
            v-model="dongCode"
            :options="dongs"
            @change="dongSelect($event)"
          ></b-form-select>
        </b-col>
        <b-col md="auto" v-if="type == 'houseSearch' && userInfo" class="p-1">
          <b-btn class="mt-0" @click="addInterestAddr">관심지역등록</b-btn>
        </b-col>
      </b-row>
    </b-col>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import years from "@/components/address/include/years.js";
import months from "@/components/address/include/months.js";
import { eventBus } from "@/main.js";
import { insertInterestAddr } from "@/util/address.js";
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
    ...mapState("userStore", ["userInfo"]),
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
  mounted() {
    if (this.$route.params.sido_code)
      this.sidoCode = this.$route.params.sido_code;
    if (this.$route.params.gugun_code)
      this.gugunCode = this.$route.params.gugun_code;
    if (this.$route.params.gugun_code) {
      this.dongCode = this.$route.params.dong_code;
      if (this.dongCode) this.changeYM();
    }
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
      if (this.type == "home")
        this.$router.push({
          name: "HouseSearch",
          params: {
            type: "houseSearch",
            sido_code: this.sidoCode,
            gugun_code: this.gugunCode,
            dong_code: null,
          },
        });
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
    addInterestAddr() {
      if (!this.dongCode) {
        alert("시,군,동을 선택해주세요!");
      } else {
        if (this.userInfo)
          insertInterestAddr(
            { userid: this.userInfo.id, dongCode: this.dongCode },
            () => {}
          );
      }
    },
  },
};
</script>

<style></style>
