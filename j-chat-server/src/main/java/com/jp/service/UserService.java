package com.jp.service;

import com.jp.domain.dto.UserRegisterDTO;
import com.jp.domain.dto.UserResetDTO;
import com.jp.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.UserAccountVO;
import com.jp.domain.vo.UserDetailVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
* @author 12607
* @description 针对表【j_user】的数据库操作Service
* @createDate 2024-10-30 05:13:35
*/
public interface UserService extends IService<User>, UserDetailsService {
//    ResponseResult<UserDetailVO> login(UserLoginDTO userLoginDto);
    ResponseResult<UserDetailVO>  getUserInfo(Long userId);
    ResponseResult<Void> register(UserRegisterDTO userRegisterDto);
    ResponseResult<Void> resetConfirm(UserResetDTO userResetDto);
    ResponseResult<Void> resetPassword(UserResetDTO userResetDto);
    ResponseResult<UserAccountVO> findAccountById(Long id);
}
