import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import BoardView from "../views/BoardView.vue";
import MapView from "../views/MapView.vue";

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
        path: "",
        name: "MapSearch",
        component: () => import("@/components/address/MapSearch.vue"),
      },
      {
        path: "house",
        name: "HouseSearch",
        component: () => import("@/components/address/HouseSearch.vue"),
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
