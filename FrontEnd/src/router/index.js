import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import BoardView from "../views/BoardView.vue";
import MapView from "../views/MapView.vue";
import InterestView from "../views/InterestView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  {
    name: "board",
    path: "/board",
    component: BoardView,
    children: [
      {
        path: "",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "create",
        name: "boardCreate",
        component: () => import("@/components/board/BoardCreate.vue"),
      },
      {
        path: "view/:board_no",
        name: "boardView",
        component: () => import("@/components/board/BoardView.vue"),
      },
      {
        path: "modify/:board_no",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
    redirect: () => {
      return "/board";
    },
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView.vue"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "regist",
        name: "regist",
        component: () => import("@/components/user/UserRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        component: () => import("@/components/user/UserMypage.vue"),
      },
      {
        path: "update",
        name: "update",
        component: () => import("@/components/user/UserUpdate.vue"),
      },
      {
        path: "checkpw",
        name: "checkpw",
        component: () => import("@/components/user/checkPass.vue"),
      },
      {
        path: "delete",
        name: "delete",
        component: () => import("@/components/user/checkDelete.vue"),
      },
    ],
  },
  {
    path: "/info",
    name: "info",
    component: () => import("../views/InfoView.vue"),
    redirect: "/info/chart",
    children: [
      {
        path: "chart",
        name: "chart",
        component: () => import("@/components/info/InfoChart.vue"),
      },
    ],
  },

  {
    path: "/about",
    name: "About",
    component: () => import("../views/AboutView.vue"),
  },
  {
    path: "/map",
    name: "Map",
    component: MapView,
    children: [
      {
        path: "house",
        name: "HouseSearch",
        component: () => import("@/components/address/HouseSearch.vue"),
      },
    ],
  },
  {
    path: "/interest",
    name: "Interest",
    component: InterestView,
    children: [
      {
        path: "area",
        name: "InterestArea",
        component: () => import("@/components/interest/InterestArea.vue"),
      },
      {
        path: "apart",
        name: "InterestApart",
        component: () => import("@/components/interest/InterestApt.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
