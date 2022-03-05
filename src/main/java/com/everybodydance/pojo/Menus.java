package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data@AllArgsConstructor@NoArgsConstructor
public class Menus {
    private int id;
    private String text;
    private String href;
    private int pid;
    private int sort;
    private List<Menus> nodes;
}
