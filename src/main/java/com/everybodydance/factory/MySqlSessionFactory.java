package com.everybodydance.factory;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.IOException;

/**
 * 重写sqlSessionFactory,解决ssm框架Mapper语法错误重复加载无异常
 */
public class MySqlSessionFactory extends SqlSessionFactoryBean {
    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws Exception {
        try {
            return super.buildSqlSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            ErrorContext.instance().reset();
        }

    }
}