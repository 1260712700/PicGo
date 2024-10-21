<template>
  <div class="notice-container">
    <div class="title">
      <el-icon color="#c9cfce"  :size="30"
        ><Message
      /></el-icon>
      <div class="font-bold  ml-3 text-xl">公告</div>
    </div>
    <div class="content p-[10px]">
      <div class=" whitespace-normal mx-5 font-bold">
      </div>
    </div>
  </div>
</template>
    
    <script setup>

</script>
    
    <style  scoped>
.title {
  @apply h-[30] p-[10px] flex  items-center;
}
.message-icon {
  animation: shake 2s linear infinite;
}
@keyframes shake {
  10% {
    rotate: -15deg;
  }
  20% {
    rotate: 15deg;
  }
  30% {
    rotate: -12deg;
  }
  40% {
    rotate: 12deg;
  }
  50% {
    rotate: -8deg;
  }
  70% {
    rotate: 8deg;
  }

  100% {
    rotate: 0;
  }
}
.notice-container {
  @apply transition-colors duration-700 w-full min-w-[200px] h-auto shadow-md rounded-md;
  background-color: var(--el-bg-color);
}
.substance {
  @apply mx-[20px] my-[5px]  text-sm flex justify-between;
}
.sub-title {
  @apply text-sm   font-bold;
}
.sub-content {
  @apply text-sm   font-bold;
}
</style>