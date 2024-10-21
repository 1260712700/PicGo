<template>
    <div class="home-container">
      <Images/>
      <Brand/>
      <Main/>
      </div>
      <div class="articleDetail">
        <router-view  v-slot="{ Component }">
          <transition 
          @before-enter="el=>setInitialState(el)"
          @before-leave="el=>setBeforeLeaveState(el)"
          >
            <component :is="Component" />
          </transition>
        </router-view>
      </div>
   
      <div class="friend-container">
        
      </div>
</template>

<script setup>
import Images from "@/views/Home/image/index.vue";
import Brand from "@/views/Home/brand/index.vue";
import Main from "@/views/Home/main/index.vue";
import { useSettingStore } from "@/stores/setting";
const settingStore=useSettingStore()
function setInitialState(el){
  const box=settingStore.getBoxRect()
  el.style.position='absolute'
        el.style.top=box.top+'px'
        el.style.left=box.left+'px'
        el.style.height=box.height+'px'
        el.style.width=box.width+'px'
        el.style.transform='scale(1)'
        el.style.transition='all .7s'
        requestAnimationFrame(()=>{
          const targetX=window.innerWidth/2-(window.innerWidth*0.6)/2-box.left
          const targetY=window.innerHeight/2-(window.innerHeight*0.8)/2-box.top
       
        // el.style.transform = `translate(${targetX}px, ${targetY}px)`;
      el.style.height='80vh'
        el.style.width='60vw'
        })
}
function setBeforeLeaveState(el){
  const box=settingStore.getBoxRect()
  el.style.position='absolute'
             el.style.transform='scale(1)'
             el.style.transition='all .7s'
        requestAnimationFrame(()=>{
            el.style.left=box.left+'px'
            el.style.top=box.top+'px'
            el.style.height=box.height+'px'
            el.style.width=box.width+'px'
        })
}
</script>

<style lang="scss" scoped>

</style>