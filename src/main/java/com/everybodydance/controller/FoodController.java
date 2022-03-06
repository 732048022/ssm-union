package com.everybodydance.controller;

import com.everybodydance.commons.response.ETResponse;
import com.everybodydance.commons.utils.SetResponse;
import com.everybodydance.commons.utils.page;
import com.everybodydance.pojo.Food;
import com.everybodydance.pojo.FoodPic;
import com.everybodydance.service.FoodService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/food")
@Slf4j
public class FoodController {
    @Autowired
    private SetResponse sr;
    @Autowired
    private FoodService service;
    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse addPic(MultipartFile file, HttpServletRequest request) throws IOException {
        log.info("进入添加图片功能......");
        String fileName = file.getOriginalFilename();
        String fExt = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "")+ fExt;
        ServletContext application = request.getServletContext();
        String path = application.getRealPath("/files");
        File f = new File(path, newFileName);
        file.transferTo(f);
        FoodPic pic = new FoodPic();
        pic.setRealname(fileName);
        pic.setSavepath("/files/" + newFileName);
        if (service.addPic(pic)) {
            return sr.setResponse(pic.getId(),"200");
        }
        return null;

    }
    @RequestMapping(value = "/addFood", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse addFood(Food food) {
        try {
            service.addFood(food);
            return sr.setResponse("添加成功","200");
        } catch (Exception e) {
            return null;
        }
    }
    @RequestMapping(value = "/querySome", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse querySome(int pageNumber, int pageSize) {
        List<Food> rows = service.querySome(pageNumber, pageSize);
        PageInfo<Food> info = new PageInfo<>(rows);//info 是个Mybatis写的page
        page<Food> pa = new page<>();
        pa.setRows(info.getList());
        pa.setTotal(Integer.parseInt(info.getTotal() + ""));
        pa.setPageSize(pageSize);
        pa.setPageNumber(pageNumber);
        return sr.setResponse(pa,"200");
    }
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse delByFoodId(String id) {
        service.delByFoodId(id);
        return sr.setResponse("","200");
    }
    @RequestMapping(value = "/setFood", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse setFood(HttpServletRequest req) {
        String id =req.getParameter("id");
        String picid =req.getParameter("picid");
        String name =req.getParameter("name");
        String info =req.getParameter("info");
        int kindid =Integer.parseInt(req.getParameter("kindid"));
        int price = Integer.parseInt(req.getParameter("price"));
        Food f =new Food(id,name,picid,kindid,info,price);
        service.setFood(f);
        return sr.setResponse("","200");
    }
    @RequestMapping(value = "/hasFood", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse hasFood(String name) {
        Food food = service.hasFood(name);
        if(food!=null){
            return sr.setResponse(food.getId(),"200");
        }
         return sr.setResponse("","444");
    }
    @RequestMapping(value = "/queryPicUrlByFoodId", method = RequestMethod.POST)
    @ResponseBody
    public ETResponse queryPicUrlByFoodId(String foodId){
        String url =service.qeuryPicUrlByFoodId(foodId);
        return sr.setResponse(url+"","200");
    }
}
