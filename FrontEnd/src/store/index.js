import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common";

import createPersistedState from "vuex-persistedstate"; //

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
  plugins: [createPersistedState()],
});
