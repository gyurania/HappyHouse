<template>
  <b-container>
    <div class="background">당신은 어떤 집에서 살고 싶으신가요?</div>
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
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.background {
  font-family: Georgia, "맑은 고딕", serif;
  font-size: xx-large;
  font-weight: bolder;
  color: aliceblue;
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/house.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>
