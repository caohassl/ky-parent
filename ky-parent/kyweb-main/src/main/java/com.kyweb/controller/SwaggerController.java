package com.kyweb.controller;

import com.kyweb.model.TbUser;
import com.kyweb.vo.UserVo;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
@RestController
@RequestMapping(value="/users")
public class SwaggerController {


    /*
     *  http://localhost:8080/swagger/index.html
     */

    /**
     *
     * @return
     */
    @ApiOperation(value="Get all users",notes="requires noting")
    @RequestMapping(method=RequestMethod.GET)
    public List<UserVo> getUsers(){
        List<UserVo> list=new ArrayList<UserVo>();

        UserVo user=new UserVo();
        user.setName("hello");
        list.add(user);

        UserVo user2=new UserVo();
        user.setName("world");
        list.add(user2);
        return list;
    }

    @ApiOperation(value="Get user with id",notes="requires the id of user")
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public TbUser getUserById(@PathVariable String name){
        TbUser user=new TbUser();
        user.setName("hello world");
        return user;
    }
}
