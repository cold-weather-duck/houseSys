package com.test.test0509;

import com.entity.House;
import com.service.IHouseService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        House house = ds.selectByPrimaryKeyHouse(Integer.parseInt("1000"));
        System.out.println("房屋="+house);
        System.out.println("用户="+house.getUsers());
        System.out.println("街道="+house.getStreet());
        System.out.println("类型="+house.getType2());
    }
}
