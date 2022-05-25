import { sidoList, gugunList, dongList } from "@/util/address.js";

const addressStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    sido: "선택하세요",
    gugun: "선택하세요",
    dong: "선택하세요",
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.dongCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_SIDO: (state, sido) => {
      state.gugun = "선택하세요";
      state.sidos.forEach((object) => {
        if (object.value == sido) state.sido = object.text;
      });
    },
    SET_GUGUN: (state, gugun) => {
      state.dong = "선택하세요";
      state.guguns.forEach((object) => {
        if (object.value === gugun) state.gugun = object.text;
      });
    },
    SET_DONG: (state, dong) => {
      state.dongs.forEach((object) => {
        if (object.value == dong) state.dong = object.text;
      });
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = sidoCode;
      gugunList(
        params,
        ({ data }) => {
          commit("CLEAR_GUGUN_LIST");
          commit("SET_GUGUN_LIST", data);
          commit("SET_SIDO", sidoCode);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = gugunCode;
      dongList(
        params,
        ({ data }) => {
          commit("CLEAR_DONG_LIST");
          commit("SET_DONG_LIST", data);
          commit("SET_GUGUN", gugunCode);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default addressStore;
