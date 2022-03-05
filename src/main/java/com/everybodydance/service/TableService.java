package com.everybodydance.service;

import com.everybodydance.pojo.Table;

import java.util.List;

public interface TableService {
    public List<Table> querySome(int pageNumber, int pageSize);

    public void setOrderNum(int id, int setOrderNum);

    public void delTable(int id);

    public Table modifyTable(int id);

    public void setTable(String info, int personCount, int id);

    public void addTable(String info, int personCount);
}
