import http from "@/utils/http.js";
export function getCategory(){
   return http({
    url:'/category',
    method:'get'
})
}
export function publishArticle(form){
    const formData= new FormData()
    formData.append('id', form.value.id);
    formData.append('title', form.value.title);
    formData.append('content', form.value.content);
    formData.append('tags', form.value.tags);
    formData.append('articleType', form.value.articleType);
    formData.append('status', 3);
       // 假设 imgList 是包含文件的数组
       form.value.imgList.forEach(file => {
        formData.append('imgList', file.raw); // file.raw 是 File 对象
    });
    return http.post("/article/auth/publish", formData, {
      headers: {
          'Content-Type': 'multipart/form-data', // 设置请求头
      }
  });
}
export function getArticleByType(type){
    return http.get("/article/auth/"+type);
}