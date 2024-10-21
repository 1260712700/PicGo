<template>
    <div>
        <el-drawer
        size="45%"
        v-model="settingStore.mobileNavigationSwitch"
        title="导航"
        :direction="direction"
      >
      <HeadNavigation :mode="mode"/>
      </el-drawer>
    
    </div>
</template>

<script setup>
import { useSettingStore } from '@/stores/setting';
import { onMounted } from 'vue';
import HeadNavigation from './head-navigation.vue';
const settingStore= useSettingStore()
defineProps({
    mode:String,
    direction:String
})
onMounted(()=>{
})
</script>

<style lang="scss" scoped>

</style>