package com.kyweb.service.serviceImpl;

import com.kyweb.mapper.TbUserMapper;
import com.kyweb.model.TbUser;
import com.kyweb.service.UserService;
import com.kyweb.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zl on 2015/8/27.
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserMapper tbuserMapper;

    public UserVo getUserInfo(String name) {
        TbUser tbUser = tbuserMapper.findTbUser(name);
        UserVo userVo = new UserVo();
        if (null != tbUser) {
            BeanUtils.copyProperties(tbUser, userVo);
        }
        return userVo;
    }

    @Override
    public boolean checkUser(UserVo userVo) {
        log.info("开始校验用户是否登录成功");
        UserVo userDB = getUserInfo(userVo.getName());

        return userVo.getPassword().equals(userDB.getPassword());
    }

}
