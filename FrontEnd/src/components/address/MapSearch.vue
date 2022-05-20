<template>
  <div>
    <h2>지역 조회</h2>
    <si-gun-dong type="mapSearch" />
    <kakao-map></kakao-map>
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
