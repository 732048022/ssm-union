package com.everybodydance.dao;

import com.everybodydance.pojo.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableMapper {
    List<Table> querySome();

    void setOrderNum(@Param("id") int id,@Param("o") String orderNum);

    void removeOrderNum(int id);

    void delTable(int id);

    Table modifyTable(int id);

    void setTable(@Param("info") String info,@Param("p") int personCount,@Param("id") int id);

    void addTable(@Param("i") String info,@Param("p") int personCount);
}
