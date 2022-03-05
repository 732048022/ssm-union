package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class FoodPic {
    private String id;
    private String savepath;
    private String realname;
    private String foodid;
}
