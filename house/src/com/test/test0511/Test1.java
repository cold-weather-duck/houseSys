package com.test.test0511;

import com.entity.Users;
import com.service.IHouseService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        Map<String,Object> map = new HashMap<>();


        map.put("uname","admin");
        map.put("upwd","123456");

        Users u = ds.selectLoginUsers(map);

        System.out.println(u);
    }
}
