<template>
  <div class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="pin-angle-fill"></b-icon>
      관심 목록
    </h3>
    <b-navbar>
      |
      <b-navbar-nav>
        <b-nav-item
          ><router-link class="nav-link" to="/interest/area"
            >관심 지역</router-link
          ></b-nav-item
        >
      </b-navbar-nav>
      |
      <b-navbar-nav>
        <b-nav-item
          ><router-link class="nav-link" to="/interest/recent"
            >최근 본 매물</router-link
          ></b-nav-item
        >
      </b-navbar-nav>
      |
    </b-navbar>
    <router-view></router-view>
  </div>
</template>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
