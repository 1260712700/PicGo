import http from "@/utils/http.js";
export function getCategory(){
   return http.get("/category")
}
export function publishArticle(form){
    return http.post("/article/auth/publish", form);
}
/**
 * 类型说明：
 * - 0：获取草稿箱文章
 * - 1：获取个人所有文章
 * - 2：获取推荐文章
 * - 3：获取所有文章
 * - 4：获取审核中公开文章
 * - 5：获取审核中私人文章
 * @param {*} type 
 * @param {Number} pageNum 页数
 * @param {Number} pageSize 一页有多少
 * @returns 
 */
export function getArticleByType(type,pageNum,pageSize){
    return http.get(`/article/auth/type/${type}`,{
        params: {
            pageNum: pageNum,
            pageSize: pageSize
        }
    });
}
/**
 * 
 * @param {Number} categoryId 文章分类id
 * @returns 
 */
export function getArticleByCategory(categoryId,pageNum,pageSize){
    return http.get(`/article/category/${categoryId}`,{
        params: {
            pageNum: pageNum,
            pageSize: pageSize
        }
    })
}
/**
 * 根据类型获取文章列数量统计。
 * @param {String} type - 获取文章的类型。
 * 
 */
export function getArticleCountByType(type){
    return http.get("/article/auth/count/"+type);
}
/**
 * 
 * @param {Number} id 
 */
export function deleteArticleById(id){
    return http.delete("/article/auth/"+id);
}