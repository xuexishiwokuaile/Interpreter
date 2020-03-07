package com.xjt.controller;

import com.xjt.compiler.*;
import com.xjt.model.ReturnClass;
import com.xjt.service.ISystemService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController
{
    /**
    CREATED BY CHEN ANRAN
    */
    StatementTable st = new StatementTable();

    @Autowired
    private ISystemService systemService;
    @RequestMapping("/WordAnalyse")
    @ResponseBody
    public void WordAnalyse(@RequestParam("Source") String source, HttpServletResponse response) throws ServletException, IOException, ParseException, AssignmentException, DeclarationException{
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        System.out.println(source);
        try{
            InputStream in = getStringInputStream(source);
            CMMCompiler compiler = new CMMCompiler(in);
            SimpleNode n = compiler.Start();
            in = null;
            jsonObject.put("source",compiler.token_list);
            jsonArray.add(jsonObject);
            out.println(jsonArray);
            compiler.token_list = "";
        }catch ( Exception e) {
            jsonObject.put("source", e.toString());
            jsonArray.add(jsonObject);
            out.println(jsonArray);
        }
        st.reLoad();
    }

    public static InputStream getStringInputStream(String s) {
        if (s != null && !s.equals("")) {
            try {
                ByteArrayInputStream stringInputStream = new ByteArrayInputStream(
                        s.getBytes());
                return stringInputStream;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping("/GrammarAnalyse")
    @ResponseBody
    public void GrammarAnalyse(@RequestParam("Source") String source,HttpServletResponse response) throws ServletException, IOException, ParseException, AssignmentException, DeclarationException{
        response.setContentType("text/json;charset=utf-8");//设置文件格式为json
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            InputStream in = getStringInputStream(source);
            CMMCompiler compiler = new CMMCompiler(in);
            SimpleNode n = compiler.Start();
            jsonObject = n.dump("");
            in = null;
            JSONObject exceptionObject = new JSONObject();
            exceptionObject.put("wrong", "false");
            jsonArray.add(exceptionObject);
            jsonArray.add(jsonObject);
            out.println(jsonArray);
            compiler.token_list = "";
        }catch ( Exception e) {
            jsonObject.put("source", e.toString());
            jsonArray.add(jsonObject);
            out.println(jsonArray);
        }
        st.reLoad();
    }

    @RequestMapping("/SemanticAnalyse")
    @ResponseBody
    public void SemanticAnalyse(@RequestParam("Source") String source, HttpServletResponse response) throws ServletException, IOException, ParseException, AssignmentException, DeclarationException{
        response.setContentType("text/json;charset=utf-8"); //设置文件格式为json
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        System.out.println(source);
        try{
            InputStream in = getStringInputStream(source);
            CMMCompiler compiler = new CMMCompiler(in);
            SimpleNode n = compiler.Start();
            String output = st.getText();
            System.out.println(output);
            in = null;
            jsonObject.put("source",output);
            jsonArray.add(jsonObject);
            out.println(jsonArray);
            compiler.token_list = "";
        }catch ( Exception e) {
            jsonObject.put("source", e.toString());
            jsonArray.add(jsonObject);
            out.println(jsonArray);
        }
        st.reLoad();
    }

    @RequestMapping("/SemanticAnalyse2")
    @ResponseBody
    public void SemanticAnalyse2(@RequestParam("Source") String source, @RequestParam("parameter") String para, HttpServletResponse response)throws ServletException, IOException, ParseException, AssignmentException, DeclarationException{
        response.setContentType("text/json;charset=utf-8"); //设置文件格式为json
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        System.out.println(source);
        System.out.println(para);
        st.paraArray = JSONArray.fromObject(para);
        try{
            InputStream in = getStringInputStream(source);
            CMMCompiler compiler = new CMMCompiler(in);
            SimpleNode n = compiler.Start();
            String output = st.getText();
            in = null;
            jsonObject.put("source",output);
            jsonArray.add(jsonObject);
            out.println(jsonArray);
            compiler.token_list = "";
        }catch ( Exception e) {
            jsonObject.put("source", e.toString());
            jsonArray.add(jsonObject);
            out.println(jsonArray);
        }
        st.reLoad();
    }
}
