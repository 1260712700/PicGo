<template>
  <div class="publish-article h-full w-full relative">
    <div class="head h-[45px] w-full flex items-center">
      <el-page-header class="shadow-md rounded-md w-full pl-3 " >
        <template #content>
          <el-menu
          :default-active="activeIndex"
          :ellipsis="false"
          class="w-full"
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
        </template>
      </el-page-header>

    </div>
    <div class="container">
    
       <router-view v-slot="{ Component }">
        <transition name="el-fade-in-linear" mode="out-in">
          <!-- 使用动态组件来包裹过渡 -->
          <component :is="Component" />
        </transition>
      </router-view>
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
  background-color: transparent;
}
</style>
