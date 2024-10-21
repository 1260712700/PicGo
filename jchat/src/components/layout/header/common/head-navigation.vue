<template>
  <el-menu class="menu" router :ellipsis="false"  :mode="mode" :popper-offset="16">
    <el-menu-item index="/">
      <el-icon>
        <House />
      </el-icon>
      首页
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>
        <el-icon>
          <Files />
        </el-icon>归档</template>
      <el-menu-item index="/time-lile"><el-icon>
          <Timer />
        </el-icon>时间轴</el-menu-item>
      <el-menu-item index="/time-line"><el-icon>
          <Notebook />
        </el-icon>我的文章分类</el-menu-item>
      <el-menu-item index="/tag"><el-icon>
          <CollectionTag />
        </el-icon>标签</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="3" :popper-offset="8">
      <template #title>
        <el-icon>
          <IceCreamRound />
        </el-icon>摸鱼</template>
      <el-menu-item index="3-1"><el-icon>
          <Edit />
        </el-icon>留言区</el-menu-item>
      <el-menu-item index="3-2"><el-icon>
          <DeleteFilled />
        </el-icon>树洞</el-menu-item>
      <el-menu-item index="3-3"><el-icon>
          <Aim />
        </el-icon>小游戏</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="/music"><el-icon>
        <Headset />
      </el-icon>
      音乐</el-menu-item>
    <el-menu-item index="/about"><el-icon>
        <UserFilled />
      </el-icon>
      关于</el-menu-item>
  </el-menu>
</template>

<script setup>
import { onMounted } from 'vue';
 defineProps({
  mode:String// vertical || horizontal 
})
onMounted(()=>{
  
})
</script>

<style>
.menu{
  @apply h-[45px] bg-transparent outline-none;
}

</style>