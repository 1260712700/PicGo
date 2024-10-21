<template>
    <div class="mobile-setting-button flex items-center justify-center">
        <el-icon :size="25" @click="openMobileNavigation"><Menu /></el-icon>
    </div>

</template>

<script setup>
import { useSettingStore } from '@/stores/setting';

const settingStore= useSettingStore()
function openMobileNavigation(){
    settingStore.mobileNavigationSwitch=true
}
</script>

<style lang="scss" scoped>

</style>