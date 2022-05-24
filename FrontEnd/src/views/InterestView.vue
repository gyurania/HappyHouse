<template>
  <div>
    <h2>관심 목록</h2>
    <b-navbar>
      <b-navbar-nav>
        <b-nav-item
          ><router-link class="nav-link" to="/interest/area"
            >관심 지역</router-link
          ></b-nav-item
        >
      </b-navbar-nav>
      <b-navbar-nav>
        <b-nav-item
          ><router-link class="nav-link" to="/interest/apart"
            >관심 매물</router-link
          ></b-nav-item
        >
      </b-navbar-nav>
      <b-navbar-nav>
        <b-nav-item
          ><router-link class="nav-link" to="/interest/recent"
            >최근 본 매물</router-link
          ></b-nav-item
        >
      </b-navbar-nav>
    </b-navbar>
    <router-view></router-view>
  </div>
</template>
