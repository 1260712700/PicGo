import { getCategory } from '@/api/article';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useCategoryStore = defineStore('categoryStore', () => {
    const categoryList = ref(
        [
            { id: 1, categoryName: '推荐' },
            { id: 2, categoryName: '美食' },
            { id: 3, categoryName: '彩妆' },
            { id: 4, categoryName: '影视' },
            { id: 5, categoryName: '职场' },
            { id: 6, categoryName: '情感' },
            { id: 7, categoryName: '家居' },
            { id: 8, categoryName: '游戏' },
            { id: 9, categoryName: '旅行' },
            { id: 10, categoryName: '健身' },
            { id: 11, categoryName: '科技' },
            { id: 12, categoryName: '其他' },
        ]
    )
    return {
        categoryList
    }
});
