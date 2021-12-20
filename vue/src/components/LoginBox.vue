<template>
  <div class="login-box">
    <v-card class="login-card" max-width="344">
      <v-text-field
        v-model="email"
        label="Email"
        type="email"
        prepend-icon="mdi-email"
        required
      ></v-text-field>
      <v-text-field
        v-model="password"
        label="Password"
        type="password"
        prepend-icon="mdi-lock"
        required
      ></v-text-field>
      <v-btn color="#F08080" block class="login-btn" @click="handleLogin">
        Login
      </v-btn>
    </v-card>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/composition-api";
import { baseApi } from "@/util/axiosApi";
import router from "@/router";

export default {
  name: "LoginBox",
  setup() {
    const state = reactive({
      email: "",
      password: "",
    });
    const handleLogin = async () => {
      console.log(state.email);
      console.log(state.password);

      try {
        const { data } = await baseApi.post("/auth/signin", {
          email: state.email,
          password: state.password,
        });

        console.log(data);

        // 토큰만 저장하고 싶은데, 프론트에서 토큰 까는 방법 모름
        window.localStorage.setItem("username", data.name);
        window.localStorage.setItem("role", data.role);
        window.localStorage.setItem("access", data.token);
        window.localStorage.setItem("userid", data.uuid);

        router.push("/"); // 원하는 경로로 리다이렉트
      } catch (e) {
        window.alert("로그인 실패");
        console.log(e);
      }
    };
    return {
      ...toRefs(state),
      handleLogin,
    };
  },
};
</script>

<style lang="postcss" scoped>
.login-card {
  padding: 2rem;
}
</style>
