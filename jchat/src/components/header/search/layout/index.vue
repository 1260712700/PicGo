<template>
    <div   class=" search-area h-[30px] w-[80%]">
        <div class="search-input w-full">
            <slot name="search-input"></slot>
        </div>
        <div class="history w-full  h-auto">
                <slot name="history"></slot>
           
        </div>
    </div>
</template>

<script setup>

</script>

<style  scoped>

</style>