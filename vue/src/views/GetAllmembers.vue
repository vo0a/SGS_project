<template>
  <div class="all-members">
    <div class="members">
      <div class="title-row">
        <span>id</span>
        <span>닉네임</span>
        <span>이메일</span>
        <span>권한</span>
        <span>활성 상태</span>
      </div>
      <div class="content-row" v-for="member in memberList" :key="member.uuid">
        <router-link :to="`/admin/users/${member.uuid}`">{{ member.uuid }}</router-link>
        <span>{{ member.name }}</span>
        <span>{{ member.email }}</span>
        <span>{{ member.role == 0 ? 'GUEST': member.role == 1 ? 'USER' : 'ADMIN'}}</span>
        <span>{{ member.active ? '활성' : '비활성' }}</span>
      </div>
    </div>
  </div>
</template>
<script>

import { reactive, toRefs } from "@vue/composition-api";
import { baseApi } from "@/util/axiosApi";

export default {
  name: "GetAllmembers",
  setup() {
    const state = reactive({
      memberList: [],
    });
    (async () => {
      try {
        const { data } = await baseApi.get("/membership/admin/users");
        
        console.log(data);

        state.memberList = data;

      } catch (e) {
        window.alert("로드 실패");
        console.log(e);
      }
    })();
    return {
      ...toRefs(state),
    };
  },
};
</script>

<style lang="postcss" scoped>
.all-members {
  height: calc(100vh - 60px);
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}
.members {
  width: 70%;
}
.title-row {
  display: flex;
  align-items: space-around;
  justify-content: center;
  flex-wrap: wrap;
  gap: 1rem;
  width: 80%;
  font-weight: 700;
  background-color: #bebebe;
}
.content-row {
  display: flex;
  align-items: space-around;
  justify-content: center;
  flex-wrap: wrap;
  gap: 1rem;
  width: 80%;
  font-weight: 700;
  background-color: rgb(255, 196, 196);
}
</style>