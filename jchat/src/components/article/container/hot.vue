<template>
        <div
        class="w-full "
        v-for="(item,index) in articleStore.articleList" :key="index">
            <ArticleItem :item="item" :index="index"/>
        </div>
     
</template>

<script setup>
import { useArticleStore } from '@/stores/article';
import ArticleItem from '../item/index.vue';
import { onMounted } from 'vue';

const articleStore= useArticleStore()
onMounted(()=>{
})
</script>

<style lang="scss" scoped>

</style>