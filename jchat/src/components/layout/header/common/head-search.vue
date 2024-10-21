<template>
  <div>
    <el-input v-model="content" placeholder="搜索"></el-input>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const content=ref('')
</script>

<style lang="scss" scoped>

</style>