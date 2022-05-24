<template>
  <div>
    <h2>아파트거래 조회</h2>
    <b-row>
      <b-col lg="3">
        <i v-on:click="filterBtnClick()">{{ filterBtn }} </i>
        <b-form v-if="filterShow" class="text-right">
          <b-container>
            <b-input-group size="sm" prepend="최소가격" append="만원">
              <b-form-input v-model="start"></b-form-input>
            </b-input-group>
            <b-input-group size="sm" prepend="최대가격" append="만원">
              <b-form-input v-model="end"></b-form-input>
            </b-input-group>
            <b-btn @click="filterClick" size="sm" class="mt-0">필터적용</b-btn>
          </b-container>
        </b-form>
        <house-list />
      </b-col>
      <b-col lg="9">
        <si-gun-dong type="houseSearch" />
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
      end: Number.MAX_SAFE_INTEGER,
      filterShow: false,
    };
  },
  computed: {
    filterBtn() {
      return this.filterShow ? "가격필터 숨기기" : "가격필터 보기";
    },
  },
  methods: {
    ...mapMutations("houseStore", ["SET_START_PRICE", "SET_END_PRICE"]),
    filterClick() {
      this.SET_START_PRICE(this.start);
      this.SET_END_PRICE(this.end);
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

<style></style>
