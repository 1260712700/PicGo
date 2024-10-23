<template>
    <div class="message-box flex">
        <div class="left h-[85vh]  border-r overflow-auto w-[25%]" style=" 
        scrollbar-width: none;-ms-overflow-style: none; border-color:var(--theme-background-color)">
            <slot name="userList"></slot>
        </div>
        <div class="right h-full overflow-hidden w-[75%]"
        style=" scrollbar-width: none;-ms-overflow-style: none; ">
            <slot name="messageBox"></slot>
        </div>
    </div>
</template>

<script setup>

</script>

<style>

</style>