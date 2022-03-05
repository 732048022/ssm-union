package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class Table {
    private int id;
    private String ordernum;
    private int personcount;
    private String info;
    private boolean status;
    private int del;

}
