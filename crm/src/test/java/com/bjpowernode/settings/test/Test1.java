package com.bjpowernode.settings.test;

import com.bjpowernode.crm.utils.DateTimeUtil;

public class Test1 {
    public static void main(String[] args) {

        //验证失效时间
        //失效时间
        String expireTime = "2019-10-10 10:10:10";
        //当前系统时间
        String currentTime = DateTimeUtil.getSysTime();

        int count = expireTime.compareTo(currentTime);

        System.out.println(count);
    }
}
