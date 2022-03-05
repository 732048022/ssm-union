package com.everybodydance.dao;

import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.BookVo;
import com.everybodydance.pojo.Pic;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookMapper {
    //返回主键Mybatis 字符串类型的主键
    int addPic(Pic pic);
    void addBook(Book book);



    Pic queryPicById(String picid);

    void updatePic(Pic pic);
    //按照田条件查询
    List<Book> queryByCondition(BookVo vo);

    String queryPicByBookId(String id);

    void delById(@Param("listStr") List<String> listStr);
}
