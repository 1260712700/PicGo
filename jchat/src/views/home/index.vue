<template>
    <div class="home-container">
      <Images/>
      <Brand/>
      <Main/>
      </div>
      <!-- 展示文章详情 -->
     <ArticleLink/>
      
     <FriendBar/>
     <MessageBar/>

</template>

<script setup>
import Images from "@/views/Home/image/index.vue";
import Brand from "@/views/Home/brand/index.vue";
import Main from "@/views/Home/main/index.vue";
import ArticleLink from "@/views/Home/article/index.vue";
import FriendBar from "@/views/Home/friend/index.vue";
import MessageBar from "@/views/Home/message/index.vue";


</script>

<style lang="scss" scoped>

</style>