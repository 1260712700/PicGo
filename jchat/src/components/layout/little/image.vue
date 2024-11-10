<template>
    <div class="h-full w-full">
        <div class="z-10 h-full w-full">
            <slot name="image"></slot>
        </div>
        <div class="z-20 top-0 absolute">
            <slot name="cover"></slot>
        </div>
    </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>

</style>