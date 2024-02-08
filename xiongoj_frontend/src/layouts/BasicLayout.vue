<template>
  <div id="basicalayout">
    <a-layout style="min-height: 100vh;">
      <a-layout-header class="header">
        <GlobalHeader />
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
#basicalayout {
}
#basicalayout .header {
  margin-bottom: 16px;
  box-shadow: #eee 1px 1px 6px;
}
#basicalayout .content {
  background: linear-gradient(to right, #fefefe, #ffff);
  margin-bottom: 16px;
  padding: 20px;
}
#basicalayout .footer {
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  background: #efefef;
  padding: 16px;
}
</style>

<script>
import GlobalHeader from "@/components/GlobalHeader";
export default {
  components: { GlobalHeader },
};
</script>
