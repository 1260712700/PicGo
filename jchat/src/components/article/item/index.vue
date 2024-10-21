<template>
    <div :ref="el => setArticleRef(el)"
      @mouseenter="enLargeImage" @mouseleave="reduceImage"
      @click="articleActive(item.id)"
      class="article-box  shadow-lg   rounded-md mx-[8px] my-[16px] w-full h-[240px]">
      <div  class="pc w-full flex">
        <div class="left w-[50%]">
          <Content v-if="direction" />
        <Image :ref="el=>setImageRef(el)" v-else :bg-cover="bgCover" :loading="loading"/>

        </div>
        <div class="right w-[50%]">
        <Image :ref="el=>setImageRef(el)" v-if="direction" :bg-cover="bgCover" :loading="loading"/>
        <Content v-else/>
          <div>
          </div>
  
        </div>
      </div>
  
    </div>
  
  </template>
  
  <script setup>
  import { computed, onBeforeMount, onMounted, ref } from 'vue';
  import { useSettingStore } from '@/stores/setting';
  import { useRouter } from 'vue-router';
import { lazyLoading } from '@/utils/vLazy';
import Image from './little/image.vue';
import Content from './little/content.vue';
  const loading=ref(true)
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
 
  const settingStore = useSettingStore()
  const router = useRouter()
  // 跳转到文章详情
  function articleActive(id) {
  // 绑定盒子的尺寸信息
  const articleRect= articleRef.value.getBoundingClientRect()
  settingStore.setBoxRect(articleRect)

  console.log(settingStore.getBoxRect());
  
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