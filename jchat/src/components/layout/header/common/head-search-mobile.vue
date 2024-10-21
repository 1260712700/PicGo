<template>
    <div class="mobile-search-area min-w-[210px] max-w-[500px] flex justify-center items-center">
        <div  @click="focusSearch" class="mx-5" >
            <el-input @blur="blurSearch" v-model="content">
                <template #suffix>
                    <img @click="search" :src="constantStore.svg.worldSearch">
                </template>
            </el-input>
        </div>
    </div>
</template>
<!-- TODO 记得做搜索 -->
<script setup>
import { useConstantStore } from '@/stores/constant';
import { ref } from 'vue';
const constantStore= useConstantStore()
const content=ref('')
const focusSearch=()=>{
}
const blurSearch=()=>{
     content.value=''
}
function search(){
    
}
</script>

<style>

</style>