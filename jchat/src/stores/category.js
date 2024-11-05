import { getCategory } from '@/api/article';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useCategoryStore = defineStore('categoryStore', () => {
    const category=ref()
     const getInfo = async () => {
        getCategory().then((res) => {
            if (res.code === 200) {
                category.value = res.data
            }
        })
    }
    return {
        category,getInfo
    }
});
