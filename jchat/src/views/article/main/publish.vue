<template>
  <div class="publish-article">
    <div class="head h-[45px] w-full flex items-center">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        router
      >
        <el-menu-item index="/article/publish" to="/article/publish">
          <el-icon><Plus /></el-icon>发布文章
        </el-menu-item>
        <el-menu-item index="/article/home" to="/article/home">
          <el-icon><HomeFilled /></el-icon>首页
        </el-menu-item>
        <el-menu-item index="/article/management" to="/article/management">
          <el-icon><Memo /></el-icon>文章管理
        </el-menu-item>
      </el-menu>
    </div>
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const activeIndex = ref(route.path); // 初始值为当前路径
const router = useRouter();

watch(
  () => route.path,
  (newPath) => {
    activeIndex.value = newPath; // 路径变化时更新activeIndex
  }
);

onMounted(() => {
  router.push(activeIndex.value);
});
</script>

<style>
/* 添加一些样式来提升外观 */
.publish-article {
  padding: 20px;
}
.container {
  margin-top: 10px;
}
.publish-article .el-menu--horizontal {
  --el-menu-horizontal-height: 45px;
  @apply rounded-md shadow-md w-full;
}
</style>
