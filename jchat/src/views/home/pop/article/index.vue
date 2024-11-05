<template>
    <div class="article-link">
        <div v-show="settingStore.getIsArticleActive()" class=" fixed top-0 left-0 h-screen w-screen z-30" @click="backToHome"></div>
        <router-view  v-slot="{ Component }">
          <transition 
          @before-enter="el=>setInitialState(el)"
          @before-leave="el=>setBeforeLeaveState(el)"
          >
            <component :is="Component" />
          </transition>
        </router-view>
    </div>
   
</template>

<script setup>
import { useSettingStore } from "@/stores/setting";
import { useRouter } from "vue-router";
const router=useRouter()
function backToHome(){
settingStore.setIsArticleActive(false)
  router.push('/')
}
const settingStore=useSettingStore()
function setInitialState(el){
    const box=settingStore.getBoxRect()
    const scrollbarTop=settingStore.scrollTopGetter().value
    el.style.position='absolute'
        el.style.left=box.left+'px'
        el.style.top=box.top+scrollbarTop+'px'
        el.style.height=box.height+'px'
        el.style.width=box.width+'px'
        el.style.transition='all .7s'
        el.style.zIndex='50'
        requestAnimationFrame(()=>{
          const targetX=window.innerWidth/2-(window.innerWidth*0.6)/2-box.left
          const targetY=window.innerHeight/2-(window.innerHeight*0.8)/2-box.top
        el.style.height='80vh'
        el.style.width='70vw'
      el.style.transform = `translate(${targetX}px, ${targetY}px)`;
        })
}
function setBeforeLeaveState(el){
  const box=settingStore.getBoxRect()
  el.style.position='absolute'
  const scrollbarTop=settingStore.scrollTopGetter().value
             el.style.transform='scale(1)'
             el.style.transition='all .7s'
        requestAnimationFrame(()=>{
            el.style.left=box.left+'px'
            el.style.top=box.top+scrollbarTop+'px'
            el.style.height=box.height+'px'
            el.style.width=box.width+'px'
        })
}
</script>

<style lang="scss" scoped>

</style>