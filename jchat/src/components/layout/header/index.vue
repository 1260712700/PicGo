<template>
    <transition name="header-change">
    <div class="head-container" v-show="showHeader">
        <div class="left">
        <div class="  w-full flex justify-center items-center  h-[45px] ">
            <slot name="left"></slot>
        </div>
        </div>
        <div class="mid">
            <slot name="mid"></slot>
        </div>
        <div class="right">
            <div class="flex  space-x-5 justify-center items-center ">
            <slot name="right"></slot>
        </div>
        </div>
    </div>
  
</transition>
  
</template>

<script setup>

import { useSettingStore } from '@/stores/setting';
import { onMounted, ref, watch } from 'vue';
const showHeader=ref(true)
const settingStore= useSettingStore()

onMounted(()=>{
    window.addEventListener('resize',()=>{
    settingStore.screenWidthSetter(window.innerWidth)
    settingStore.screenHeightSetter(window.innerHeight)
    })
    window.addEventListener('scroll',()=>{
        resize()
    })
})
function resize(){
    settingStore.scrollTopSetter(window.scrollY)
}
watch(settingStore.mouseYGetter(),(cur,pre)=>{
  showHeader.value=(cur<=45)?true:false
})
watch(settingStore.scrollTopGetter(),(cur,pre)=>{
    if(cur>=45)showHeader.value=false
    if(cur<pre)showHeader.value=true
})
</script>

<style  scoped>
.head-container{
    @apply  items-center backdrop-blur-md flex z-50 fixed top-0  w-screen px-4 rounded-b-3xl h-[45px]
}
.left{
    @apply h-[45px] flex w-auto;
}
.mid{
    @apply  w-full mx-auto h-[45px] items-center justify-center flex ;
}
.right{
    @apply h-[45px] space-x-4  items-center  flex w-auto;
}
</style>