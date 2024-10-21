<template>
    <div class=" space-y-5 h-auto">
        <Clock/>
        <Notice/>
        <UserInfo/>
        <Calender/>
        <WebsiteNews/>
    </div>
</template>

<script setup>
import Calender from './common/calender.vue';
import Clock from './common/clock.vue';
import Notice from './common/notice.vue';
import UserInfo from './common/user-info.vue';
import WebsiteNews from './common/website-news.vue';

</script>

<style lang="scss" scoped>

</style>