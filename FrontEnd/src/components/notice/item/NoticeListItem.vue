<template>
  <b-tr>
    <b-td>{{ notice.notice_no }}</b-td>
    <b-td class="text-left">
      <router-link
        :to="{ name: 'noticeView', params: { notice_no: notice.notice_no } }"
        >{{ notice.title }}</router-link
      >
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
};
</script>

<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>
