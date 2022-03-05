package com.everybodydance.service;

import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.BookVo;
import com.everybodydance.pojo.Pic;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public boolean addPic(Pic pic);
    public String queryPicByBookId(String id);

    public void delById(List<String> listStr);
    List<Book> querySome(BookVo vo, int pageNumber, int pageSize);
}
