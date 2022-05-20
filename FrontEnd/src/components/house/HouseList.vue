<template>
  <b-container v-if="apts && apts.length" class="list">
    <div v-if="type == 'detail'">돌아가기</div>
    <house-list-item
      v-for="(apt, index) in apts"
      :key="index"
      :apt="apt"
      :type="type"
      @typeSelect="typeSelect"
    >
    </house-list-item>
  </b-container>
  <b-container v-else>
    <b-row
      ><b-col><b-alert show>거래 내역이 없습니다.</b-alert></b-col></b-row
    >
  </b-container>
</template>
<script>
import { mapState } from "vuex";
import HouseListItem from "@/components/house/HouseListItem.vue";

const houseStore = "houseStore";
export default {
  name: "HouseList",
  data() {
    return {};
  },
  props: {
    type: String,
  },
  components: {
    HouseListItem,
  },
  computed: {
    ...mapState(houseStore, ["apts"]),
  },
  methods: {
    typeSelect() {
      this.$emit("typeSelect", "detail");
    },
  },
};
</script>

<style>
.list {
  overflow: scroll;
  height: 650px;
  overflow-x: hidden;
}
</style>
