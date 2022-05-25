<template>
  <b-tr @click="link()" class="link">
    <b-td>{{ notice.notice_no }}</b-td>
    <b-td class="text-left">
      {{ notice.title }}
    </b-td>
    <b-td>{{ notice.userid }}</b-td>
    <b-td>{{ notice.create_time | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    notice: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    link() {
      this.$router.push({
        name: "noticeView",
        params: { notice_no: this.notice.notice_no },
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
