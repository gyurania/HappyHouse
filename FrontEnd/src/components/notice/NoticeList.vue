<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mt-1">
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
              v-for="notice in noticesPage"
              :key="notice.noticeno"
              :notice="notice"
            />
          </tbody>
        </b-table-simple>
        <b-pagination
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
        ></b-pagination>
      </b-col>

      <b-col v-else class="text-center">등록된 공지사항이 없습니다.</b-col>
    </b-row>

    <b-row class="mt-1">
      <b-col class="text-right">
        <b-button
          v-if="adminCheck"
          variant="outline-primary"
          @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
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
      perPage: 10,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    rows() {
      return this.notices.length;
    },
    noticesPage() {
      let arr = [];
      for (
        let i = (this.currentPage - 1) * this.perPage, cnt = 0;
        i < this.rows && cnt < this.perPage;
        i++, cnt++
      ) {
        arr.push(this.notices[i]);
      }
      return arr;
    },
  },
  created() {
    http.get(`/notice`).then(({ data }) => {
      this.notices = data;
    });
  },
  mounted() {
    // 관리자일 경우에만 공지사항 작성 가능
    if (this.userInfo != null) {
      if (this.userInfo.isAdmin == 1) {
        this.adminCheck = true;
      }
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
