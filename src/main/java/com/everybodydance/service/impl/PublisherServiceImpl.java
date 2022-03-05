package com.everybodydance.service.impl;

import com.everybodydance.dao.PublisherMapper;
import com.everybodydance.pojo.Publisher;
import com.everybodydance.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherMapper dao;

    @Override
    public List<Publisher> queryAllPubs() {
        //Controller-->Service-->mapper
        return dao.queryAllPubs();
    }
}
