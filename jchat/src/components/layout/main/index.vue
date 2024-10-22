<template>
    <div class="main-box h-auto w-screen ">
        <div class="main-container ">
            <div class="left w-full  rounded-md  lg:w-[75%] h-full ">
                <div class="container w-full mx-auto">
                    <slot name="content"></slot>
                </div>
            </div>
            <div class="right w-0 lg:w-[25%] h-auto overflow-y-hidden">
                <div class="sideBar">
                    <slot name="information">
                    </slot>
                </div>
            </div>
        </div>
    
    </div>
</template>

<script setup>
defineOptions({
    name:'MainBox'
}) 

</script>

<style  scoped>
.main-box{
    @apply transition-colors duration-700  pt-5;
    background-color: var(--theme-background-color);
}
.main-container {
    @apply overflow-x-hidden lg:space-x-5 w-full mx-auto flex h-full md:w-[80%] max-w-[1500px] ;
}
.left{
    @apply transition-colors  duration-700  ;
}
.right{
    @apply transition-colors duration-700  ;
}
</style>