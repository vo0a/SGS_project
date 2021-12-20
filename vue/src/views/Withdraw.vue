<template>
  <div class="container">
    <v-btn color="#808080" block class="btn" @click="handleWithdrawApi">
        정말로 탈퇴하기
    </v-btn>
  </div>
</template>
<script>

import axios from "axios";
import router from "@/router";

const BASE_URL = "http://localhost:8100/";

export default {
  name: "Withdraw",
  setup() {
    const handleWithdrawApi = (token) => {
      try {
        axios.delete({
            baseURL: BASE_URL,
            headers: {
              "Content-Type": "application/json",
              "X-AUTH-TOKEN": `${token}`,
            },
        });

        window.alert("탈퇴 완료 : 30일 이내에 재접속하면 탈퇴되지 않습니다.");

        router.push("/");
      }
      catch {
        window.alert("탈퇴 실패");
      }
    };
    return {
      handleWithdrawApi,
    };
  },
};
</script>

<style lang="postcss" scoped>
.container {
  height: calc(100vh - 60px);
  width: 70%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>