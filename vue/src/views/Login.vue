<template>
  <div class="login">
    <login-box />
  </div>
</template>
<script>
import LoginBox from "../components/LoginBox.vue";

export default {
  name: "Login",
  components: {
    LoginBox,
  },
  setup() {},
};
</script>

<style lang="postcss" scoped>
.login {
  height: calc(100vh - 60px);
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
