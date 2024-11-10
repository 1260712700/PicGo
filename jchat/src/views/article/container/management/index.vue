<template>
  <div class="management">
        <div class="head">
            <el-tabs v-model="activeName" tab-position="left" @tab-click="handleClick">
                <el-tab-pane label="全部笔记" name="first">
                    <div class="w-full text-right text-sm">共有{{articleStore.articleList.length}}篇文章</div>
                    <div class="article"  v-for="(item,index) in articleStore.articleList" :key="index">
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

import { deleteArticleById } from '@/api/article';
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
   articleStore.getArticleList("1")
}
function handleUpdate(article){
    articleStore.isPublish=true
    articleStore.isUpdate=true
    articleStore.saveArticle=article
    router.push('/article/publish')
}
function handleDelete(id){
    ElMessageBox.confirm(
        '确定删除该文章吗',
        '删除文章',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ) .then(() => {
        deleteArticleById(id).then(res=>{
        if(res.code==200){
        ElMessage.success("删除成功")
        init()
        }
        })
    })
    .catch(() => {
    
    })
}
function handleClick(tabs,event){
    
}
</script>

<style lang="scss" scoped>

</style>
