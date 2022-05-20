import { houseList } from "@/util/house.js";

const houseStore = {
  namespaced: true,
  state: {
    houses: [],
    apts: [],
  },
  getters: {},

  mutations: {
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_APT_LIST: (state, houses) => {
      let arr = [];
      for (let house of houses) {
        if (!arr.find((element) => element.아파트 == house.아파트)) {
          arr.push(house);
        }
      }
      state.apts = arr;
    },
    SET_APT_LIST_DONG: (state, dongName) => {
      let apts = [];
      let arr = [];
      for (let house of state.houses) {
        if (!apts.find((element) => element.아파트 == house.아파트)) {
          apts.push(house);
        }
      }
      for (let apt of apts) {
        if (apt.법정동.trim().includes(dongName.trim())) {
          arr.push(apt);
        }
        console.log(apt.법정동.trim());
      }
      state.apts = arr;
    },
    SET_DETAIL_HOUSE: (state, houseName) => {
      let arr = [];
      for (let house of state.houses) {
        if (house.아파트.trim() == houseName) {
          arr.push(house);
        }
      }
      state.apts = arr;
    },
  },
  actions: {
    getHouseList: ({ commit }, args) => {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        LAWD_CD: args.gugunCode,
        DEAL_YMD: args.YMD,
        numOfRows: 10000,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        (response) => {
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
          commit("SET_APT_LIST", response.data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
