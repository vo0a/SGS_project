<template>
  <div>
    <v-app>
      <global-nav-bar />
      <div class="main">
        <router-view />
      </div>
    </v-app>
  </div>
</template>

<script>
import GlobalNavBar from "./components/GlobalNavBar.vue";

export default {
  name: "App",
  components: { GlobalNavBar },
};
</script>
<style scoped>
.v-app {
  height: 100%;
}
.main {
  height: 100%;
}
</style>
