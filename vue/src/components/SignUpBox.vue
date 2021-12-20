<template>
  <div class="container">
    <v-card class="login-card" max-width="344">
      <v-text-field
        v-model="name"
        label="name"
        type="text"
        prepend-icon="mdi-account"
        required
      ></v-text-field>
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
      <v-btn color="#F08080" block class="login-btn" @click="handleSignUp">
        회원가입
      </v-btn>
    </v-card>
  </div>
</template>

<script>
import { reactive, toRefs } from "@vue/composition-api";
import { baseApi } from "@/util/axiosApi";
import router from "@/router";

export default {
  name: "SignUpBox",
  setup() {
    const state = reactive({
      name: "",
      email: "",
      password: "",
    });
    const handleSignUp = async () => {
      try {
        // 회원가입 요청 api
        const { data } = await baseApi.post("/auth/signup", {
          name: state.name,
          email: state.email,
          password: state.password,
        });
        window.alert("회원가입이 완료되었습니다. 로그인을 부탁해요 :)");
        router.push("/login");
        console.log(data);
      } catch (e) {
        window.alert("회원가입 실패");
        console.log(e);
      }
    };
    return {
      ...toRefs(state),
      handleSignUp,
    };
  },
};
</script>

<style lang="postcss" scoped>
.login-card {
  padding: 2rem;
}
</style>
