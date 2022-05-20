<template>
  <div>
    <h2>아파트거래 조회</h2>
    <si-gun-dong type="houseSearch" @typeSelect="typeSelect" />
    <b-row>
      <b-col lg="3">
        <house-list :type="this.type" @typeSelect="typeSelect" />
      </b-col>
      <b-col lg="9">
        <kakao-map></kakao-map>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import SiGunDong from "@/components/address/include/SiGunDong.vue";
import KakaoMap from "@/components/address/include/KakaoMap.vue";
import HouseList from "@/components/house/HouseList.vue";

export default {
  name: "HouseSearch",
  data() {
    return {
      type: "",
    };
  },
  components: {
    SiGunDong,
    KakaoMap,
    HouseList,
  },
  methods: {
    typeSelect(type) {
      this.type = type;
    },
  },
};
</script>

<style></style>
