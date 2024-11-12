<template>
    <div :ref="el => setArticleRef(el)"
      @click="articleActive(item.id)"
      @mouseenter="enLargeImage" @mouseleave="reduceImage"
      class="article-box  shadow-lg h-full  rounded-md my-[16px]">
      <div class="pc h-[240px]   w-full flex">
        <div class="left h-[240px] w-[50%]">
          <Content :item="item" v-if="direction" />
        <Image :ref="el=>setImageRef(el)" v-else :item="item" />
        </div>
        <div class="right h-[240px] w-[50%]">
        <Image :ref="el=>setImageRef(el)" v-if="direction" :item="item" />
        <Content :item="item" v-else/>
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
  const props= defineProps({
  item:Object,
  index:Number
  })
   const direction=computed(()=>{
      return props.index%2==0?true:false
   })
  onMounted(()=>{
  })
  
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