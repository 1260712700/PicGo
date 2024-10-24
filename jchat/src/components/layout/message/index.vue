<template>
    <div class="message-box h-full w-full flex">
        <div class="left h-full border-r-2 overflow-auto w-[25%]" style=" 
        scrollbar-width: none;-ms-overflow-style: none; border-color:var(--theme-background-color)">
            <slot name="userList"></slot>
        </div>
        <div class="right h-full overflow-hidden w-[75%]"
        style=" scrollbar-width: none;-ms-overflow-style: none; ">
        <div class="h-[70%] relative overflow-auto w-full border-b-2"
        style=" scrollbar-width: none;-ms-overflow-style: none; border-color:var(--theme-background-color) ">
            <slot name="messageBox"></slot>
        </div>
        <div class="input-box  h-[30%] overflow-hidden w-full"
        style="scrollbar-width: none;-ms-overflow-style: none; ">
            <slot name="inputBox" ></slot>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style>

</style>