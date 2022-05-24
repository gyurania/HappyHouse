<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <!-- 관리자만 공지사항 작성 가능 -->
    <!-- <b-row v-if="adminCheck" class="mb-1"> -->
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>

    <b-row>
      <b-col v-if="notices.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>공지번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <notice-list-item
              v-for="notice in notices"
              :key="notice.noticeno"
              :notice="notice"
            />
          </tbody>
        </b-table-simple>
      </b-col>

      <b-col v-else class="text-center">등록된 공지사항이 없습니다.</b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import NoticeListItem from "@/components/notice/item/NoticeListItem";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeList",
  components: { NoticeListItem },
  data() {
    return {
      notices: [],
      adminCheck: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {
    http.get(`/notice`).then(({ data }) => {
      this.notices = data;
    });
  },
  mounted() {
    // 관리자일 경우에만 공지사항 작성 가능
    console.log(this.userInfo.isAdmin);
    if (this.userInfo.isAdmin == 1) {
      this.adminCheck = true;
    }
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveWrite() {
      this.$router.push({ name: "noticeCreate" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
