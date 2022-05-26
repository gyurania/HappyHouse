import { houseList } from "@/util/house.js";
import { eventBus } from "@/main.js";

const houseStore = {
  namespaced: true,
  state: {
    houses: [],
    apts: [],
    backType: "",
    viewType: "apt",
    viewRange: "gugun",
    aptPriceFilter: {
      start: 0,
      end: 0,
    },
  },
  getters: {
    aptResult: (state) => {
      let result = [];
      state.apts.forEach((apt) => {
        let price = apt.거래금액.replace(/[^\d]+/g, "");
        if (state.viewRange != "house") {
          if (!result.find((data) => data.아파트 == apt.아파트)) {
            if (
              price * 1 >= state.aptPriceFilter.start * 1 &&
              price * 1 <= state.aptPriceFilter.end * 1
            ) {
              result.push(apt);
            }
          }
        } else if (
          price >= state.aptPriceFilter.start &&
          price <= state.aptPriceFilter.end
        ) {
          result.push(apt);
        }
      });

      return result;
    },
  },

  mutations: {
    SET_FILTER_PRICE(state, { startPrice, endPrice }) {
      state.aptPriceFilter.start = startPrice;
      state.aptPriceFilter.end = endPrice;
      eventBus.$emit("getGeoCode", state.viewRange);
    },
    INIT_HOUSE_HOUSE_STORE: (state) => {
      state.houses = [];
      state.apts = [];
      state.backType = "";
      state.viewType = "apt";
      state.viewRange = "gugun";
      state.aptPriceFilter = {
        start: 0,
        end: 100000000000,
      };
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_APT_LIST: (state, houses = state.houses) => {
      let arr = [];
      for (let house of houses) {
        arr.push(house);
      }
      state.apts = arr;
      state.viewRange = "gugun";
      state.backType = "";
      eventBus.$emit("getGeoCode", state.viewRange);
    },
    SET_APT_LIST_DONG: (state, dongName) => {
      let apts = [];
      let arr = [];
      if (dongName == "") state.viewRange = "gugun";
      else state.viewRange = "dong";
      for (let house of state.houses) {
        apts.push(house);
      }
      for (let apt of apts) {
        if (apt.법정동.trim().includes(dongName.trim())) {
          arr.push(apt);
        }
      }
      state.apts = arr;
      eventBus.$emit("getGeoCode", state.viewRange);
    },
    SET_DETAIL_HOUSE: (state, houseName) => {
      let arr = [];
      for (let house of state.houses) {
        if (house.아파트.trim() == houseName) {
          arr.push(house);
        }
      }
      state.apts = arr;
      state.viewRange = "house";
      eventBus.$emit("getGeoCode", "house");
    },
    SET_BACK_TYPE: (state, dongName = "") => {
      state.backType = dongName;
    },
    SET_VIEW_TYPE: (state, type) => {
      state.viewType = type;
    },
  },
  actions: {
    async getHouseList({ commit }, args) {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        LAWD_CD: args.gugunCode,
        DEAL_YMD: args.YMD,
        numOfRows: 10000,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      await houseList(
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
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
