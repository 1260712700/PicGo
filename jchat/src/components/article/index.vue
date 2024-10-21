<template>
    <div>
     
        <el-tabs type="border-card" 
        v-model="activeName"  @tab-click="handleClick">
          <el-tab-pane label="世界" name="first">
            <World/>
          </el-tab-pane>
          <el-tab-pane label="热门" name="second">
            <Hot/>
          </el-tab-pane>
          <el-tab-pane label="最新" name="third">
            <New/>
          </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script setup>
import World from './container/world.vue';
import New from './container/new.vue';
import Hot from './container/hot.vue';
import { ref } from 'vue'

const activeName = ref('second')

const handleClick = (tab,event) => {
}
</script>

<style lang="scss" scoped>

</style>