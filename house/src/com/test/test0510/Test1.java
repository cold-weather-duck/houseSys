package com.test.test0510;

import com.entity.*;
import com.service.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IHouseService ds = (IHouseService)ctx.getBean("houseService");

        Map<String,Object> map = new HashMap<>();

        String title = "好";
        title = title.trim();
        if(!"".equals(title)){
            map.put("title","%"+title+"%");
        }

        int minprice = 0;
        int maxprice = 10000;
        if(maxprice!=0 && maxprice>minprice){
            map.put("minprice",minprice);
            map.put("maxprice",maxprice);
        }

        map.put("street","中关村大街");

        map.put("type2","一室两厅");

        map.put("district","海淀");

        int minfloorage = 0;
        int maxfloorage = 100;
        if(maxfloorage>0 && maxfloorage>minfloorage){
            map.put("minfloorage",minfloorage);
            map.put("maxfloorage",maxfloorage);
        }


        List<House> list = ds.selectActiveHouse(map);
        for (House h:list){
            System.out.println(h.getId());
            System.out.println("房屋标题=="+h.getTitle());
            System.out.println("房屋街道=="+h.getStreet().getName());
            System.out.println("房屋面积=="+h.getFloorage());
            System.out.println("联系方式=="+h.getContact());
            System.out.println("房屋类型=="+h.getType2().getName());
            System.out.println("房屋价格="+h.getPrice());
            System.out.println("区域名称=="+h.getStreet().getDistrict().getName());
            System.out.println("--------------------------");
        }

    }
}
