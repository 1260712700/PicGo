<template>
    <Welcome>
        <template #left>
            <div class="img h-full w-full -z-10"></div>
        </template>
        <template #right>
            <router-view v-slot="{Component}">
                <transition name="el-fade-in-linear" mode="out-in">
                  <component :is="Component"/>
                </transition>
              </router-view>
        </template>
    </Welcome>
   
</template>

<script setup>
import Welcome from '@/components/layout/welcome/index.vue';


</script>

<style  scoped>
.img{
    @apply bg-cover bg-center;
    background-image: url('/bg/2/beauty.jpg');
}
</style>