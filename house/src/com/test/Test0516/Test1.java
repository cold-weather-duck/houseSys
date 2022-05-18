package com.test.Test0516;

import com.entity.Type2;
import com.service.IHouseService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        List<Type2> list = ds.selectAllType2();
        for(Type2 t:list){
            System.out.println(t.getName());
        }
    }
}
