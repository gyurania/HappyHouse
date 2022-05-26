<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articlesPage"
              :key="article.articleno"
              :article="article"
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

      <b-col v-else class="text-center">작성된 게시글이 없습니다.</b-col>
    </b-row>

    <b-row class="mt-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import BoardListItem from "@/components/board/item/BoardListItem";
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      perPage: 10,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    rows() {
      return this.articles.length;
    },
    articlesPage() {
      let arr = [];
      for (
        let i = (this.currentPage - 1) * this.perPage, cnt = 0;
        i < this.rows && cnt < this.perPage;
        i++, cnt++
      ) {
        console.log(i, cnt);
        arr.push(this.articles[i]);
      }
      return arr;
    },
  },
  mounted() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveWrite() {
      if (this.userInfo) {
        this.$router.push({ name: "boardCreate" });
      } else {
        alert("로그인 후 이용 가능합니다.");
        this.$router.push({ name: "login" });
      }
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
