<template>
  <div class="website-container"
  >
    <div class="title">
      <el-icon color="#c9cfce" :class="histogramIcon" :size="30"
        ><Histogram
      /></el-icon>
      <div class="font-bold text-blog-deeper-color dark:text-blog-deep-color ml-3 text-xl">网站资讯</div>
    </div>
    <div class="content p-[10px]">
      <div class="substance">
        <span class="sub-title">文章数目</span>
        <span class="sub-content">1</span>
      </div>
      <div class="substance">
        <span class="sub-title">运行时长</span>
        <span class="sub-content">&nbsp;天</span>
      </div>
      <div class="substance">
        <span class="sub-title">访问次数</span>
        <span class="sub-content">1</span>
      </div>
      <div class="substance">
        <span class="sub-title">最后更新</span>
        <span class="sub-content">天前</span>
      </div>
      <div class="substance">
        <span class="sub-title">开发日期</span>
        <span class="sub-content">2024/10/5</span>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
// TODO 获取启动时长等信息
import { onMounted, ref } from "vue";


const histogramIcon = ref("histogram-icon");
onMounted(() => {

  
  let b = true;
  setInterval(() => {
    b = !b;
    histogramIcon.value = b ? "histogram-icon" : "histogram-icon-active";
  }, 1000);
});
</script>
  
  <style  scoped>
.title {
  @apply h-[30] p-[10px] flex  items-center;
}
.histogram-icon {
  scale: 1;
  transition: scale 1s linear;
}
.histogram-icon-active {
  scale: 1.3;
  transition: scale 1s linear;
}
.website-container {
  @apply transition-colors duration-700 w-full  min-w-[200px] h-[200px]  shadow-md rounded-md;
  background-color: var(--el-bg-color);
}
.substance {
  @apply mx-[20px] my-[5px]  text-sm flex justify-between;
}
.sub-title {
  @apply text-sm   font-bold;
}
.sub-content {
  @apply text-sm  font-bold;
}
</style>