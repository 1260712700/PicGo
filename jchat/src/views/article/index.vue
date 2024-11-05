<template>
    <div class="w-full">
        <el-tabs 
        class="tabs"
        type="border-card" 
        v-model="activeName"  @tab-click="handleClick">
          <el-tab-pane label="发现" name="first">
            <Discover/>
          </el-tab-pane>
          <el-tab-pane label="发布" name="second">
            <Publish/>
          </el-tab-pane>
        </el-tabs>
    
    </div>
</template>

<script setup>
import Publish from './main/publish.vue';
import Discover from './main/discover.vue';
import { ref } from 'vue'

const activeName = ref('first')

const handleClick = (tab,event) => {
}


</script>

<style  >
.tabs .el-tabs__header{
  @apply transition-colors duration-700 rounded-md;
}
.el-tabs--border-card {
  @apply transition-colors duration-700 rounded-md;
}
</style>