<template>
  <b-card class="mb-1 p-0" style="min-width: 20em; max-width: 20em">
    <b-card-header class="pt-2"
      >{{ index }}
      <b-btn
        variant="danger"
        size="sm"
        class="float-right mt-0 pt-0 pb-0 pl-1 pr-1"
        @click="deleteClick()"
        >X</b-btn
      ></b-card-header
    >
    <b-card-text class="p-4" v-if="type == 'area'" @click="cardClick()">
      {{ item.sidoName }} {{ item.gugunName }}
      {{ item.dongName }}
    </b-card-text>
    <b-card-text v-if="type == 'apart'"
      ><h4>{{ item.아파트 }}</h4>
      <h6>
        날짜 : <i>{{ item.년 }}.</i>
        <i>{{ item.월 }}.</i>
      </h6>
      <h6>주소 : {{ item.법정동 }} {{ item.지번 }}</h6></b-card-text
    >
  </b-card>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { deleteInterest } from "@/util/address.js";
export default {
  props: {
    type: String,
    item: Object,
    index: Number,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
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
    deleteClick() {
      const params = [this.userInfo.id, this.item.dongCode];
      if (this.type == "area") {
        deleteInterest(params, ({ data }) => {
          if (data == "삭제성공") console.log(data);
          this.$router.go();
        });
      }
      if (this.type == "apart" && this.$route.path == "/interest/recent") {
        let newList = [];
        const aptList = JSON.parse(localStorage.getItem("recentApt"));
        if (aptList) {
          for (let apt of aptList) {
            if (!(apt.일련번호 === this.item.일련번호)) {
              newList.push(apt);
            }
          }
        }
        localStorage.setItem("recentApt", JSON.stringify(newList));
        this.$router.go();
      }
    },
  },
};
</script>

<style scoped>
.card-body {
  padding: 0;
}
</style>
