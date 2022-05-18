package com.test.test0509;

import com.entity.Street;
import com.service.IHouseService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test3 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        Map<String,Object> map = new HashMap<>();
        map.put("id",Integer.parseInt("1000"));

        List<Street> streetList = ds.selectActiveStreet(map);
        for (Street s:streetList){
            System.out.println(s.getDistrict());
        }
    }
}
