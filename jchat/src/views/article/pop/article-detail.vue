<template>
    <!-- 文章详情 -->
    <el-dialog
    v-model="centerDialogVisible"
    :show-close="false"
    align-center
  >
    <div class="h-full w-full">
      <Article>
        <template #content>
            <div class="header fixed w-[50%] p-[24px]" style="background-color:var(--el-bg-color)">
                <div class="user-info items-center w-full h-auto flex">
                    <div class="avatar h-[40px]">
                        <el-avatar :src="avatar"/>
                    </div>
                    <div class="name ml-3 text-base "> 
                        {{ nickname }}
                    </div>
                        <el-button class="ml-auto w-[100px] h-[40px] rounded-md" type="primary">关注</el-button>
                   </div>
            </div>
            <div class="article-container w-full mt-[88px] no-scrollbar overflow-auto px-4 pb-3">
                <div class="title text-xl mb-2">
                    {{ dialogArticle.title }}
                </div>
                <div class="content text-base">
                    {{ dialogArticle.content }}
                </div>
                <div class="create-time mt-[12px]">
                    发布时间：{{ dialogArticle.createTime }}
                </div>
                <el-divider/>
            </div>
            <div class="comment-area">
                <!-- TODO 评论区 -->
            </div>
        </template>
        <template #media>
            <div class="h-full flex items-center w-full">
                <el-carousel  height="90vh" class="w-full">
                    <el-carousel-item v-for="(item, index) in imageList" :key="index">
                      <img
                        :src="item"
                        class="hardware-speedup carousel-image"
                      />
                    </el-carousel-item>
                  </el-carousel>
            </div>
        </template>
      </Article>
    </div>
  </el-dialog>
</template>

<script setup>
import { computed, ref, watch } from 'vue';
import { useUserStore } from '@/stores/user';
import Article from '@/components/layout/article/index.vue';

const avatar=ref(null)
const nickname=ref('')
const userStore= useUserStore()

const props=defineProps({
    dialogArticle:Object,
    
})

const imageList=computed(()=>{
    let list=[]
    if (props.dialogArticle.images) {
        list= props.dialogArticle.images.split(',')
    list.length = list.length - 1
  }
    return list==[]?[]:list
});
const centerDialogVisible = ref(false)//对话框展示

watch(()=>userStore.targetUserInfo,(cur)=>{
    avatar.value=cur.avatar
    nickname.value=cur.nickname
    centerDialogVisible.value=true
    
})

</script>

<style lang="scss" scoped>

</style>