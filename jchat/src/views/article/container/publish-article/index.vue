<template>
    <div>
        <div class="article-title">
            标题
            <el-input v-model="form.title" placeholder="请输入标题"></el-input>
          </div>
          <div>
            <el-upload
              action="#" 
              :limit="9"
              :on-change="handleChange"
              :auto-upload="false"
              :file-list="form.imgList"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
            >
              <el-icon><Plus /></el-icon>
            </el-upload>
            
            <el-dialog v-model="dialogVisible" @close="dialogVisible = false">
              <img w-full :src="dialogImageUrl" alt="Preview Image" />
            </el-dialog>
          </div>
          <div class="content">
            正文内容
            <el-input 
              show-word-limit
              resize="none" 
              maxlength="1500"
              type="textarea" 
              :rows="5" 
              v-model="form.content"
              placeholder="请输入正文内容"
            ></el-input>
          </div>
          <div class="tag">
            标签
            <el-input v-model="form.tags" placeholder="请输入标签，用逗号分隔" />
          </div>
          <div class="articleType">
            内容来源声明
            <el-radio-group v-model="form.articleType">
              <el-radio value="1" size="large">原创</el-radio>
              <el-radio value="2" size="large">转载</el-radio>
            </el-radio-group>
          </div>
          <div class="status">
            文章可见范围
            <el-radio-group v-model="form.status">
              <el-radio value="1" size="large">公开</el-radio>
              <el-radio value="2" size="large">私密</el-radio>
            </el-radio-group>
          </div>
          <el-divider />
          <div>
            <el-button @click="publish" type="success">发布</el-button>
            <el-button @click="save">保存</el-button>
          </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'

  import { getArticleByType, publishArticle } from '@/api/article';
  import { ElMessage } from 'element-plus';
  onMounted(()=>{
    getArticleByType("0").then(res=>{
      console.log(res.data[0]);
      setForm(res.data[0])
    })
  })
  
  function setForm(arr){
    arr.imgList.spli
    form.value.title=arr.title
      form.value.content=arr.content
      form.value.tags=arr.tags
  }
  const form = ref({
    title: '',
    content: '',
    imgList: [],
    tags: '', // 添加标签字段
    articleType:"1",
    status:"1"
  })
  
  const dialogImageUrl = ref('')
  const dialogVisible = ref(false)
  
  const handleRemove = (uploadFile, uploadFiles) => {
    form.value.imgList = uploadFiles // 更新 imgList
  }
  
  const handleChange = (file, fileList) => {
    form.value.imgList = fileList
  }
  
  const handlePictureCardPreview = (uploadFile) => {
    dialogImageUrl.value = uploadFile.url
    dialogVisible.value = true
  }
  const publish = () => {
    if( form.value.title==''){
      ElMessage.error('文章标题为空')
      return
    }
    if( form.value.imgList.length<=0){
      ElMessage.error('你暂未放任何图片')
      return
    }
    if( form.value.content==''){
      ElMessage.error('文章正文为空')
      return
    }
    if( form.value.tags==''){
      ElMessage.error('请选择一个标签')
      return
    }
publishArticle(form).then(res=>{
  if(res.code==200){
    ElMessage.success('文章上传成功')
  }
  else{
  ElMessage.error(res.msg);
  }
}).catch(err=>{
  ElMessage.error(err);
})
 
  }
  
  const save = () => {
    form.value.id='50'
    publishArticle(form).then(res=>{
  if(res.code==200){
    ElMessage.success('文章保存到草稿箱成功')
  }
  else{
  ElMessage.error(res.msg);
  }
}).catch(err=>{
  ElMessage.error(err);
})
  }
</script>

<style lang="scss" scoped>

</style>