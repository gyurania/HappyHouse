<template>
  <div>
    <div class="wrap">
      <div class="background"><img src="@/assets/house.jpg" /></div>
      <div class="content">
        <p>어떤 집에서 살고 싶으신가요?</p>
        <si-gun-dong type="home" />
      </div>
    </div>
  </div>
</template>

<script>
import SiGunDong from "@/components/address/include/SiGunDong.vue";
export default {
  name: "HomeView",
  components: {
    SiGunDong,
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

.wrap {
  width: 100%;
  position: relative;
}
.wrap img {
  width: 100%;
  vertical-align: middle;
}
.content {
  position: absolute;
  top: 60%;
  left: 50;
  width: 100%;
  /* transform: translate(-50%, 50%); */
  font-size: 20px;
  font-family: "Jeju Gothic";
  text-align: center;
  color: white;
}
</style>
