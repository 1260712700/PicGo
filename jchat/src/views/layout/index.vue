<script setup >
import HeaderBox from '@/components/layout/header/index.vue';

</script>

<template>
  <HeaderBox/>
  <div style="min-height: 100vh">
    <router-view></router-view>
   <!-- <router-view v-slot="{Component}">
      <transition name="el-fade-in-linear" mode="out-in">
        <component :is="Component"/>
      </transition>
    </router-view> -->
 </div> 

</template>

<style scoped lang="scss">

</style>