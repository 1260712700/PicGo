<template>
    <!-- 展示文章数据页面 -->
    <div class="article-area">
        <div class="article-head h-auto w-full mt-2 flex items-center flex-wrap">
            <button :class="{'opacity-40':currentIndex==index}" class="py-1 px-2 mr-4  w-[50px] mb-2 text-center flex-nowrap whitespace-nowrap  bg-blushing-gray hover:opacity-40 rounded-md"
             @click="handleCurrentIndex(item.id,index)" v-for="(item,index) in categoryStore.categoryList" :key="index">
                    {{ item.categoryName }}
            </button>
        </div>
        <!-- 展示数据 -->
         <!-- 无数据时展示 -->
         <transition name="el-zoom-in-top" tag="div">
            <div v-show="noData" class="no-data w-full h-[200px] space-x-2 text-lg text-red-600 flex items-center justify-center">
                <el-icon class=" text-lg" color="text-red-600"><WarningFilled /></el-icon>
                <div class="msg">
                    啊欧，没有任何数据哦
                </div>
         </div>
         </transition>
        <div class="article-list hardware-speedup" >
            <transition-group name="el-zoom-in-center" tag="div">
                <div
                    class="w-full"
                    v-for="(item, index) in articleStore.homeArticleList"
                    :key="item.id">
                    <ArticleItem @click="handleClick(item)" :item="item" :index="index"/>
                </div>
            </transition-group>
        </div>
        <!-- 分页 -->
        <div class="foot flex items-center justify-center">
            <el-pagination  :hide-on-single-page="noData" background  :page-sizes="[5, 10, 20]" :default-page-size="5"
              @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
            layout="sizes, prev, pager, next" :total="articleStore.page.total" />
        </div>
        <!-- 文章详细展示框 -->
        <ArticleDetail :dialog-article="dialogArticle"  />
    </div>
    
     
</template>
<script setup>

import { useArticleStore } from '@/stores/article';
import ArticleItem from '../item/home-article-item.vue';
import { computed, onMounted, onUnmounted, ref, watch } from 'vue';
import { useCategoryStore } from '@/stores/category';
import { useUserStore } from '@/stores/user';
import ArticleDetail from '../pop/article-detail.vue';
const userStore= useUserStore()
const categoryStore= useCategoryStore()
const articleStore= useArticleStore()
const currentIndex=ref(0);//按钮索引
const categoryId=ref(1);//分类id
const pageNum=ref(1)//第几页
const pageSize=ref(5)//页面展示多少条数据
const noData=ref(true)

const dialogArticle=ref({
    id:'',
        title: '',
        content: '',
        images: "",
        tags: '', // 添加标签字段
        articleType:"1",
        categoryId:"",
        status:"1",
        createTime:'',
        updateTime:''
});
onMounted(()=>{
    handleArticleList()
})
onUnmounted(()=>{
    currentIndex.value=0
})
// 处理分页
function handleCurrentIndex(id,index){
    categoryId.value=id;
    currentIndex.value=index
    handleArticleList();
}
const handleSizeChange = (val) => {
    pageSize.value=val
    handleArticleList()

}
const handleCurrentChange = (val) => {
    pageNum.value=val
    handleArticleList()
}
function handleArticleList(){
    articleStore.getArticle("home",categoryId.value,pageNum.value,pageSize.value)
}
// 查看是否有数据
watch(()=>articleStore.page.total,(cur)=>{
    if(cur<=0)noData.value=true
    else noData.value=false
})
// 点击文章
function handleClick(item){
    dialogArticle.value=item
    userStore.getInfoById(item.userId)
}


</script>

<style  >
/* 确保轮播图的图片填满轮播图的高度，保持比例并左右留白 */
.carousel-image {
    width: 100%;    /* 宽度占满容器 */
    height: 100%;   /* 高度填满轮播图容器 */
    object-fit: cover; /* 图片填充容器并裁剪 */
    object-position: center center; /* 图片居中显示 */
  }

</style>