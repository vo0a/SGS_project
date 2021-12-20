<template>
  <div class="container">
    <sign-up-box />
  </div>
</template>
<script>
import SignUpBox from "../components/SignUpBox.vue";
export default {
  name: "SignUp",
  components: { SignUpBox },
  setup() {},
};
</script>

<style lang="postcss" scoped>
.container {
  height: calc(100vh - 60px);
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
