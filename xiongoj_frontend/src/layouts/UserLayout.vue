<template>
  <div id="userlayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header"> 
      <a-space>
        <img src="../assets/logo.png" class="logo" alt="">
        <div>熊 OJ</div>
      </a-space>  
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a href="http://attackingbear.top">进击的小熊 by 程序员小熊</a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#userlayout {
  text-align: center;
}
.logo{
  width: 64px;
  height: 64px;
}
#userlayout .header {
  margin-top: 16px;
  margin-bottom: 16px;
}
#userlayout .content {
  margin-bottom: 16px;
  padding: 20px;
}
#userlayout .footer {
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  background: #efefef;
  padding: 16px;
}
</style>

<script></script>
