<template>
  <Main>
    <template #content>
      <Article/>
    </template>
    <template #information>
      <SideBar/>
    </template>
  </Main>
 </template>
 

<script setup>
import Article from '@/components/article/index.vue';
import Main from '@/components/layout/main/index.vue';
import SideBar from '@/components/layout/sideBar/index.vue';

</script>

<style scoped>

</style>