import http from "@/utils/http.js";
export function getCategory(){
   return http.get("/category")
}
export function publishArticle(form){
    return http.post("/article/auth/publish", form);
}
export function getArticleByType(type){
    return http.get("/article/auth/"+type);
}
export function getArticleCount(type){
    return http.get("/article/auth/count/"+type);
}
export function deleteArticleById(id){
    return http.delete("/article/auth/"+id);
}