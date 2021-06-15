package com.bjpowernode.crm.web.listener;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.settings.service.impl.DicServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class SysInitListener implements ServletContextListener {
    //该方法是用来监听上下文域对象的方法，当服务器启动，上下文域对象创建对象创建完毕后，马上执行该方法
    @Override
    public void contextInitialized(ServletContextEvent event) { //event:该参数能够取得监听的对象

        System.out.println("服务器缓存处理数据字典开始");

        ServletContext application = event.getServletContext();
        DicService ds = (DicService) ServiceFactory.getService(new DicServiceImpl());

        Map<String, List<DicValue>> map = ds.getAll();
        //将map解析为上下文域对象中保存的键值对
        Set<String> set = map.keySet();
        for (String key:set){
            //取数据字典
            application.setAttribute(key,map.get(key));
        }
        System.out.println("服务器缓存处理数据字典结束");

        //数据字典处理完毕后，处理Stage2Possibility.properties文件,解析properties文件
        Map<String,String> pMap = new HashMap<String, String>();
        ResourceBundle rb = ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> e = rb.getKeys();
         while (e.hasMoreElements()){
             //阶段
             String key = e.nextElement();
             //可能性
             String value = rb.getString(key);
             pMap.put(key,value);
         }

         //将pMap保存到服务器缓存中
        application.setAttribute("pMap",pMap);
    }
}
