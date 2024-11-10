<template>
    <div class="article-item mb-2 shadow-md rounded-md p-[16px] flex hover:bg-blushing-gray group">
        <!-- 文章封面展示 -->
       <div class="article-cover relative h-[160px] w-[120px]">
        <Image>
            <template #image>
                <div class="h-[160px] w-[120px]">
                    <img :src="item.articleCover" class=" rounded-md h-full w-full  object-cover" alt="">
                </div>
            </template>
            <template #cover>
                <div v-if="item.status==4||item.status==5" class="article-cover h-[160px] w-[120px]">
                   <button class="absolute flex space-x-1 items-center rounded-md p-1 bg-blushing-gray  text-red-500 text-xs left-2 bottom-2">
                    <el-icon color="red"><Stamp /></el-icon>
                    <div class="msg">审核中...</div>
                </button>
                </div>
                <div v-if="item.status==2" class="article-cover h-[160px] w-[120px]">
                    <button class="absolute flex space-x-1 items-center rounded-md p-1 bg-blushing-gray text-white text-xs left-2 bottom-2">
                        <el-icon color="white"><Lock /></el-icon>
                        <div class="msg">仅自己可见</div>
                    </button>
                 </div>
                 <div v-if="item.status==1" class="article-cover h-[160px] w-[120px]">
                    <button class="absolute flex space-x-1 items-center rounded-md p-1 bg-blushing-gray text-white text-xs left-2 bottom-2">
                        <el-icon color="white"><Unlock /></el-icon>
                    <div class="msg">
                        公开
                    </div></button>
                 </div>
            </template>
        </Image>
       </div>
        <!-- 数据展示 -->
        <div class="data-container relative mt-[1px] ml-[16px] w-full">
            <div class="title">
                {{ item.title }}
            </div>
            <div class="data mt-2 flex space-x-3">
               <div class="h-[15px] space-x-1 items-center text-xs flex">
                    <Eye/>
                <div>
                    {{ item.visitCount }}
                </div>
               </div> 
               <div class="h-[15px] space-x-1 items-center text-xs flex">
                <Comment/>
                <div>
                    {{ item.commentCount }}
                </div>
            </div>
                <div class="h-[15px] space-x-1 items-center text-xs flex">
                    <Like/>
                    <div>
                        {{ item.like }}
                    </div>
               </div> 
               <div class="h-[15px] space-x-1 items-center text-xs flex">
                <Collect/>
                <div>
                    {{ item.collectCount }}
                </div>
                <div class="h-[15px] space-x-1 items-center text-xs flex">
                    <Share/>
                    <div>
                        {{ item.shareCount }}
                    </div>
                </div>
           </div> 
            </div>
            <div class="create-time text-xs mt-2">
                上传时间：{{ item.createTime }}
            </div>
            <!-- 编辑按钮 -->
            <div class="absolute opacity-0 group-hover:opacity-100  bottom-2 flex space-x-2">
                <button @click="handleUpdate(item)" class=" hover:opacity-40 text-sm items-center space-x-1 flex h-[15px]">
                    <EditPen/>
                    <div>
                        编辑
                    </div>
                </button>
                <button @click="handleDelete(item.id)" class=" hover:opacity-40  text-sm items-center space-x-1 flex h-[15px]">
                    <el-icon color="var(--theme-icon-color)"><Delete /></el-icon>
                    <div>
                        删除
                    </div>
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>

import { deleteArticleById,  } from '@/api/article';
import Image from '@/components/layout/little/image.vue';
import Eye from '@/icon/eye.vue';
import { ElMessage, ElMessageBox } from 'element-plus';

import Like from '@/icon/like.vue';
import Collect from '@/icon/collect.vue';
import Share from '@/icon/share.vue';
import EditPen from '@/icon/editPen.vue';
import { useRouter } from 'vue-router';
import { useArticleStore } from '@/stores/article';
import Comment from '@/icon/comment.vue';
const router= useRouter()
const articleStore= useArticleStore()
const props=defineProps({
    item:Object
})
function handleUpdate(article){
    articleStore.isPublish=true
    articleStore.isUpdate=true
    articleStore.saveArticle=article
    router.push('/article/publish')
}
function handleDelete(id){
    ElMessageBox.confirm(
        '确定删除该文章吗',
        '删除文章',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ) .then(() => {
        deleteArticleById(id).then(res=>{
        if(res.code==200){
        ElMessage.success("删除成功")
        articleStore.getArticleList("1")
        }
        })
    })
    .catch(() => {
    
    })
}
</script>

<style lang="scss" scoped>

</style>