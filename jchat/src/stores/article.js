import { getArticleByCategory, getArticleByType, getArticleCountByType } from '@/api/article';
import { handleDateTime } from '@/utils/time';
import { ElMessage } from 'element-plus';
import { defineStore } from 'pinia';
import { reactive, ref } from 'vue';

export const useArticleStore = defineStore('article', () => {
    const saveArticleCount=ref(0)//保存文章的计数
    const isUpdate=ref(false)//是否进入编辑模式
    const homeArticleList=ref([])//主页文章展示
    const personalArticleList=ref([])//个人文章展示
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
    const page=ref({
        total:0,
        size:0,
        current:0,
        pages:0
    })

    /**
     * 
     * @param {String} mode mode 查找模式（save、home、personal） 计数模式(count)
     * @param {*} val 查找值 
     * @param {*} current 当前页
     * @param {*} size 当前页大小
     */
    function getArticle(mode,val,current,size){
        switch(mode){
            case "save":{
                getArticleByType("0",1,1).then((res) => {
                    if (res.code === 200) {
                            saveArticle.value=res.data.records[0]
                         ElMessage.success("已恢复保存的文章")
                    }
                }).catch(err=>{
                    ElMessage.error(err.msg)
                })
            }
                break;
            case "home":{
                getArticleByCategory(val,current,size).then(res=>{
                    if(res.code===200){
                                 // 处理分页的时间
                                 handleTime(res.data)
                        homeArticleList.value=res.data.records
                        setPage(res.data)
                    }else{
                        console.log('getArticleByCategory'+res);
                    }
                }) .catch(err=>{
                    console.log(err);
                })
            }
            break;
            case "personal":{
                getArticleByType(val,current,size).then(res=>{
                    if(res.code===200){
                        handleTime(res.data)
                        personalArticleList.value=res.data.records
                        setPage(res.data)
                    }else{
                        console.log('getArticleByCategory'+res);
                    }
                }) .catch(err=>{
                    console.log(err);
                })
            }
            break;
            case "count":{
                getArticleCountByType(val).then(res=>{
                    if(res.code===200){
                        saveArticleCount.value=res.data
                    }
                })
            }
            break;
        }
    }
    /**
     * 处理时间
     * @param {Object} data 返回来的数据
     */
    function handleTime(data){
        if(Array.isArray(data.records)){
            data.records.forEach(item => {
                if (item.updateTime) {
                  item.updateTime = handleDateTime(item.updateTime);
                }
                if (item.createTime) {
                  item.createTime = handleDateTime(item.createTime);
                }
              });
        }
    }
    function setPage(data){
        page.value.total=data.total
        page.value.size=data.size
        page.value.current=data.current
        page.value.pages=data.pages
    }
    function clearPage(){
        page.value={
            total:0,
            size:1,
            current:1,
            pages:1
        }
    }
    function clearArticle(mode){
        switch(mode){
            case "save":{
                saveArticle.value={}
            }
            break;
            default:
                saveArticle.value={}
                 homeArticleList.value={}
                 personalArticleList.value={}
        }
      
    }
    return {
        saveArticle,clearArticle,saveArticleCount
        ,isUpdate,isPublish,personalArticleList,homeArticleList,page
        ,getArticle,clearPage
    }
});
