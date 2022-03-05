package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
public class Food {
    private String id;
    private String name;
    private String picid;
    private int kindid;
    private String info;
    private int price;
    private Publisher kind;
    private List<FoodPic> foodpics;

    public Food(String id, String name, String picid, int kindid, String info, int price) {
        this.id = id;
        this.name = name;
        this.picid = picid;
        this.kindid = kindid;
        this.info = info;
        this.price = price;
    }
}
