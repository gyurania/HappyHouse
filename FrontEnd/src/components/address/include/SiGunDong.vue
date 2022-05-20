<template>
  <b-container>
    <b-row class="mt-4 mb-4 text-center">
      <b-col v-if="type == 'houseSearch'" class="sm-3">
        <b-form-select v-model="year" :options="years"></b-form-select>
      </b-col>
      <b-col v-if="type == 'houseSearch'" class="sm-3">
        <b-form-select v-model="month" :options="months"></b-form-select>
      </b-col>
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
        <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import years from "@/components/address/include/years.js";
import months from "@/components/address/include/months.js";
const addressStore = "addressStore";

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
    ...mapState(addressStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(addressStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(addressStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
  },
};
</script>

<style></style>
