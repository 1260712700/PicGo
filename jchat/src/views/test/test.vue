<!-- 父组件 ParentComponent.vue -->
<template>
  <ChildComponent>
      <template #header >
        <div class="bg-black h-[500px] w-[500px]"></div>
      </template>
      <template #container>
        <div class=" bg-zinc-950 h-[500px] w-[500px]"></div>
      </template>
      <template #foot>
        <div class=" bg-slate-900 h-[500px] w-[500px]"></div>
      </template>
  </ChildComponent>
</template>
 
<script setup>
import ChildComponent from '@/components/test.vue';


</script>