package com.everybodydance.service.impl;

import com.everybodydance.dao.TableMapper;
import com.everybodydance.pojo.Table;
import com.everybodydance.service.TableService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {
    public static int count=0;
    @Autowired
    private TableMapper dao;

    @Override
    public List<Table> querySome(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return dao.querySome();
    }

    @Override
    public void setOrderNum(int id, int setOrderNum) {
        if(setOrderNum==1){
            //set
            String str ="Order-";
            SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
            String time=sf.format(new Date(System.currentTimeMillis()));
            String end ="00";
            String orderNum =str+time+"-"+end+count++;
            dao.setOrderNum(id,orderNum);
        }else {
            //remove
            dao.removeOrderNum(id);
        }
    }

    @Override
    public void delTable(int id) {
        dao.delTable(id);
    }

    @Override
    public Table modifyTable(int id) {
        return dao.modifyTable(id);
    }

    @Override
    public void setTable(String info, int personCount, int id) {
        dao.setTable(info,personCount,id);
    }

    @Override
    public void addTable(String info, int personCount) {
        dao.addTable(info,personCount);
    }


}
