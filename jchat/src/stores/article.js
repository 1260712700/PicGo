import { getArticleByType, getArticleCount } from '@/api/article';
import { ElMessage } from 'element-plus';
import { defineStore } from 'pinia';
import { reactive, ref } from 'vue';

export const useArticleStore = defineStore('article', () => {
    const saveArticleCount=ref(0)//保存文章的计数
    const isUpdate=ref(false)//是否进入编辑模式
    const getSaveArticleCount = async () => {
        getArticleCount("0").then((res) => {
            if (res.code === 200) {
                saveArticleCount.value = res.data
            }
        }).catch(err=>{
            console.log(err);
        })
    }
    const articleList=ref([])
    async function  getArticleList(type) {
        getArticleByType(type).then((res) => {
            if (res.code === 200) {
                if( res.data.length>0){
                    articleList.value=res.data
                }else{
                    clearSaveArticle()
           }
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
        getArticleByType("0").then((res) => {
            if (res.code === 200) {
                if( res.data.length>0){
                    saveArticle.value=res.data[0]
            ElMessage.success("已恢复保存的文章")
                }else{
                    clearSaveArticle()
           }
            }
        }).catch(err=>{
            console.log(err);
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

        const articleList2=reactive(
            [
             {
                 id:'1',
                 userId:'001',
                 articleCover:'/bg/1/car_station.jpg',
                 articleImage:[ 
                     '/bg/2/Your_name_misiha.png',
                     "/bg/2/cloud.jpg",
                     "/bg/2/dusk_girl.jpg",
                     "/bg/2/dusk_girl2.jpg",
                     "/bg/2/castle.jpg",
                     "/bg/2/sea_girl.jpg",
                    ],
                     content:'作者有话说。。。。',
                 categoryId : -1,
                 articleTitle:'关注文章',
                 articleAbstract:  '作者有话说',
                 is_top:0,
                 isRecommend: 0,
                 is_delete: 0  ,
                 status :1 ,
                 type :1 ,
                 createTime :'2024-10-6 22:06:00',
                 updateTime: '2024-10-6 22:06:00' 
             },
            ])
    return {
        articleList2,getSaveArticle,saveArticle,clearSaveArticle,saveArticleCount
        ,getSaveArticleCount,isUpdate,isPublish,articleList,getArticleList
    }
});
