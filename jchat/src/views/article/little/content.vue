<template>
    <div class="py-[8px] pl-[20px] w-full h-full rounded-md bg-transparent ">
        <div class="title text-2xl font-bold">{{item.title}}</div>
        <!-- TODO 统计值的绑定 -->
        <div class="count h-[16px] mt-2">
          
        </div>
        <div class="count  mt-2">
            统计
        </div>
        <div class="h-[28px] tag">标签</div>

        <div class="foot h-[20px] text-sm mt-[16px]">
          发布于：发布日期
        </div>
        <div class="foot h-[20px] text-sm">
          最后更新时间：更新时间
        </div>
      </div>
</template>

<script setup>
defineProps({
  item:Object
})
</script>

<style lang="scss" scoped>

</style>