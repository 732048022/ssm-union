package com.everybodydance.service.impl;

import com.everybodydance.dao.UserMapper;
import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.Menus;
import com.everybodydance.pojo.Users;
import com.everybodydance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper dao;

    @Override
    public List<Menus> queryMenuByUserId(int userID) {
        return dao.queryMenuByUserID(userID);
    }

    /**
     * 根据用户名和密码查询用户
     * @param uname 用户名
     * @param pwd 密码
     * @return Users
     */
    @Override
    public Users queryUserByUsernameAndPwd(String uname, String pwd) {
        return dao.queryUserByUsernameAndPwd(uname,pwd);
    }

    @Override
    public Book queryBookByBookId(String id) {
        return dao.queryBookByBookId(id);
    }

    /**
     * 新增用户
     * @param uname
     * @param pwd
     */
    @Transactional
    @Override
    public void insertUser(String uname, String pwd) {
     dao.insertUser(uname,pwd);
    }

}
