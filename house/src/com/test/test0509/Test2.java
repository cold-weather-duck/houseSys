package com.test.test0509;

import com.entity.District;
import com.entity.Street;
import com.service.IHouseService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        District district = ds.selectByPrimaryKeyDistrict(Integer.parseInt("1004"));
        List<Street> streetList = district.getStreetList();
        for(Street s:streetList){
            System.out.println("区域名称"+district.getName()+"  街道="+s);
        }
    }
}
