<template>
  <b-tr>
    <b-td>{{ article.board_no }}</b-td>
    <b-td class="text-left">
      <router-link
        class="link"
        :to="{ name: 'boardView', params: { board_no: article.board_no } }"
        >{{ article.title }}</router-link
      >
    </b-td>
    <b-td>{{ article.userid }}</b-td>
    <b-td>{{ article.create_time | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
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
};
</script>

<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}

.link {
  text-decoration: none;
  color: black;
}
</style>
