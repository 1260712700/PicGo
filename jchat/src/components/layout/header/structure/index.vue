<template>
    <Header>
        <template #left>
        <div class=" hidden lg:w-full lg:flex justify-center items-center  h-[45px] ">
            <head-logo/>
            <HeadSearch/>
            <!-- <headNavigation class="h-[45px]" :mode="mode"/> -->
        </div>
        <div class="w-full  flex items-center h-[45px] lg:hidden">
            <HeadNavigationMobileButton/>
            <HeadSearch/>
        </div>
        </template>
        <template #right>
            <div class="flex w-auto space-x-5 items-center ">
                <div class="email">
                    <EmailButton/>
                </div>
                <div class="darkButton w-auto h-auto flex justify-center items-center">
                    <DarkModeButton />
                </div>
                <head-user-info/>
            </div>
        </template>
    </Header>
</template>

<script setup>

import Header from '../layout/index.vue';
import { ref } from "vue";

import headNavigation from "../common/head-navigation.vue";
import headLogo from "../common/head-logo.vue";
import DarkModeButton from "../little/dark-mode-button.vue";
import HeadUserInfo from "../little/head-user-info.vue";
import HeadSearch from '../common/head-search.vue';
import HeadNavigationMobileButton from '../common/head-navigation-mobile-button.vue';
import EmailButton from '../little/message.vue';

const mode=ref('horizontal')//菜单展示形式 横排展示
</script>

<style  scoped>
.email:hover{
    animation: jump 0.7s ease-out;
    
}
.darkButton:hover{
    animation: jump 0.7s ease-out;
}

</style>