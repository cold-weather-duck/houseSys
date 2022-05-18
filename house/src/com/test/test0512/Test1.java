package com.test.test0512;

import com.entity.Users;
import com.service.IHouseService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        Users u = new Users();
        u.setName("昌友才");
        u.setPassword("111");
        u.setTelephone("666666");
        u.setUsername("测试大神昌友才");

        int num = ds.insertUsers(u);
        System.out.println(num);
        System.out.println(u);
    }
}
