<template>
  <div class="article-box hardware-speedup z-50 flex h-full w-full">
    <!-- 左侧，媒体部分占用50% -->
    <div class="h-full w-[50%]">
      <slot name="media"></slot>
    </div>
  
    <!-- 右侧，内容部分占用50% -->
    <div class="h-full no-scrollbar rounded-md overflow-auto w-[50%]">
      <slot name="content"></slot>
    </div>
  </div>
  
  

</template>
<script setup>
</script>

<style  scoped>

</style>