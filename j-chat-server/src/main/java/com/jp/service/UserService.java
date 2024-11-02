package com.jp.service;

import com.jp.domain.dto.UserLoginDto;
import com.jp.domain.dto.UserRegisterDto;
import com.jp.domain.dto.UserResetDto;
import com.jp.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.UserAccountVO;
import com.jp.domain.vo.UserDetailVo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
* @author 12607
* @description 针对表【j_user】的数据库操作Service
* @createDate 2024-10-30 05:13:35
*/
public interface UserService extends IService<User>, UserDetailsService {
//    ResponseResult<UserDetailVo> login(UserLoginDto userLoginDto);
    ResponseResult<UserDetailVo>  getUserInfo(Long userId);
    ResponseResult<Void> register(UserRegisterDto userRegisterDto);
    ResponseResult<Void> resetConfirm(UserResetDto userResetDto);
    ResponseResult<Void> resetPassword(UserResetDto userResetDto);
    ResponseResult<UserAccountVO> findAccountById(Long id);
}
