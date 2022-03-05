package com.everybodydance.dao;

import com.everybodydance.pojo.Food;
import com.everybodydance.pojo.FoodPic;

import java.util.List;

public interface FoodMapper {
    boolean addPic(FoodPic pic);
    void addFood(Food food);
    FoodPic queryPicById(String picid);
    void updatePic(FoodPic pic);

    List<Food> querySome();

    void delByFoodId(String id);

    void setFood(Food food);

    Food hasFood(String name);

    String queryPicUrlByFoodId(String foodId);
}
