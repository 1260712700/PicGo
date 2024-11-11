<template>
  <div class="management">
        <div class="head">
            <el-tabs v-model="activeName" tab-position="left" @tab-click="handleClick">
                <el-tab-pane label="全部笔记" name="first">
                    <div class="w-full text-right text-sm">共有{{articleStore.personalArticleList.length}}篇文章</div>
                    <div class="article"  v-for="(item,index) in articleStore.personalArticleList" :key="index">
                    <ArticleItem :item="item"/>
                    </div>
                  
                </el-tab-pane>
                <el-tab-pane label="已审核" name="second">Config</el-tab-pane>
                <el-tab-pane label="审核中" name="third">Role</el-tab-pane>
                <el-tab-pane label="未通过" name="fourth">Task</el-tab-pane>
              </el-tabs>
        </div>
    </div>
</template>

<script setup>

import { deleteArticleById, getArticleByCategory, getArticleByType } from '@/api/article';
import { ElMessage, ElMessageBox } from 'element-plus';
import { onMounted, ref } from 'vue';

import { useRouter } from 'vue-router';
import { useArticleStore } from '@/stores/article';
import ArticleItem from '../../item/article-item.vue';

const activeName = ref('first')
const router= useRouter()
const articleStore= useArticleStore()
onMounted(()=>{
   init()
})
function  init(){
  getArticleByType("1",1,4).then(res=>{
    if(res.code===200){
    articleStore.personalArticleList=res.data.records
    }
  })
}
function handleClick(tabs,event){
  
}
</script>

<style lang="scss" scoped>

</style>
