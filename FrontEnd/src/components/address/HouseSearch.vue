<template>
  <div>
    <h2>아파트거래 조회</h2>
    <b-row>
      <b-col lg="3">
        <i>최소가격 (만원)</i> <b-input v-model="start" />
        <i>최대가격 (만원)</i>
        <b-input v-model="end" />
        <b-btn @click="filterClick">필터적용</b-btn>
        <house-list />
      </b-col>
      <b-col lg="9">
        <si-gun-dong type="houseSearch" />
        <kakao-map></kakao-map>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SiGunDong from "@/components/address/include/SiGunDong.vue";
import KakaoMap from "@/components/address/include/KakaoMap.vue";
import HouseList from "@/components/house/HouseList.vue";
import { mapMutations } from "vuex";

export default {
  name: "HouseSearch",
  data() {
    return {
      start: 0,
      end: Number.MAX_SAFE_INTEGER,
    };
  },
  methods: {
    ...mapMutations("houseStore", ["SET_START_PRICE", "SET_END_PRICE"]),
    filterClick() {
      this.SET_START_PRICE(this.start);
      this.SET_END_PRICE(this.end);
    },
  },
  components: {
    SiGunDong,
    KakaoMap,
    HouseList,
  },
};
</script>

<style></style>
