<template>
    <div>
        <transition name="header-change">
        <Header v-show="showHeader"/>
        </transition>
    </div>
</template>

<script setup>
import { useSettingStore } from '@/stores/setting';
import Header from './structure/index.vue';
import { onMounted, ref, watch } from 'vue';
const showHeader=ref(true)
const settingStore= useSettingStore()
onMounted(()=>{
    // window.addEventListener('mousemove',(e)=>{
    //     settingStore.mouseXSetter(e.pageX)
    //     settingStore.mouseYSetter(e.pageY)
    // })
    // window.addEventListener('touchmove',(e)=>{
    //     settingStore.mouseXSetter(e.pageX)
    //     settingStore.mouseYSetter(e.pageY)
    // })
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

<style lang="scss" scoped>

</style>