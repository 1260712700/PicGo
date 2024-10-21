<template>

    <div 
    class=" article-box z-30 flex  rounded-md  shadow-md bg-white ">
        <slot name="content"></slot>
        <slot name="media"></slot>
</div>  

</template>
 

<script setup>
import { useRouter } from 'vue-router';

const router= useRouter()
function backToHome(){
  router.push('/')
}
</script>

<style  scoped>

</style>