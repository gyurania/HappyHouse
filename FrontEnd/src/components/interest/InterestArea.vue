<template>
  <b-container v-if="userInfo">
    <b-card-group deck>
      <interest-item
        v-for="(item, index) in itemList"
        :key="index"
        type="area"
        :item="item"
        :index="index + 1"
      ></interest-item>
    </b-card-group>
  </b-container>
  <div v-else>관심지역을 사용하려면 로그인을 해주세요</div>
</template>

<script>
import { mapState } from "vuex";
import InterestItem from "@/components/interest/item/InterestItem.vue";
import { interestList } from "@/util/address.js";

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
