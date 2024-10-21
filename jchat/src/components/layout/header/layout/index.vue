<template>

    <div class="head-container">
        <div class="left">
            <slot name="left"></slot>
        </div>
        <div class="right">
            <slot name=right></slot>
        </div>
    </div>
  
  
</template>

<script setup>

</script>

<style  scoped>
.head-container{
    @apply  items-center backdrop-blur-md flex z-50 fixed top-0  w-screen px-4 rounded-b-3xl h-[45px]
}
.left{
    @apply h-[45px] flex w-auto;
}
.right{
    @apply h-[45px] space-x-4 ml-auto  items-center  flex w-auto;
}
</style>