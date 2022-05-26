<template>
  <div id="map" style="width: 95%; height: 590px"></div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import { eventBus } from "@/main.js";

export default {
  name: "KaKaoMap",
  data() {
    return {
      infowindows: [],
      positions: [],
      markers: [],
      num: 0,
      range: "",
    };
  },
  computed: {
    ...mapGetters("houseStore", ["aptResult"]),
    ...mapState("addressStore", ["sido", "gugun", "dong"]),
  },
  created() {
    eventBus.$on("getGeoCode", (range) => {
      this.getMarkerCoordinate(range);
    });
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&libraries=services`;
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.098066, 127.505276),
        level: 13,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    async removeMarker() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
      this.infowindows.forEach((infowindow) => {
        infowindow.close();
      });
      this.infowindows = [];
    },
    async getMarkerCoordinate(range) {
      let map = this.map;
      let positions = [];
      let geocoder = new kakao.maps.services.Geocoder();
      let lastIdx = this.aptResult?.length;
      let cnt = 0;
      if (this.num++ != 0 && range == this.range) return;
      this.range = range;
      if (lastIdx) {
        this.aptResult.forEach((apt) => {
          let str = `${this.sido} ${apt.법정동} ${apt.도로명} ${apt.도로명건물본번호코드} ${apt.도로명건물부번호코드}`;
          geocoder.addressSearch(str, async (result, status) => {
            cnt++;
            if (status === kakao.maps.services.Status.OK) {
              let coords = await new kakao.maps.LatLng(
                result[0].y,
                result[0].x
              );
              if (!positions.some((position) => position[0] === apt.아파트)) {
                positions.push([apt.아파트, coords]);
              }
              if (lastIdx === cnt) {
                if (range == "gugun") {
                  map.setLevel(7);
                } else if (range == "dong") {
                  map.setLevel(5);
                } else if (range == "house") {
                  map.setLevel(3);
                }
                this.positions = positions;
                await this.removeMarker();
                this.displayMarker("apt");
              }
            }
          });
        });
      } else {
        let str = `${this.sido}`;
        if (this.gugun != "선택하세요") str += ` ${this.gugun}`;
        if (this.dong != "선택하세요") str += ` ${this.dong}`;
        geocoder.addressSearch(str, async (result, status) => {
          cnt++;
          if (status === kakao.maps.services.Status.OK) {
            let coords = await new kakao.maps.LatLng(result[0].y, result[0].x);
            if (cnt === 1) {
              positions.push([str.split(" ").slice(-1)[0], coords]);
              this.positions = positions;
              map.setLevel(5);
              await this.removeMarker();
              this.displayMarker("dong");
            }
          }
        });
        this.num = 0;
      }
    },
    displayMarker(type) {
      let map = this.map;
      map.setCenter(
        new kakao.maps.LatLng(this.positions[0][1].Ma, this.positions[0][1].La)
      );
      map.relayout();
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: map,
          position: position[1],
        });
        this.markers.push(marker);
        const infowindow = new kakao.maps.InfoWindow({
          content: `<span class="info-title">${position[0]}</span>`,
        });
        infowindow.open(map, marker);
        this.infowindows.push(infowindow);
        kakao.maps.event.addListener(
          marker,
          "click",
          this.makeClickListener(position[0], type)
        );
      });
      var infoTitle = document.querySelectorAll(".info-title");
      infoTitle.forEach(function (e) {
        var w = e.offsetWidth + 10;
        var ml = w / 2;
        e.parentElement.style.top = "82px";
        e.parentElement.style.left = "50%";
        e.parentElement.style.marginLeft = -ml + "px";
        e.parentElement.style.width = w + "px";
        e.parentElement.previousSibling.style.display = "none";
        e.parentElement.parentElement.style.border = "0px";
        e.parentElement.parentElement.style.background = "unset";
      });
    },
    makeClickListener(name, type) {
      if (type === "apt") {
        this.num = 0;
        return function () {
          eventBus.$emit("aptMarkerSelect", name);
        };
      } else if (type === "dong") {
        this.num = 0;
      }
    },
  },
};
</script>

<style>
.info-title {
  display: block;
  background: #50627f;
  color: #fff;
  text-align: center;
  height: 24px;
  line-height: 22px;
  border-radius: 4px;
  padding: 0px 3px;
  opacity: 0.8;
}
</style>
