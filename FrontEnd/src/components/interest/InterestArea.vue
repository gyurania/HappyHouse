<template>
  <b-card-group deck v-if="userInfo" class="ml-3">
    <b-jumbotron v-if="!itemList.length"
      >관심지역이 없습니다. <br /><br />관심지역을 등록해주세요</b-jumbotron
    >
    <interest-item
      v-for="(item, index) in itemList"
      :key="index"
      type="area"
      :item="item"
      :index="index + 1"
    ></interest-item>
  </b-card-group>
  <div v-else>관심지역을 사용하려면 로그인을 해주세요</div>
</template>

<script>
import { mapState } from "vuex";
import { interestList } from "@/util/address.js";
import InterestItem from "@/components/interest/item/InterestItem.vue";

export default {
  name: "InterestArea",
  data() {
    return {
      itemList: [],
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  mounted() {
    this.getInterestArea();
  },
  components: {
    InterestItem,
  },
  methods: {
    getInterestArea() {
      if (this.userInfo)
        interestList(this.userInfo.id, ({ data }) => {
          this.itemList = data;
        });
    },
  },
};
</script>

<style></style>
