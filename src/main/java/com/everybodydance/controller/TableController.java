package com.everybodydance.controller;

import com.everybodydance.commons.response.ETResponse;
import com.everybodydance.commons.utils.SetResponse;
import com.everybodydance.commons.utils.page;
import com.everybodydance.pojo.Table;
import com.everybodydance.service.TableService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private SetResponse sr;
    @Autowired
    private TableService service;
    @RequestMapping(value = "/querySome",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse querySome(int pageNumber, int pageSize){
        List<Table> rows = service.querySome(pageNumber, pageSize);
        PageInfo<Table> info = new PageInfo<>(rows);//info 是个Mybatis写的page
        page<Table> pa = new page<>();
        pa.setRows(info.getList());
        pa.setTotal(Integer.parseInt(info.getTotal() + ""));
        pa.setPageSize(pageSize);
        pa.setPageNumber(pageNumber);
        return sr.setResponse(pa,"200");
    }
    @RequestMapping(value = "/setOrderNum",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse setOrderNum(int id,int setOrderNum){
        service.setOrderNum(id,setOrderNum);
        return sr.setResponse("","200");
    }
    @RequestMapping(value = "/delTable",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse delTable(int id){
        service.delTable(id);
        return sr.setResponse("删除成功","200");
    }
    @RequestMapping(value = "/modifyTable",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse modifyTable(int id){
        Table t =service.modifyTable(id);
        return sr.setResponse(t,"200");
    }
    @RequestMapping(value = "/setTable",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse setTable(String info,int personCount,int id){
        service.setTable(info,personCount,id);
        return sr.setResponse("","200");
    }
    @RequestMapping(value = "/addTable",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse addTable(String info,int personCount){
        service.addTable(info,personCount);
        return sr.setResponse("","200");
    }
}
