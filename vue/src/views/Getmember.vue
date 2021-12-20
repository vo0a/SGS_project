<template>
  <div class="member">
    <div class="title-row">
      <span>id</span>
      <span>닉네임</span>
      <span>이메일</span>
      <span>권한</span>
      <span>활성 상태</span>
      <span>생성일</span>
      <span>수정일</span>
    </div>
    <div class="content-row">
      <span>{{ member.uuid }}</span>
      <span>{{ member.name }}</span>
      <span>{{ member.email }}</span>
      <span>{{
        member.role == 0 ? "GUEST" : member.role == 1 ? "USER" : "ADMIN"
      }}</span>
      <span>{{ member.active ? "활성" : "비활성" }}</span>
      <span>{{ member.createdDate }}</span>
      <span>{{ member.createdDate }}</span>
    </div>
  </div>
</template>
<script>

import { reactive, toRefs } from "@vue/composition-api";
import { baseApi } from "@/util/axiosApi";
import { getCurrentInstance } from "@vue/composition-api";

export default {
  name: "Getmember",
  setup() {
    const state = reactive({
      member: [],
    });
    (async () => {
      try {
        const vm = getCurrentInstance();

        console.log("===> ", vm.proxy.$route.params.uuid);
        const { data } = await baseApi.get(`/membership/admin/users/${vm.proxy.$route.params.uuid}`);
        
        console.log(data);

        state.member = data;

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