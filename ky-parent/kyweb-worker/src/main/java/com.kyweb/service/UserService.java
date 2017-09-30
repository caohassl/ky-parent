package com.kyweb.service;

import com.kyweb.vo.UserVo;

/**
 * Created by zl on 2015/8/27.
 */

public interface UserService {


    /**
     * 获得一个UserVo对象
     * @param name
     * @return
     */
    UserVo getUserInfo(String name);

    /**
     * 检查是否在数据库存在
     * @param userVo
     * @return
     */
    boolean checkUser(UserVo userVo);
}
