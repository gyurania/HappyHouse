<template>
  <b-tr @click="link()" class="link">
    <b-td>{{ article.board_no }}</b-td>
    <b-td class="text-left">
      {{ article.title }}
    </b-td>
    <b-td>{{ article.userid }}</b-td>
    <b-td>{{ article.create_time | dateFormat }}</b-td>
  </b-tr>
</template>
<script>
// <router-link :to="{ name: 'boardView', params: { board_no: article.board_no } }" class="link" >
// </router-link>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    article: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    link() {
      this.$router.push({
        name: "boardView",
        params: { board_no: this.article.board_no },
      });
    },
  },
};
</script>

<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}

.link:hover {
  cursor: pointer;
}
</style>
