package com.xjt.service.impl;

import com.xjt.dao.ISystemDao;
import com.xjt.model.RamdomString;
import com.xjt.model.ReturnClass;
import com.xjt.service.ISystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("systemService")
public class SystemServiceImpl implements ISystemService
{
    /**
     CREATED BY CHEN ANRAN
     2019.7.4
     */

    @Resource
    private ISystemDao systemDao;

    //接收前端发送的程序源代码，并将解释器处理后的结果返回到前端
    public ReturnClass WordAnalyse(String code)
    {
        //生成id
        String id = RamdomString.generateString();
        //将源代码存入到数据库中
        systemDao.insertWordAnalyseSourceCode(id,code);
        ReturnClass rt = new ReturnClass();
        rt.setCode(1);
        rt.setMsg("success");
        rt.setData(code);
        return rt;
    }
}
