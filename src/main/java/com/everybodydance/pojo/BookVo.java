package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class BookVo {
    //条件查询所需要的类
    private String name;
    private String publisherid;
    private String startdate;
    private String enddate;
}
