package com.everybodydance.service;

import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.Menus;
import com.everybodydance.pojo.Users;

import java.util.List;

public interface UserService {

    public List<Menus> queryMenuByUserId(int userID);

    public Users queryUserByUsernameAndPwd(String uname, String pwd);

    public Book queryBookByBookId(String id);

    public void insertUser(String uname, String pwd);


}
