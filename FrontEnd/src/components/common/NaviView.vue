<template>
  <div class="container">
    <header id="index_header" class="jumbotron text-center mb-1">
      <router-link to="/">
        <img id="logo" src="@/assets/happyhouse.png" width="400" />
      </router-link>
    </header>
    <!-- nav start -->
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link class="nav-link" to="/">Home</router-link>
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbardrop"
            data-toggle="dropdown"
          >
            동네 정보
          </a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">APT 매매</a>
            <!-- 						<a class="dropdown-item" href="#">APT 전월세</a> -->
            <!-- 						<a class="dropdown-item" href="#">주택 매매</a> -->
            <!-- 						<a class="dropdown-item" href="#">주택 전월세</a> -->
            <!-- 						<a class="dropdown-item" href="#">상권 정보</a> -->
            <!-- 						<a class="dropdown-item" href="#">환경 정보</a> -->
          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Notice</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">News</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/board">QnA</router-link>
        </li>
      </ul>

      <div id="nav-login" style="display: flex; float: right">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Sign up</a>
          </li>
        </ul>

        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">관심지역</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">my page</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">logout</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {};
</script>

<style>
.ssafy_logo {
  width: 150px;
}
.header {
  text-align: center;
  box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.3);
}
.header img {
  vertical-align: middle;
}
.logo {
  display: inline-block;
  font-size: 30pt;
  font-weight: bold;
}
</style>
