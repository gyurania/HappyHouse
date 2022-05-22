import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common";

import createPersistedState from "vuex-persistedstate"; //
import addressStore from "@/store/modules/addressStore.js";
import houseStore from "@/store/modules/houseStore";
import userStore from "@/store/modules/userStore";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    comments: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
  },
  mutations: {
    setComments(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    getComments(context, payload) {
      http.get(payload).then(({ data }) => {
        context.commit("setComments", data);
      });
    },
  },
  modules: {
    addressStore,
    houseStore,
    userStore,
  },
  plugins: [
    createPersistedState({
      // paths: [addressStore, userStore],
      storage: sessionStorage,
    }),
  ],
});
