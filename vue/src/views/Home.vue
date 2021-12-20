<template>
  <div class="home">
    <div class="container">
      <h1>Hello world!</h1>

      <div v-if="!isLogin" class="main-box">
        <router-link to="/login">
          <v-btn color="#F08080"> 로그인 </v-btn>
        </router-link>
        <router-link to="/signup">
          <v-btn color="#F08080"> 회원가입 </v-btn>
        </router-link>
      </div>

      <div v-else class="main-box">
        
        <div v-if="role === 'ADMIN'">
          <div class="admin-home">
            <p>
              당신은 관리자 {{username}}
            </p>
            <router-link to="/admin/users">
              <v-btn class="btn" color="#F08080"> 회원 정보 보기 </v-btn>
            </router-link>
          </div>
        </div>
        <div v-else>
          <div class="user-home">
            <p>
              반갑다.
              당신의 이름은 {{username}}
              등급은 {{role}}다.
            </p>
            <router-link to="/withdraw">
              <v-btn class="btn" color="#808080"> 탈퇴하기 </v-btn>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import { reactive, toRefs } from "@vue/composition-api";

export default {
  name: "Home",
  setup() {
    const state = reactive({
      username: "",
      role: "",
      isLogin: false,
    });

    (() => {
      state.username = window.localStorage.getItem("username");
      state.role = window.localStorage.getItem("role");
      state.isLogin = window.localStorage.access ? true : false;
    })();
    return {
      ...toRefs(state),
    };
  },
};
</script>
<style scoped lang="postcss">
.home {
  height: calc(100vh - 60px);
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-wrap: wrap;
  gap: 3rem;
}
.main-box {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  align-items: center;
  justify-content: center;
}
.btn {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
