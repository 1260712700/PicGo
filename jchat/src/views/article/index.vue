<template>
  <div class="w-full">
    <el-tabs
      class="tabs"
      type="border-card"
      v-model="activeName"
      @tab-click="handleClick"
    >
      <transition name="el-fade-in-linear" mode="out-in"> 
        <!-- 使用 v-show 来避免销毁组件 -->
        <el-tab-pane label="发现" name="first" v-show="activeName === 'first'">
          <Discover />
        </el-tab-pane>
      </transition>

      <transition name="el-fade-in-linear" mode="out-in">
        <el-tab-pane label="发布" name="second" v-show="activeName === 'second'">
          <Publish />
        </el-tab-pane>
      </transition>
    </el-tabs>
  </div>
</template>

<script setup>
import Publish from './main/publish.vue';
import Discover from './main/discover.vue';
import { ref } from 'vue'
import { useRouter } from 'vue-router';
import { useArticleStore } from '@/stores/article';
const activeName = ref('first')
const router= useRouter()
const articleStore= useArticleStore()
function handleClick(tabs,event){
  articleStore.clearPage()
  articleStore.clearArticle()
  if(tabs.props.name==='first'){
    router.push('/')
  }
  if(tabs.props.name==='second'){
    router.push('/article/publish')
  }
}
</script>

<style  >
.tabs .el-tabs__header{
  @apply transition-colors duration-700 rounded-md;
}
.el-tabs--border-card {
  @apply transition-colors duration-700 rounded-md;
}
.el-tabs__item{
  background-color: transparent;
  @apply bg-transparent
}
.el-tabs__item.is-active {
  @apply transition-colors duration-700
}
</style>