<template>
  <div>
    <h3 class="underline-hotpink pt-1">
      <b-icon icon="search"></b-icon> 아파트 거래 조회
    </h3>
    <b-row>
      <b-col lg="3">
        <i v-on:click="filterBtnClick()" class="link">{{ filterBtn }} </i>
        <b-form v-if="filterShow" class="text-right">
          <b-row align-h="center">
            <b-col sm="9" class="m-0 p-0">
              <b-input-group size="sm" prepend="최소가격" append="만원">
                <b-form-input v-model="start"></b-form-input>
              </b-input-group>
              <b-input-group size="sm" prepend="최대가격" append="만원">
                <b-form-input v-model="end"></b-form-input>
              </b-input-group>
            </b-col>
            <b-col sm="auto" class="m-0 p-0">
              <b-btn @click="filterClick" size="md" class="mt-0"
                >필터<br />적용</b-btn
              >
            </b-col>
          </b-row>
        </b-form>
      </b-col>
      <b-col lg="9">
        <si-gun-dong type="houseSearch" />
      </b-col>
    </b-row>
    <b-row>
      <b-col lg="3">
        <house-list />
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
import { mapMutations } from "vuex";

export default {
  name: "HouseSearch",
  data() {
    return {
      start: 0,
      end: 100000000000,
      filterShow: false,
    };
  },
  computed: {
    filterBtn() {
      return this.filterShow ? "가격필터 숨기기" : "가격필터 보기";
    },
  },
  methods: {
    ...mapMutations("houseStore", ["SET_FILTER_PRICE"]),
    filterClick() {
      this.SET_FILTER_PRICE({ startPrice: this.start, endPrice: this.end });
    },
    filterBtnClick() {
      this.filterShow = !this.filterShow;
    },
  },
  components: {
    SiGunDong,
    KakaoMap,
    HouseList,
  },
};
</script>

<style scoped>
.link:hover {
  cursor: pointer;
}
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
