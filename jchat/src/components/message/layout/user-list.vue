<template>
    <div v-for="(item,index) in userList" :key="index">
    <button 
    class="flex  info-box w-full p-1 h-[45px] rounded-md shadow-md" style="background-color:var(--theme-background-color)">
    <div class=" p-[20px] mr-2 bg-cover bg-center" :style="{backgroundImage:'url('+item.avatar+')'}">
    </div>
    <div class="h-[40px] w-full">
        <div class="h-[20px] flex w-full">
            <div class="h-[20px] w-auto text-sm" >
                {{ item.name }}
            </div>
            <div class="h-[20px] ml-auto text-xs" style="color: var(--theme-little-text-color);">
                {{ item.time }}
            </div>
        </div>
        <div class="h-[20px] text-left text-xs" style="color: var(--theme-little-text-color);">
            {{  item.content }}
        </div>
    </div>
    </button>
</div>

</template>

<script setup>
defineProps({
    userList:Array,
})

</script>

<style lang="scss" scoped>

</style>