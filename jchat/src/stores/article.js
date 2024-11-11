import { getArticleByCategory, getArticleByType, getArticleCountByType } from '@/api/article';
import { ElMessage } from 'element-plus';
import { defineStore } from 'pinia';
import { reactive, ref } from 'vue';

export const useArticleStore = defineStore('article', () => {
    const saveArticleCount=ref(0)//保存文章的计数
    const isUpdate=ref(false)//是否进入编辑模式
    const homeArticleList=ref([])
    const personalArticleList=ref([])
    const page=ref({
        total:1,
        size:1,
        current:1,
        pages:1
    })
    function setPage(data){
        page.value.total=data.total
        page.value.size=data.size
        page.value.current=data.current
        page.value.pages=data.pages
    }
 
    const getArticleCount = async (type) => {
        getArticleCountByType(type).then((res) => {
            if (res.code === 200) {
                saveArticleCount.value = res.data
            }else{
                ElMessage.error(res.msg)
            }
        }).catch(err=>{
            console.log(err);
        })
    }
    const isPublish=ref(false)//编辑的文章是否已经发布
    const saveArticle=ref({
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
    })
    const getSaveArticle = async () => {
        getArticleByType("0",1,1).then((res) => {
            if (res.code === 200) {
                if( res.data.length>0){
                    saveArticle.value=res.data[0]
                 ElMessage.success("已恢复保存的文章")
                }
            }
        }).catch(err=>{
            ElMessage.error(err.msg)
        })
    }
    function clearSaveArticle(){
        saveArticle.value={
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
        }
    }
    return {
        getSaveArticle,saveArticle,clearSaveArticle,saveArticleCount
        ,getArticleCount,isUpdate,isPublish,personalArticleList,homeArticleList,page
    }
});
