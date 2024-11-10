<template>
    <div class="home-container">
      <Images/>
      <Brand/>
      <Main/>
      </div>
      <!-- 展示文章详情 -->
     <FriendBar/>
     <MessageBar/>

</template>

<script setup>
import Images from "@/views/Home/image/index.vue";
import Brand from "@/views/Home/brand/index.vue";
import Main from "@/views/Home/main/index.vue";
import FriendBar from "@/views/Home/friend/index.vue";
import MessageBar from "@/views/home/pop/message/index.vue";
import { onMounted } from "vue";
import { useUserStore } from "@/stores/user"; 
const userStore=useUserStore(
onMounted(()=>{
  userStore.getInfo()
})
)
</script>

<style lang="scss" scoped>

</style>