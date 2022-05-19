import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import BoardContainer from "../views/BoardContainer.vue";

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
    component: BoardContainer,
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
