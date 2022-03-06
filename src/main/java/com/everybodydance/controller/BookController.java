package com.everybodydance.controller;

import com.everybodydance.commons.response.ETResponse;
import com.everybodydance.commons.utils.SetResponse;
import com.everybodydance.commons.utils.page;
import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.BookVo;
import com.everybodydance.pojo.Pic;
import com.everybodydance.service.BookService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private SetResponse sr;
    @Autowired
    private BookService service;

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse addFood(Book book) {
        try {
            service.addBook(book);
            return sr.setResponse("添加成功","200");

        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse addPic(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        String fExt = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "")+ fExt;
        ServletContext application = request.getServletContext();
        String path = application.getRealPath("/files");
        File f = new File(path, newFileName);
        file.transferTo(f);
        Pic pic = new Pic();
        pic.setRealname(fileName);
        pic.setSavepath("/files/" + newFileName);
        pic.setCover(0);
        pic.setUploadtime(new Timestamp(System.currentTimeMillis()));
        if (service.addPic(pic)) {
            return sr.setResponse(pic.getId(),"200");
        }
        return null;

    }

    /**
     * 查询桌子信息
     * @param vo
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/querySome",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse querySome(BookVo vo, int pageNumber, int pageSize) {
        List<Book> rows = service.querySome(vo, pageNumber, pageSize);
        PageInfo<Book> info = new PageInfo<>(rows);//info 是个Mybatis写的page
        page<Book> pa = new page<>();
        pa.setRows(info.getList());
        pa.setTotal(Integer.parseInt(info.getTotal() + ""));
        pa.setPageSize(pageSize);
        pa.setPageNumber(pageNumber);
        return sr.setResponse(pa,"200");
    }

    @RequestMapping(value = "/queryPicByBookId",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse queryBookById(String id){
        String str=service.queryPicByBookId(id);
        return sr.setResponse(str,"200");
    }
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse delById(String id){
        String[] arrStr =id.split(",");
        List<String> listStr =new ArrayList<>();
        for (String s : arrStr) {
            listStr.add(s);
        }
        service.delById(listStr);
        return sr.setResponse("","200");
    }

}
