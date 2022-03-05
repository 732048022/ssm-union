package com.everybodydance.dao;

import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.Menus;
import com.everybodydance.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<Menus> queryMenuByUserID(int userID);

    Users queryUserByUsernameAndPwd(@Param("u") String uname, @Param("p") String pwd);

    Book queryBookByBookId(String id);

    void insertUser(@Param("u") String uname, @Param("p") String pwd);
}
