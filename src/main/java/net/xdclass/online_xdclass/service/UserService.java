package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserService {

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int save (Map<String,String>userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
