import { defineStore } from 'pinia';
import { reactive, ref } from 'vue';

export const useInfoStore = defineStore('infoStore', () => {
    const targetUser=reactive({
        id:'1',
        username:'测试目标用户',
        avatar:'/svg/girl-avatar.svg',
        avatarCover:"/bg/1/sun.png",
        email:'1260712700@qq.com',
        intro:'测试信息',
        articleCount:1,
        categorizeCount:1,
        fansCount:1,
        followCount:100,
        isSubscribe:0,
        isDelete:0,
        createTime:'2024-10-06 21:42:04'
    })
    const curUser=ref({
        id:'1',
        username:'admin',
        avatar:'/svg/boy-avatar.svg',
        avatarCover:"/bg/1/train.jpg",
        email:'1260712700@qq.com',
        intro:'管理员？一个程序猿罢力',
        articleCount:1,
        categorizeCount:1,
        fansCount:1,
        followCount:1000,
        isSubscribe:0,
        isDelete:0,
        createTime:'2024-10-06 21:42:04'
    })
    function setCurUser(user){
        curUser.value=user
    }
    function getCurUser(){
        return curUser.value
    }
    const targetUserWord=ref([
        '你不知道的事，还有很多呢，傻孩子',
        '在一无所有的的年纪遇到了最适合的你',
        '无须伤心，还有更多伤心的事等着你',
        '世界上很多事情都可以相信，唯独人性不行',
        '我的回忆不是我的',
        '爱是个什么东西？',
        '努力提升自己吧！等以后你遇到更优秀的女孩儿的时候会发现其实当初的那个她也没那么重要了',
        '爱我还是他说出来这句话结果已经很显然你输了',
        '别难过啦乖乖 我们来日方长',
        '怪缘分不够~噢'
      ])
      const targetUserArticleList=reactive(
        {
            helloArticle:{
                id:'1',
                userId:'001',
                articleCover:'/bg/3/yournames_misiha.png',
                categoryId : -1,
                articleTitle:'项目更新日志',
                articleAbstract:  '作者有话说',
                is_top:0,
                isRecommend: 0,
                is_delete: 0  ,
                status :1 ,
                type :1 ,
                createTime :'2024-10-6 22:06:00',
                updateTime: '2024-10-6 22:06:00' 
            },
            hello2Article:{
                id:'1',
                userId:'001',
                articleCover:'/bg/3/yournames_misiha.png',
                categoryId : -1,
                articleTitle:'项目更新日志',
                articleAbstract:  '作者有话说',
                is_top:0,
                isRecommend: 0,
                is_delete: 0  ,
                status :1 ,
                type :1 ,
                createTime :'2024-10-6 22:06:00',
                updateTime: '2024-10-6 22:06:00' 
            }
        }
    )
    const targetUserBg=reactive(
        [ 
          '/bg/1/dusk.jpg',
        "/bg/1/car_station.jpg",
        "/bg/1/girl_and_station.jpg",
        "/bg/1/sun.jpg",
        "/bg/1/traffic.jpg",
        "/bg/1/train.jpg",
      
      ])
    return {
        targetUserWord,targetUserBg,
        targetUserArticleList, targetUser,setCurUser,getCurUser
    }
});
