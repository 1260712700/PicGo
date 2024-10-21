<template>
  <div>
    <el-tooltip
    class="box-item"
    effect="light"
    content="点此回到主页"
    placement="bottom"
  >
    <el-link href="/" type="primary">
      <div  class="flex font-bold  text-lg items-center justify-center h-[45px] w-[100px]">
        你的世界

      </div>
    </el-link>
  </el-tooltip>
  </div>
  
 
</template>

<script setup>
import { useInfoStore } from '@/stores/info';

const infoStore= useInfoStore()
</script>

<style lang="scss" scoped>
</style>