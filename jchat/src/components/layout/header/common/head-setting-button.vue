<template>
    <div class="min-h-[25px] min-w-[25px]">
        <el-tooltip
        class="box-item"
        effect="light"
        content="设置"
        placement="bottom"
      >
      <el-link href="/setting">
        <img  :class="settingClass"
        @mouseenter="settingEnter"  class=" object-contain" @mouseleave="settingLeave" :src="constant.svg.setting">
       
    </el-link>
      </el-tooltip>
     
           
    </div>
</template>

<script setup>
import { useConstantStore } from '@/stores/constant';
import { ref } from 'vue';

 const constant =useConstantStore()
 const settingClass=ref('')
 const settingEnter=()=>{
    settingClass.value='setting-active'
    
 }
 const settingLeave=()=>{
    settingClass.value='setting-normal'
 }
</script>

<style  scoped>
    .setting-active{
        animation:  rotate 1.5s linear infinite ;
        animation-fill-mode: forwards;
    }
    @keyframes rotate {
        from { transform: rotate(0deg); }
        to { transform: rotate(360deg); }
    }
    .setting-normal{
    }
</style>