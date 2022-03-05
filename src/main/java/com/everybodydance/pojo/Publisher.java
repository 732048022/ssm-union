package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//生成getter setter toString hashCode等
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    private int id;
    private String name;
}
