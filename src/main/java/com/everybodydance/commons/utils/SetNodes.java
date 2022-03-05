package com.everybodydance.commons.utils;

import com.everybodydance.pojo.Menus;

import java.util.ArrayList;
import java.util.List;

public class SetNodes {
    public static void setNodes(Menus m, List<Menus> menus){
        List<Menus> children =new ArrayList<>();//存子菜单的集合
        for (Menus menu : menus) {
            if(menu.getPid()==m.getId()){
                children.add(menu);//装载
            }
        }
        m.setNodes(children);//设置当前菜单的子菜单属性

        if(children.size()>0){//直到没有子菜单
            for (Menus child : children) {
                SetNodes.setNodes(child,menus);//递归
            }
        }
    }
}
