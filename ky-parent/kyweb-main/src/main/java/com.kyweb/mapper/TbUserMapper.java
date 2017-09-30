package com.kyweb.mapper;

import com.kyweb.model.TbUser;

/**
 * Created by zl on 2015/8/27.
 */
public interface TbUserMapper {
    /**
     * 根据姓名查用户
     *
     * @param name
     * @return
     */
    TbUser findTbUser(String name);
}
