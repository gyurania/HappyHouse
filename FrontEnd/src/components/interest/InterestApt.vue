<template>
  <b-container v-if="userInfo">
    <b-card-group deck>
      <interest-item v-for="i in arr" :key="i" type="apart"></interest-item>
    </b-card-group>
  </b-container>
  <div v-else>관심매물을 사용하려면 로그인을 해주세요</div>
</template>

<script>
import { mapState } from "vuex";
import InterestItem from "@/components/interest/item/InterestItem.vue";

export default {
  name: "InterestArea",
  data() {
    return {
      arr: 5,
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  components: {
    InterestItem,
  },
};
</script>

<style></style>
