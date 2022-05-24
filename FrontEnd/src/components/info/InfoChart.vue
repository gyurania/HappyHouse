<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>지역 정보</h3></b-alert>
      </b-col>
    </b-row>
    <si-gun-dong />

    <div align="center">
      <b-form-input id="id" v-model="guName" type="text"></b-form-input>
      <b-button type="button" variant="primary" class="m-1" @click="list"
        >검색</b-button
      >
    </div>

    <!-- chart -->
    <div style="text-align: center" class="p-3 mt-0">
      <div class="row mt-0" style="display: flex; justify-content: center">
        <div class="col-2 scale-up-2 child">
          <h5>인구수</h5>
          <div v-if="infoResult.info">
            <Bar
              :chart-options="chartOptions"
              :chart-data="chartData"
              :chart-id="chartId"
              :dataset-id-key="datasetIdKey"
              :plugins="plugins"
              :width="width"
              :height="height"
            />
          </div>
        </div>

        <div class="col-2 scale-up-2 child">
          <h5>인구밀도</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="chartData2"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-2 scale-up-2 child">
          <h5>시장</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="chartData2"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-2 scale-up-2 child">
          <h5>의료기관</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="chartData2"
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
        <div class="col-2 scale-up-2 child">
          <h5>공원</h5>
          <div v-if="infoResult.info">
            <Bar
              :chart-options="chartOptions"
              :chart-data="chartData"
              :chart-id="chartId"
              :dataset-id-key="datasetIdKey"
              :plugins="plugins"
              :width="width"
              :height="height"
            />
          </div>
        </div>

        <div class="col-2 scale-up-2 child">
          <h5>공공도서관</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="chartData2"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-2 scale-up-2 child">
          <h5>노인복지시설</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="chartData2"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>

        <div class="col-2 scale-up-2 child">
          <h5>보육시설</h5>
          <Bar
            :chart-options="chartOptions"
            :chart-data="chartData2"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :width="width"
            :height="height"
          />
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import SiGunDong from "@/components/address/include/SiGunDong.vue";
import { Bar } from "vue-chartjs/legacy";
import http from "@/util/http-common";

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  // Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  // Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "InfoChart",
  data() {
    return {
      chartData: {
        labels: ["구 이름", "평균"],
        datasets: [{ data: [100, 30] }],
      },
      temp: 100,
      chartData2: {
        labels: ["구 이름", "평균"],
        datasets: [
          {
            borderColor: ["#f3b773", "#BDBDBD"],
            backgroundColor: ["rgba(243,183,115,0.5)", "rgba(189,189,189,0.5)"],
            borderWidth: 5,
            data: [20, 43],
          },
        ],
      },
      chartOptions: {
        responsive: true,
      },
      guName: "",
      infoResult: {
        info: null,
        avg: null,
      },
    };
  },
  components: { Bar, SiGunDong },
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
      default: 800,
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
  methods: {
    list() {
      http.get(`/info/${this.guName}`).then(({ data }) => {
        let msg = "실패";
        if (data != null) {
          msg = "성공";
          this.infoResult.info = data.info;
          this.infoResult.avg = data.avg;
          console.log(this.infoResult.info.market);
          console.log(this.infoResult.avg.market);
        }
        alert(msg);
      });
    },
  },
};
</script>

<style scoped>
/* @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

.bv-example-row {
  font-family: "Jeju Gothic";
} */
</style>
