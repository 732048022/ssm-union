package com.everybodydance.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private String author;
    private Double price;
    private Integer stock;
    private Integer del;
    private Integer status;
    private String publisherid;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")//从数据库查出来返回给前台的格式
    private Date publishdate;
    private String info;
    private String picid;
    private Publisher pub;
    private List<Pic> pics;
}
