<script setup >
import HeaderBox from '@/components/header/index.vue';

</script>

<template>
  <div>
    <HeaderBox/>
    <div style="min-height: 100vh">
      <router-view></router-view>
  </div>
 </div> 

</template>

<style scoped lang="scss">

</style>