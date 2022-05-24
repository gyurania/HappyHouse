<template>
  <b-card
    :header="index + ''"
    class="mb-1"
    style="max-width: 20em"
    @click="cardClick()"
  >
    <b-card-text v-if="type == 'area'">
      {{ item.sidoName }} <br />{{ item.gugunName }}<br />
      {{ item.dongName }}
    </b-card-text>
    <b-card-text v-if="type == 'apart'">아파트 이름</b-card-text>
  </b-card>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: {
    type: String,
    item: Object,
    index: Number,
  },
  methods: {
    ...mapActions("addressStore", [
      "getGugun",
      "getDong",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    ...mapActions("houseStore", ["getHouseList"]),
    async cardClick() {
      const sidoCode = this.item.dongCode.substr(0, 2);
      this.getGugun(sidoCode);
      const gugunCode = this.item.dongCode.substr(0, 5);
      this.getDong(gugunCode);
      const dongCode = this.item.dongCode.substr(0, 8);
      this.$router.push({
        name: "HouseSearch",
        params: {
          type: "houseSearch",
          sido_code: sidoCode,
          gugun_code: gugunCode,
          dong_code: dongCode,
        },
      });
    },
  },
};
</script>

<style scoped></style>
