<template>
  <div v-if="!articleStore.isUpdate" class="update-picture">
    <div v-if="articleStore.saveArticleCount > 0" class="flex">
      <div class="q text-sm h-[40px] p-[5px]">
        检测到你有已保存的文章，请问是否继续编辑?
      </div>
      <div class="ml-auto">
        <el-button type="primary" @click="continueUpdate">继续编辑</el-button>
        <el-button type="danger" @click="giveUp">放弃</el-button>
      </div>
    </div>
    <el-upload :limit="9" :http-request="upload" :before-upload="beforeUpload" :on-success="handleUploadSuccess"
      :on-error="handleUploadError" drag multiple>
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        拖拽到这或者 <em>点击上传</em>
      </div>
      <template #tip>
        <div class="el-upload__tip">
          jpg/png 小于100MB
        </div>
      </template>
    </el-upload>
  </div>
  <div v-else class="w-full mt-6 flex">
    <div class="edit w-full">
      <!-- 标题输入框 -->
      <div class="article-title">
        标题
        <el-input v-model="articleStore.saveArticle.title" maxlength="20" show-word-limit
          placeholder="请输入标题"></el-input>
      </div>

      <!-- 图片上传组件 -->
      <div>
        <el-upload :limit="9" :http-request="upload" :file-list="imageList" :before-upload="beforeUpload"
          :before-remove="beforeRemove" list-type="picture-card" :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove" :on-success="handleUploadSuccess" :on-error="handleUploadError" multiple>
          <template #default>
            <el-icon>
              <Plus />
            </el-icon>
          </template>

        </el-upload>

        <el-dialog v-model="dialogVisible" @close="dialogVisible = false">
          <img w-full :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>
      </div>

      <!-- 正文内容输入框 -->
      <div class="content">
        正文内容
        <el-input show-word-limit resize="none" maxlength="1500" type="textarea" :rows="5"
          v-model="articleStore.saveArticle.content" placeholder="请输入正文内容"></el-input>
      </div>
      <!-- 分类输入 -->
      <div class="category">

        <el-select-v2 v-model="articleStore.saveArticle.categoryId" :options="options" placeholder="请选择分类"
          style="width: 240px" />
      </div>

      <!-- 内容来源声明 -->
      <div class="articleType">
        内容来源声明
        <el-radio-group v-model="articleStore.saveArticle.articleType">
          <el-radio value="1" size="large">原创</el-radio>
          <el-radio value="2" size="large">转载</el-radio>
        </el-radio-group>
      </div>

      <!-- 文章可见范围 -->
      <div class="status">
        文章可见范围
        <el-radio-group v-model="articleStore.saveArticle.status">
          <el-radio value="1" size="large">公开</el-radio>
          <el-radio value="2" size="large">私密</el-radio>
        </el-radio-group>
      </div>

      <el-divider />

      <!-- 发布和保存按钮 -->
      <div>
        <el-button @click="publish" type="success">发布</el-button>
        <el-button v-if="!articleStore.isPublish" @click="save">保存</el-button>
      </div>
    </div>
    <div class="preview pl-[48px] w-[300px] h-full">
      <Phone />
    </div>
  </div>

</template>

<script setup>

import { computed, onMounted, onUnmounted, ref } from 'vue';
import { deleteArticleById, getCategory, publishArticle } from '@/api/article';
import { ElMessage } from 'element-plus';
import { useArticleStore } from '@/stores/article';
import { uploadImage } from '@/api/upload';
import Phone from '../../little/phone.vue';
import { useCategoryStore } from '@/stores/category';

// 使用状态管理
const articleStore = useArticleStore();
const dialogImageUrl = ref('');
const dialogVisible = ref(false);
const categoryStore= useCategoryStore()

const imageList = computed(() => {
  let list = [];
  if (articleStore.saveArticle.images) {
    list = articleStore.saveArticle.images.split(',')
    list.length = list.length - 1
  }
  return list != [] ?
    list.map((url, index) => ({
      uid: index.toString(),
      url: url,
      name: `image_${index}`,
    }))
    : [];
});
const options = ref([])
// 页面加载时初始化
onMounted(() => {
  init();
});
onUnmounted(() => {
    destroy()
  if (articleStore.isUpdate) {
    save()
  }
})
function destroy(){
  articleStore.clearArticle("save")
  articleStore.isUpdate = false;
  articleStore.isPublish=false;
}
function continueUpdate() {
  articleStore.isUpdate = true;
  articleStore.getArticle("save","0")
}
function giveUp() {
  deleteArticleById(articleStore.saveArticle.id).then(res => {
    if (res.code == 200) {
      ElMessage.success("已放弃编辑保存的文章")
      articleStore.getArticle("count","0");
      articleStore.clearArticle("save")
    }
  })
}
// 初始化方法
function init() {
   options.value = categoryStore.categoryList.map(category => ({
        value: category.id,   // 分类ID
        label: category.categoryName  // 分类名称
  }))
  articleStore.getArticle("count","0");
}
function beforeRemove(uploadFile, uploadFiles) {
  if (uploadFiles.length <= 1) {
    ElMessage.error("文章至少有一张图哦")
    return false;
  }
  return true;
}
// 删除图片的处理函数
const handleRemove = (uploadFile, uploadFiles) => {
  let images = ""
  for (let temp of uploadFiles) {
    images += temp.url + ","
  }
  // 更新 images 字符串
  articleStore.saveArticle.images = images
};

// 预览图片
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url;
  dialogVisible.value = true;
};
// 处理文章上传错误
function handleUploadError(err, file, fileList) {
  ElMessage.error(err.msg)
}
// 上传前检查
function beforeUpload(file) {
  const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
  const isLt500kb = file.size / 1024 / 1024 < 100;
  if (!isJPG) {
    ElMessage.error('上传文件只能是 JPG/PNG 格式!');
  }
  if (!isLt500kb) {
    ElMessage.error('上传文件大小不能超过 100MB!');
  }

  return isJPG && isLt500kb;
}
// 处理上传图片成功后的回调
const handleUploadSuccess = (response, file, fileList) => {
  articleStore.isUpdate = true;
  if (response.code === 200) {
    ElMessage.success('文件上传成功');
    // 创建新图片对象
    // 将新的图片 URL 添加到 images 字符串
    const newImages = articleStore.saveArticle.images
      ? `${articleStore.saveArticle.images}${response.data}`
      : response.data;
    articleStore.saveArticle.images = newImages;
  } else {
    ElMessage.error('文件上传失败');
  }
};
// 自定义上传方法
function upload(options) {
  const { file, onProgress, onSuccess, onError } = options;
  // 调用上传接口
  uploadImage(file, 'article')
    .then(res => {
      if (res.code === 200) {
        onSuccess(res);
      } else {
        onError(res);
      }
    })

}

// 生成 UUID
function generateUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    const r = Math.random() * 16 | 0;
    const v = c === 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}

// 发布文章
const publish = () => {
  
  if (!articleStore.saveArticle.title) {
    ElMessage.error('文章标题为空');
    return;
  }
  if (imageList.value.length <= 0) {
    ElMessage.error('你暂未放任何图片，至少整一张图片吧');
    return;
  }
  if (!articleStore.saveArticle.content) {
    ElMessage.error('文章正文为空哦');
    return;
  }
  if (!articleStore.saveArticle.categoryId) {
    ElMessage.error('请选择文章分类');
    return;
  }
  if(articleStore.saveArticle.status=='3'||articleStore.saveArticle.status=='4'||articleStore.saveArticle.status=='5'){
    ElMessage.error("请选择文章范围")
    return
  }
  publishArticle(articleStore.saveArticle)
    .then(res => {
      console.log(res);
      if (res.code === 200) {
        ElMessage.success('文章上传成功');
        destroy()
        articleStore.saveArticleCount = 0
      } 
    })
    .catch(err => {
      ElMessage.error(err);
    });
};

// 保存文章
const save = () => {
  articleStore.saveArticle.status = '3';
  publishArticle(articleStore.saveArticle)
    .then(res => {
      if (res.code === 200) {
        articleStore.saveArticle.status = '1';
        ElMessage.success('文章保存到草稿箱成功');
      } else {
        ElMessage.error(res.msg);
      }
    })
    .catch(err => {
      ElMessage.error(err);
    });
};
</script>

<style>
/* 样式定义 */
.el-icon--delete svg path {
  fill: white
}

.el-icon--zoom-in svg path {
  fill: white
}
</style>
