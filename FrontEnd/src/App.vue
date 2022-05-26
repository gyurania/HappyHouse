<template>
  <div id="app">
    <navi-view />
    <!-- 현재 route에 맞는 component가 렌더링된다. -->
    <router-view />
  </div>
</template>

<script>
import NaviView from "@/components/common/NaviView";

export default {
  name: "nav-home",
  components: {
    NaviView,
  },
};
</script>

<style>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
#app {
  font-family: "Jeju Gothic";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  overflow-x: hidden;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

a {
  text-decoration: none;
  color: #787878;
}

.underline {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, cyan 30%);
}
</style>
