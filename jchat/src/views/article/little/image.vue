<template>
    <div class="article-cover top w-full  h-full rounded-md  overflow-hidden">
        <el-skeleton
        style="width: 100%; height: 100%"
        :loading="loading"
        animated
        :throttle="500"
      >
      <template #template>
        <el-skeleton-item 
        class=" transition-colors duration-700"
        variant="image" style="width: 100%; height: 100%"/>
      </template>
      <template #default>
        <div  
        :ref="el => setArticleImageListRef(el)"
            :style="{backgroundImage:'url('+bgCover+')'}"
             class="transition-all  w-full bg-cover bg-center h-full duration-700" />
      </template>
    </el-skeleton>
      </div>

     </template>

<script setup>
import { ref } from 'vue';

defineProps({
     bgCover:String,
     loading:Boolean
})
// 定义暴露给父组件的方法
defineExpose({
    enLargeArticleCover,reduceArticleCover
})
function enLargeArticleCover() {
  if(articleImage.value) {
    articleImage.value.style.transform = 'scale(1.3)'
  }
  }
  function reduceArticleCover() {
    if(articleImage.value)
  articleImage.value.style.transform = 'scale(1)'
  }
  
const articleImage=ref()
  function setArticleImageListRef(el) {
    articleImage.value=el
  }
</script>

<style lang="scss" scoped>

</style>