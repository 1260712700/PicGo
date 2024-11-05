<template>
  <div >
    <div 
    class="hidden article-box md:z-50 md:flex md:h-full md:w-full md:rounded-md md:shadow-md  ">
    <div class=" h-full  w-[50%]">
      <slot name="content" ></slot>
    </div>
    <div class=" h-full  overflow-hidden w-[50%]">
      <slot name="media"></slot>
    </div>
  </div>  
  <div 
  class="md:hidden article-box z-50   h-full w-full" style="background-color: var(--el-bg-color);">
  <div class="head">
    
  </div>
  <div class=" h-[50%]  w-full">
    <slot name="media"></slot>
  </div>
  <div class=" h-[50%] rounded-md  overflow-auto w-full ">
    <slot name="content" ></slot>
  </div>
</div>  
  </div>
  

</template>
<script setup>
</script>

<style  scoped>

</style>