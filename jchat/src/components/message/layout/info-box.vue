<template>
    <button 
    class="flex info-box w-full p-1 h-[45px] rounded-md shadow-md" style="background-color:var(--theme-background-color)">
    <div class=" p-[20px] mr-2 bg-cover bg-center" :style="{backgroundImage:'url('+message.avatar+')'}">
    </div>
    <div class="h-[40px] w-full">
        <div class="h-[20px] flex w-full">
            <div class="h-[20px] w-auto   text-md">
                {{ message.name }}
            </div>
            <div class="h-[20px] ml-auto text-sm">
                {{ message.time }}
            </div>
        </div>
        <div class="h-[20px] text-left text-sm">
            {{  message.content }}
        </div>
    </div>
    </button>
</template>

<script setup>
defineProps({
    message:Object,
})

</script>

<style lang="scss" scoped>

</style>