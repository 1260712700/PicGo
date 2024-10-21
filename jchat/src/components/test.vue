<!-- 子组件 ChildComponent.vue -->
<template>
    <div >
        <slot name="header">
        </slot>
        <slot name="container"></slot>
        <slot name="foot" >
        </slot>
    </div>

</template>
 
<script setup>

</script>