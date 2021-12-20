<template>
  <v-app-bar color="#F08080" height="60px">
    <router-link to="/">
      <v-toolbar-title>morad</v-toolbar-title>
    </router-link>

    <v-spacer></v-spacer>

    <router-link class="btn" to="/login">
      <v-btn color="beige" depressed>login</v-btn>
    </router-link>
    <router-link class="btn" to="/signup">
      <v-btn color="beige" depressed>signup</v-btn>
    </router-link>
    
    <div v-if="isLogin">
      <router-link to="/logout">
        <v-btn color="beige" depressed @click="handleLogout">logout</v-btn>
      </router-link>
    </div>

  </v-app-bar>
</template>

<script>
import { reactive, toRefs } from "@vue/composition-api";
import router from "@/router";

export default {
  name: "GlobalNavBar",
  setup() {
    const state = reactive({
      isLogin: false,
    });
    (() => {
      state.isLogin = window.localStorage.access ? true : false;
    })();
    const handleLogout = () => {
      try {
        window.localStorage.removeItem("username");
        window.localStorage.removeItem("role");
        window.localStorage.removeItem("access");
        window.localStorage.removeItem("userid");

        router.push("/"); 
      }
      catch (e) {
        window.alert("로그아웃 실패");
        console.log(e);
      }
    };
    return {
      ...toRefs(state),
      handleLogout,
    };
  },
};
</script>

<style>
.btn {
  margin-right: 10px;
}
</style>
