<template>
    <div class="all-article">
        <div class="w-full text-right text-sm">共有{{articleStore.personalArticleList.length}}篇文章</div>
        <div class="article"  v-for="(item,index) in articleStore.personalArticleList" :key="index">
        <ArticleItem :item="item"/>
        </div>
        <el-pagination  :hide-on-single-page="noData" background  :page-sizes="[5, 10, 20]" :default-page-size="5"
        @size-change="handleSizeChange"
@current-change="handleCurrentChange"
      layout="sizes, prev, pager, next" :total="articleStore.page.total" />
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useArticleStore } from '@/stores/article';
import ArticleItem from '@/views/article/item/personal-article-item.vue';

const pageNum=ref(1)//第几页
const pageSize=ref(5)//页面展示多少条数据
const noData=ref(false)
const articleStore= useArticleStore()
onMounted(()=>{
   init()
})
function  init(){
  articleStore.getArticle("personal","1",1,5)
}
const handleSizeChange = (val) => {
    pageSize.value=val
    handleArticleList()
}
const handleCurrentChange = (val) => {
    pageNum.value=val
    handleArticleList()
}
function handleArticleList(){
    articleStore.getArticle("personal","1",pageNum.value,pageSize.value)
}
</script>

<style lang="scss" scoped>

</style>