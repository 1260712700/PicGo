<template>
    <div class="welcome h-screen flex w-screen">
        <div class="left w-full shadow-2xl h-full">
            <slot name="left"></slot>
        </div>
        <div class="right w-[400px] h-full">
            <slot name="right"></slot>
        </div>
    </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>

</style>