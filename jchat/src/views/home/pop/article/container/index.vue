<template>
<Article>
    <template #content>
        <div class="content h-full w-full overflow-auto" style=" scrollbar-width: none;-ms-overflow-style: none; ">
            <div class="h-[200vh] bg-slate-700"></div>
            <div class="h-[200vh] bg-gray-950"></div>
        </div>
    </template>
    <template #media>
        <div class="media w-full h-full">
            <div class="h-full flex  items-center justify-center w-full" style="background-color: var(--theme-background-color);">
                <div class="w-full   h-[240px]">
                    <div v-if="loading" class="rounded-md shadow-md h-full w-full bg-cover bg-center" :style="{backgroundImage:'url('+imageList[0]+')'}"></div>
                    <div class="rounded-md shadow-md overflow-hidden" v-else>
                        <el-carousel  height="240px">
                            <el-carousel-item  v-for="item in imageList" :key="item.id">
                                <div class="h-[240px] w-full bg-cover bg-center" :style="{backgroundImage:'url('+item+')'}"></div>
                            </el-carousel-item>
                          </el-carousel>
                    </div>
                </div>
            </div>
        </div>
    </template>
</Article>
</template>
 

<script setup>
import Article from '@/components/layout/article/index.vue';
import { useArticleStore } from '@/stores/article';
import { useSettingStore } from '@/stores/setting';
import { onMounted, ref, watch } from 'vue';
const settingStore= useSettingStore()
const articleStore= useArticleStore()
const loading=ref(true)
 const props= defineProps({
    id:String
})
const imageList=ref([])
onMounted(()=>{
    setTimeout(()=>{
        loading.value=false
    },700)
    for(let item of articleStore.articleList){
        if(item.id===props.id){
            imageList.value=item.articleImage
        }
    }
})
watch(()=>settingStore.getIsArticleActive(),(cur,pre)=>{
    if(!cur)loading.value=true
})
</script>

<style  >

</style>