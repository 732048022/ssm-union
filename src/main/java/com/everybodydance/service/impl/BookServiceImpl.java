package com.everybodydance.service.impl;

import com.everybodydance.dao.BookMapper;
import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.BookVo;
import com.everybodydance.pojo.Pic;
import com.everybodydance.service.BookService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper dao;

    @Transactional
    @Override
    public void addBook(Book book) {
        dao.addBook(book);
        Pic pic =dao.queryPicById(book.getPicid());
        pic.setBookid(book.getId());
        dao.updatePic(pic);
    }

    @Override
    public boolean addPic(Pic pic) {
        return dao.addPic(pic)>0;
    }

    @Override
    public String queryPicByBookId(String id) {
        return dao.queryPicByBookId(id);
    }

    @Override
    public void delById(List<String> listStr) {
        dao.delById(listStr);
    }

    @Override
    public List<Book> querySome(BookVo vo, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return dao.queryByCondition(vo);
    }

}
