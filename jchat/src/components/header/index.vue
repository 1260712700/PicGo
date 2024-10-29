<template>
    <Header >
        <template #left>
          <HeadLogo/>
        </template>
        <template #mid>
            <HeadSearch/>
        </template>
        <template #right>
                    <Message/>
                    <DarkModeButton/>
                    <UserInfo/>
        </template>
    </Header>
</template>

<script setup>

import Header from '../layout/header/index.vue';
import DarkModeButton from './common/dark-mode-button.vue';
import HeadLogo from './common/head-logo.vue';
import HeadSearch from './search/index.vue';
import Message from './common/message.vue';
import UserInfo from './common/user-info.vue';
</script>

<style  scoped>

</style>