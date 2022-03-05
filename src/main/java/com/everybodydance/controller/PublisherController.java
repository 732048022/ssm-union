package com.everybodydance.controller;

import com.everybodydance.commons.response.ETResponse;
import com.everybodydance.commons.utils.SetResponse;
import com.everybodydance.pojo.Publisher;
import com.everybodydance.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/kind")
public class PublisherController {
    @Autowired
    private SetResponse sr;
    @Autowired
    private PublisherService service;
    @RequestMapping(value = "/queryAllKinds",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse queryAllPublishers(){
        //调用service
        List<Publisher> pubs =service.queryAllPubs();
        return sr.setResponse(pubs,"200");
    }
}
