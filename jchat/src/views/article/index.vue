<template>
   
<Article>
    <template #content>
        1234
    </template>
    <template #media>
1234
    </template>
</Article>
</template>
 

<script setup>
import Article from '@/components/layout/article/index.vue';
import { onMounted } from 'vue';

 const props= defineProps({
    id:String
})
onMounted(()=>{
})
</script>

<style  scoped>

</style>