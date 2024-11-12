<template>
  <div class="management">
        <div class="head">
            <el-tabs v-model="activeName" tab-position="left" @tab-click="handleClick">
                <el-tab-pane label="全部笔记" name="first">
                  <AllArticle/>
                </el-tab-pane>
                <el-tab-pane label="已审核" name="second">Config</el-tab-pane>
                <el-tab-pane label="审核中" name="third">Role</el-tab-pane>
                <el-tab-pane label="未通过" name="fourth">Task</el-tab-pane>
              </el-tabs>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import AllArticle from './status/all-article.vue';

const activeName = ref('first')

function handleClick(tabs,event){
  
}
</script>

<style lang="scss" scoped>

</style>
