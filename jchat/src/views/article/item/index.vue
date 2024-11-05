<template>
    <div :ref="el => setArticleRef(el)"
      @click="articleActive(item.id)"
      @mouseenter="enLargeImage" @mouseleave="reduceImage"
      class="article-box  shadow-lg h-full  rounded-md my-[16px]">
      <div v-if="isPC" class="pc h-[240px]   w-full flex">
        <div class="left h-[240px] w-[50%]">
          <Content v-if="direction" />
        <Image :ref="el=>setImageRef(el)" v-else :bg-cover="bgCover" :loading="loading"/>
        </div>
        <div class="right h-[240px] w-[50%]">
        <Image :ref="el=>setImageRef(el)" v-if="direction" :bg-cover="bgCover" :loading="loading"/>
        <Content v-else/>
          <div>
          </div>
        </div>
      </div>
      <div v-else class="mobile  h-[400px] w-full">
        <div class="top h-[200px] w-full">
        <Image :ref="el=>setImageRef(el)" :bg-cover="bgCover" :loading="loading"/>
        </div>
        <div class="bottom  h-[200px] w-full">
        <Content />
          <div>
          </div>
        </div>
      </div> 
  
    </div>
  
  </template>
  
  <script setup>
  import { computed, onBeforeMount, onMounted, ref, watch } from 'vue';
  import { useSettingStore } from '@/stores/setting';
  import { useRouter } from 'vue-router';
import { lazyLoading } from '@/utils/vLazy';
import Image from '../little/image.vue';
import Content from '../little/content.vue';
const settingStore= useSettingStore()
  const loading=ref(true)
  const isPC=computed(()=>{
    return settingStore.screenWidthGetter().value>762;
  })
  const props= defineProps({
  item:Object,
  index:Number
  })
   const direction=computed(()=>{
      return props.index%2==0?true:false
   })
  const bgCover=ref('')
  onMounted(()=>{
    init()
  })
  function init(){
    // 懒加载
    lazyLoading(articleRef.value,()=>{
      setTimeout(()=>{
        loading.value=false
          bgCover.value=props.item.articleCover
      },4000)
    })
  }
  // 加载图片
 
  const router = useRouter()
  // 跳转到文章详情
  function articleActive(id) {
    if(loading.value){
      return 
    }
  // 绑定盒子的尺寸信息
  const articleRect= articleRef.value.getBoundingClientRect()
  settingStore.setBoxRect(articleRect)
  settingStore.setIsArticleActive(true)
  router.push('/article/'+id)
    
  }
  // 放大图片调用imageRef子组件
  function enLargeImage(){
    imageRef.value.enLargeArticleCover()
  }
  function reduceImage(){
    imageRef.value.reduceArticleCover()
  }
  const imageRef=ref('')
  function setImageRef(el){
    imageRef.value=el
  }
  const articleRef=ref([])
  function setArticleRef(el){
  articleRef.value=el
  }

  </script>
  
  <style lang="scss" scoped>
  
  </style>