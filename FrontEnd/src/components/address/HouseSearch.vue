<template>
  <div>
    <h2>아파트거래 조회</h2>
    <si-gun-dong type="houseSearch" />
    <b-row>
      <b-col lg="3">
        <h2>아파트 정보</h2>
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

export default {
  name: "HouseSearch",
  components: {
    SiGunDong,
    KakaoMap,
  },
};
</script>

<style></style>
