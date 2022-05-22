<template>
  <div id="map" style="width: 95%; height: 650px"></div>
</template>

<script>
import { mapState } from "vuex";
import { eventBus } from "@/main.js";

export default {
  name: "KaKaoMap",
  data() {
    return {
      infowindows: [],
      positions: [],
      markers: [],
    };
  },
  computed: {
    ...mapState("houseStore", ["apts"]),
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
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    removeMarker() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
      this.infowindows.forEach((infowindow) => {
        infowindow.close();
      });
      this.infowindows = [];
    },
    displayMarker() {
      let map = this.map;
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: map,
          position: position[1],
        });
        this.markers.push(marker);
        const infowindow = new kakao.maps.InfoWindow({
          content: `<div style="width:150px;text-align:center;padding:6px 0;">${position[0]}</div>`,
        });
        infowindow.open(map, marker);
        this.infowindows.push(infowindow);
      });
    },
    async getMarkerCoordinate(range) {
      let map = this.map;
      var positions = [];
      var geocoder = new kakao.maps.services.Geocoder();
      let lastIdx = this.apts.length;
      let cnt = 0;
      this.apts.forEach((apt) => {
        let str = apt.법정동 + apt.지번;
        geocoder.addressSearch(str, async (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            let coords = await new kakao.maps.LatLng(result[0].y, result[0].x);
            cnt++;
            if (!positions.some((position) => position[0] === apt.아파트)) {
              positions.push([apt.아파트, coords]);
            }
            if (lastIdx === cnt) {
              map.setCenter(coords);
              if (range == "gugun") {
                map.setLevel(7);
              } else if (range == "dong") {
                map.setLevel(5);
              } else if (range == "house") {
                map.setLevel(3);
              }
              this.positions = positions;
              this.removeMarker();
              this.displayMarker();
            }
          }
        });
      });
    },
  },
};
</script>

<style></style>
