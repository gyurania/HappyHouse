<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="70px"
            height="60px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link class="nav-link" to="/map/house"
              >거래조회</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link class="nav-link" to="/info"
              >지역정보</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link class="nav-link" to="/notice"
              >공지사항</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link class="nav-link" to="/board"
              >커뮤니티</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link class="nav-link" to="/interest"
              >관심목록</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <!-- 로그인 상태 -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center" id="welcome"
            ><b-avatar
              variant="primary"
              v-text="userInfo ? userInfo.name.charAt(0).toUpperCase() : ''"
            ></b-avatar>
            {{ userInfo.name }}({{ userInfo.id }})님 환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link :to="{ name: 'mypage' }" class="nav-link"
              >회원정보</router-link
            ></b-nav-item
          >
          <b-nav-item
            class="nav-link align-self-center"
            @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
        </b-navbar-nav>

        <!-- 로그아웃 상태 -->
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'regist' }" class="nav-link text-dark"
                ><b-icon icon="person-circle"></b-icon>회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item
              ><router-link :to="{ name: 'login' }" class="nav-link text-dark"
                ><b-icon icon="key"></b-icon>로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import us from "@/store/modules/userStore";

const userStore = "userStore";

export default {
  name: "NaviView",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", us);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style>
.ssafy_logo {
  /* width: 150px; */
}
.header {
  text-align: center;
  box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.3);
}
.header img {
  vertical-align: middle;
}
.logo {
  display: inline-block;
  font-size: 30pt;
  font-weight: bold;
}
.dropdown-item > li {
  padding-top: 0px;
  padding-right: 0px;
  padding-left: 0px;
}

#welcome {
  pointer-events: none;
}
</style>
