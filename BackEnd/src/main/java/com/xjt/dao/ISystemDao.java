package com.xjt.dao;


import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISystemDao
{
    /**
     CREATED BY CHEN ANRAN
     2019.7.4
     */

    //将前端发送的源代码存储到数据库中
    void insertWordAnalyseSourceCode(@Param(value = "id") String id,@Param(value = "code") String code);
    //获取解释器处理过后的结果
    String selectWordAnalyseResult();
}
