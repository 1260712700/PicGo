<template>
    <div class="article-head h-[38px] w-full  rounded-md flex items-center  pl-3 space-x-4 shadow-md">
        <div  @click="chooseCategory(index)"  v-for="(item,index) in categoryStore.category" :key="index">
            <button :ref="el=>setCategory(el,index)" class="category-button">
                {{ item.categoryName }}
            </button>
        </div>
    </div>
    <div class="article-list">
        <div
        class="w-full "
        v-for="(item,index) in articleList" :key="index">
            <ArticleItem :item="item" :index="index"/>
        </div>
    </div>
      
     
</template>

<script setup>
import { useArticleStore } from '@/stores/article';
import ArticleItem from '../item/index.vue';
import { onMounted, ref } from 'vue';
import { useCategoryStore } from '@/stores/category';
const categoryStore= useCategoryStore()
const articleStore= useArticleStore()
const articleList=ref('')
onMounted(()=>{
    categoryStore.getInfo()
    setTimeout(() => {
        categoryRefList.value[0].className='category-is-choose'
    }, 100);
    articleList.value=articleStore.articleList
})
const categoryRefList=ref([])
function setCategory(el,index){
    categoryRefList.value[index]=el
}

const categoryIndex=ref(0)
function chooseCategory(index){
    categoryRefList.value[categoryIndex.value].className='category-button'
    categoryIndex.value=index
       categoryRefList.value[categoryIndex.value].className='category-is-choose'
}
</script>

<style  scoped>
.category-button{
    opacity: 1;
}
.category-button:hover{
    opacity: 0.55;
}
.category-is-choose{
    opacity: 0.55;
}
</style>