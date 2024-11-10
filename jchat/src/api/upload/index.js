import http from "@/utils/http";

export function uploadImage(file,type){
    let formData= new FormData()
    formData.append("images",file)
    formData.append("type",type)
    return http.post("/public/auth/upload",formData,{
        headers: {
            'Content-Type': 'multipart/form-data'
          }
    });
}