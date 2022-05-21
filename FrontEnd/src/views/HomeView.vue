<template>
  <b-container>
    <div class="wrap">
      <div class="background"><img src="@/assets/house.jpg" /></div>
      <div class="text"><p>어떤 집에서 살고 싶으신가요?</p></div>
    </div>
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
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
.text {
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
