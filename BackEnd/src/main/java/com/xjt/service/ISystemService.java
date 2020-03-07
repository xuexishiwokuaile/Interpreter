package com.xjt.service;

import com.xjt.model.ReturnClass;

import java.util.Map;

public interface ISystemService
{
    /**
    CREATED BY CHEN ANRAN
    2019.7.4
    */

    //接收前端发送的程序源代码，并将解释器处理后的结果返回到前端
    public ReturnClass WordAnalyse(String code);
}
