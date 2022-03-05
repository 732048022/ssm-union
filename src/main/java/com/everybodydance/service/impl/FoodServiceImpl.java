package com.everybodydance.service.impl;
import com.everybodydance.dao.FoodMapper;
import com.everybodydance.pojo.Food;
import com.everybodydance.pojo.FoodPic;
import com.everybodydance.service.FoodService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper dao;

    @Override
    public boolean addPic(FoodPic pic) {
        return dao.addPic(pic);
    }

    @Transactional
    @Override
    public void addFood(Food food) {
        dao.addFood(food);
        FoodPic pic =dao.queryPicById(food.getPicid());
        pic.setFoodid(food.getId());
        dao.updatePic(pic);
    }

    @Override
    public List<Food> querySome(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return dao.querySome();
    }

    @Override
    public void delByFoodId(String id) {
        dao.delByFoodId(id);
    }

    @Transactional
    @Override
    public void setFood(Food food) {
        dao.setFood(food);
        FoodPic pic =dao.queryPicById(food.getPicid());
        pic.setFoodid(food.getId());
        dao.updatePic(pic);
    }

    @Override
    public Food hasFood(String name) {
        return dao.hasFood(name);
    }

    @Override
    public String qeuryPicUrlByFoodId(String foodId) {
        return dao.queryPicUrlByFoodId(foodId);
    }

}
