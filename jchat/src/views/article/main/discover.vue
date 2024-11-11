<template>
    <div class="article-head h-auto w-full mt-2 flex items-center flex-wrap">
        <button class="py-1 px-2 mr-4  w-[50px] mb-2 text-center flex-nowrap whitespace-nowrap  bg-blushing-gray hover:opacity-40 rounded-md" @click="handleArticleList(item.id)" v-for="(item,index) in categoryStore.categoryList" :key="index">
                {{ item.categoryName }}
        </button>
    </div>
    <div class="article-list">
        <div
        class="w-full "
        v-for="(item,index) in articleStore.homeArticleList" :key="index">
            <ArticleItem :item="item" :index="index"/>
        </div>
    </div>
    <el-pagination layout="prev, pager, next" :total="articleStore.page.pages*10" />
     
</template>

<script setup>
import { useArticleStore } from '@/stores/article';
import ArticleItem from '../item/index.vue';
import { onMounted, ref } from 'vue';
import { useCategoryStore } from '@/stores/category';
const categoryStore= useCategoryStore()
const articleStore= useArticleStore()
onMounted(()=>{
})
function handleArticleList(id){
}
</script>

<style  scoped>

</style>