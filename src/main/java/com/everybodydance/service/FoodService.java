package com.everybodydance.service;

import com.everybodydance.pojo.Food;
import com.everybodydance.pojo.FoodPic;

import java.util.List;

public interface FoodService {
    public boolean addPic(FoodPic pic);

    public void addFood(Food food);

    public List<Food> querySome(int pageNumber, int pageSize);

    public void delByFoodId(String id);

    public void setFood(Food food);

    public Food hasFood(String name);

    public String qeuryPicUrlByFoodId(String foodId);
}
