import { defineStore } from 'pinia';
import { reactive } from 'vue';

export const useArticleStore = defineStore('articleStore', () => {
    const articleList=reactive(
        [
         {
             id:'1',
             userId:'001',
             articleCover:'/bg/2/Your_name_misiha.png',
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
         {
             id:'2',
             userId:'001',
             articleCover:'/bg/2/sunshine.jpg',
             articleImage:[  '/bg/2/sunshine.jpg',
                 "/bg/2/cloud.jpg",
                 "/bg/2/dusk_girl.jpg",
                 "/bg/2/dusk_girl2.jpg",
                 "/bg/2/castle.jpg",
                 "/bg/2/sea_girl.jpg",],
                 content:'作者有话说。。。。',
             categoryId : -1,
             articleTitle:'推荐文章',
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
        articleList
    }
});
