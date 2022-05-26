<template>
  <b-container class="bv-example-row mt-3">
    <!-- select box -->
    <b-row class="mt-4 mb-4">
      <b-col class="sm-3">
        <b-form-select
          class="w-50"
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          class="w-50"
          v-model="gugunCode"
          :options="guguns"
          @change="selectGugun"
        ></b-form-select>
      </b-col>
    </b-row>

    <!-- chart -->
    <div style="text-align: center" class="p-3 mt-0" v-if="infoResult.info">
      <div class="row mt-0" style="display: flex; justify-content: center">
        <div class="col-3 scale-up-2 child">
          <h5>인구수</h5>
          <div v-if="infoResult.info">
            <Bar
              :chart-options="chartOptions"
              :chart-data="popul"
              :chart-id="chartId"
              :dataset-id-key="datasetIdKey"
              :plugins="plugins"
              :width="width"
              :height="height"
            />
          </div>
        </div>

        <div class="col-3 scale-up-2 child">
          <h5>인구밀도</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="density"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-3 scale-up-2 child">
          <h5>시장</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="market"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-3 scale-up-2 child">
          <h5>의료기관</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="medical"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>
      </div>

      <!-- chart -->
      <div class="row mt-4" style="display: flex; justify-content: center">
        <div class="col-3 scale-up-2 child">
          <h5>공원</h5>
          <div v-if="infoResult.info">
            <Bar
              :chart-options="chartOptions"
              :chart-data="park"
              :chart-id="chartId"
              :dataset-id-key="datasetIdKey"
              :plugins="plugins"
              :width="width"
              :height="height"
            />
          </div>
        </div>

        <div class="col-3 scale-up-2 child">
          <h5>공공도서관</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="library"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-3 scale-up-2 child">
          <h5>노인복지시설</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="welfare"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-3 scale-up-2 child">
          <h5>보육시설</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="child"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>
      </div>
    </div>

    <div v-else>
      <h4><br /><br />자치구를 선택하세요.</h4>
    </div>
  </b-container>
</template>

<script>
import { Bar } from "vue-chartjs/legacy";
import http from "@/util/http-common";

import { mapState, mapActions, mapMutations } from "vuex";
const addressStore = "addressStore";

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(Title, Tooltip, BarElement, CategoryScale, LinearScale);

export default {
  name: "InfoChart",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      gugunName: null,

      guName: "", // 검색한 구 코드

      chartOptions: {
        responsive: true,
      },
      infoResult: {
        info: null,
        avg: null,
      },
    };
  },
  components: { Bar },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 600,
    },
    height: {
      type: Number,
      default: 600,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Object,
      default: () => {},
    },
  },
  computed: {
    ...mapState(addressStore, [
      "sidos",
      "guguns",
      "dongs",
      "sido",
      "gugun",
      "dong",
    ]),
  },
  mounted() {
    if (this.$route.params.sido_code)
      this.sidoCode = this.$route.params.sido_code;
    if (this.$route.params.gugun_code) {
      this.gugunCode = this.$route.params.gugun_code;
    }
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(addressStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(addressStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_SIDO",
      "SET_GUGUN",
      "SET_DONG",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    selectGugun() {
      this.SET_GUGUN(this.gugunCode);
      this.guName = this.gugun;
      this.search();
    },

    search() {
      if (this.sidoCode == "11") {
        http
          .get(`/info/${this.gugun}`)
          .then(({ data }) => {
            if (data != null) {
              this.infoResult.info = data.info;
              this.infoResult.avg = data.avg;
              this.drawChart();
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("서비스 준비 중입니다...");
      }
    },

    // 차트 그리기
    drawChart() {
      // 인구 수
      this.popul = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.popul, this.infoResult.avg.popul],
          },
        ],
      };
      // 인구 밀도
      this.density = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.density, this.infoResult.avg.density],
          },
        ],
      };
      // 시장
      this.market = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.market, this.infoResult.avg.market],
          },
        ],
      };
      // 의료기관
      this.medical = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.medical, this.infoResult.avg.medical],
          },
        ],
      };
      //공원
      this.park = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.park, this.infoResult.avg.park],
          },
        ],
      };
      // 도서관
      this.library = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.library, this.infoResult.avg.library],
          },
        ],
      };
      // 복지시설
      this.welfare = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.welfare, this.infoResult.avg.welfare],
          },
        ],
      };
      // 보육기관
      this.child = {
        labels: [this.guName, "서울시 평균"],
        datasets: [
          {
            borderColor: ["#87CEEB", "#BDBDBD"],
            backgroundColor: [
              "rgba(54, 162, 235, 0.5)",
              "rgba(189,189,189,0.5)",
            ],
            borderWidth: 3,
            data: [this.infoResult.info.child, this.infoResult.avg.child],
          },
        ],
      };
    },
  },
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

.bv-example-row {
  font-family: "Jeju Gothic";
}
</style>
