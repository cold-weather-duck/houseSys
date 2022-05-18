package com.action;

import com.entity.*;
import com.interceptor.Login;
import com.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.*;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class HouseController {

    @Autowired
    private IHouseService hs;


    //登录  http://127.0.0.1:8090/house/login
    @RequestMapping(value = "/login" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> login(String uname, String upwd, HttpSession session) throws Exception{
        Map<String,Object> map = new HashMap<>();

        Map<String,Object> map1 = new HashMap<>();
        map1.put("uname",uname);
        map1.put("upwd",upwd);
        Users u = hs.selectLoginUsers(map1);
        if(u != null){
            session.setAttribute("userInfo",u);
            map.put("code","1");
            map.put("info","登录成功！");
        }else {
            map.put("code","0");
            map.put("info","登录失败，用户名或密码错误！");
        }

        return map;
    }

    //注册（新增）  http://127.0.0.1:8090/house/register
    @RequestMapping(value = "/register" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> register(UsersRegister ur) throws Exception{
        System.out.println("ur="+ur);

        Map<String,Object> map = new HashMap<>();

        Users u = new Users();
        u.setName(ur.getName());
        u.setPassword(ur.getPwd());
        u.setTelephone(ur.getPhone());
        u.setUsername(ur.getUsername());

        int num = hs.insertUsers(u);
        System.out.println("num="+num);

        if(num>0){
            map.put("code","1");
            map.put("info","注册成功！");
        }else {
            map.put("code","0");
            map.put("info","注册失败！");
        }

        return map;
    }

    //主页 分页(动态条件查询)  http://127.0.0.1:8090/house/houseInfo
    @Login(check = true)
    @RequestMapping(value = "/houseInfo" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> houseInfo(@RequestParam Map<String,String> pm) throws Exception{
        //-------------接收数据--------------------
        String title = pm.get("title");//标题
        String price = pm.get("price");//价格
        String price1 = null;
        String price2 = null;
        if(!"".equals(price)){
            String[] parr = price.split("-");
            System.out.println("parr="+ Arrays.toString(parr));
            price1 = parr[0];
            price2 = parr[1];
        }
        String area = pm.get("area");//区域
        String street = pm.get("street");//街道
        String htype = pm.get("htype");//房型
        String size = pm.get("size");//面积
        String size1 = null;
        String size2 = null;
        if(!"".equals(size)){
            String[] sarr = size.split("-");
            System.out.println("sarr="+ Arrays.toString(sarr));
            size1 = sarr[0];
            size2 = sarr[1];
        }
        String pageno = pm.get("pageno");//初始页
        String pagesize = pm.get("pagesize");//每页行数
        System.out.println("pageno="+pageno);
        System.out.println("pagesize="+pagesize);
        int hno=0,hsize=0;
        try{
            hno = Integer.parseInt(pageno);
        }catch (Exception e){
            hno = 1;
        }
        try{
            hsize = Integer.parseInt(pagesize);
        }catch (Exception e){
            hsize = 1;
        }
        //------------------------------------------------------
        //动态查询传参
        Map<String,Object> hm = new HashMap<>();//动态查询方法的参数集合
        if(!"".equals(title)){
            hm.put("title","%"+title+"%");
        }
        if(price1!=null && price2!=null){
            hm.put("minprice",price1);
            hm.put("maxprice",price2);
        }
        if(!"".equals(area)){
            hm.put("district",area);
        }
        if(!"".equals(street)){
            hm.put("street",street);
        }
        if(!"".equals(htype)){
            hm.put("type2",htype);
        }
        if(size1!=null && size2!=null){
            hm.put("minfloorage",size1);
            hm.put("maxfloorage",size2);
        }
        //-------------------------------------------------------
        //分页
        Page<Object> pages = PageHelper.startPage(hno,hsize);
        List<House> list = hs.selectActiveHouse(hm);
        //------------------------------------------------------
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);//数据
        map.put("total",pages.getTotal());//总记录数
        map.put("pageno",pages.getPageNum());//页码
        map.put("pagecount",pages.getPages());//总页数
        System.out.println("list="+list);
        System.out.println("total="+pages.getTotal());
        System.out.println("pageno="+pages.getPageNum());
        System.out.println("pagecount="+pages.getPages());

        return map;
    }

    //单个房屋详情页  http://127.0.0.1:8090/house/houseOneInfo
    @Login(check = true)
    @RequestMapping(value = "/houseOneInfo" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> houseOneInfo(String nid) throws Exception{
        House house = hs.selectByPrimaryKeyHouse(Integer.parseInt(nid));
        Map<String,Object> map = new HashMap<>();
        map.put("house",house);
        return map;
    }

    //分页所有房屋（无条件）  http://127.0.0.1:8090/house/houseAll
    @Login(check = true)
    @RequestMapping(value = "/houseAll" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> houseAll(String pageno,String pagesize) throws Exception{
        //分页
        int hno=0,hsize=0;
        try{
            hno = Integer.parseInt(pageno);
        }catch (Exception e){
            hno = 1;
        }
        try{
            hsize = Integer.parseInt(pagesize);
        }catch (Exception e){
            hsize = 1;
        }
        Page<Object> pages = PageHelper.startPage(hno,hsize);
        List<House> list = hs.selectAll();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);//数据
        map.put("total",pages.getTotal());//总记录数
        map.put("pageno",pages.getPageNum());//页码
        map.put("pagecount",pages.getPages());//总页数

        return map;
    }

    //查询所有房型  http://127.0.0.1:8090/house/allType
    @Login(check = true)
    @RequestMapping(value = "/allType" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> allType() throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Type2> list = hs.selectAllType2();
        map.put("list",list);
        return map;
    }

    //查询所有区域  http://127.0.0.1:8090/house/allDistrict
    @Login(check = true)
    @RequestMapping(value = "/allDistrict" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> allDistrict() throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<District> list = hs.selectAllDistrict();
        map.put("list",list);
        return map;
    }

    //根据区域查询街道  http://127.0.0.1:8090/house/selectStreet
    @Login(check = true)
    @RequestMapping(value = "/selectStreet" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> selectStreet(String id) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Street> district = hs.selectByDistrictIdStreet(Integer.parseInt(id));
        map.put("list",district);
        return map;
    }

    //新增房屋  http://127.0.0.1:8090/house/insertHouse
    @Login(check = true)
    @RequestMapping(value = "/insertHouse" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> insertHouse(@RequestParam Map<String,String> map,HttpSession session) throws Exception{
        Users u = (Users)session.getAttribute("userInfo");//获取用户信息
        House h = new House();
        Map<String,Object> mm = new HashMap<>();

        String title = map.get("title");//标题
        String typeId = map.get("typeId");//房型
        String floorage = map.get("floorage");//面积
        String price = map.get("price");//价格
        try{
            h.setPubdate(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(map.get("pubdate")));//房产日期
        }catch (Exception e){
            map.put("dataError","日期格式错误！");
        }
        String area = map.get("area");//区域
        String streetId = map.get("streetId");//街道
        String contact = map.get("contact");//联系方式
        String description = map.get("description");//详细信息

        h.setUserId(u.getId());
        h.setTypeId(Integer.parseInt(typeId));
        h.setTitle(title);
        h.setDescription(description);
        h.setPrice(Integer.parseInt(price));
        h.setFloorage(Integer.parseInt(floorage));
        h.setContact(contact);
        h.setStreetId(Integer.parseInt(streetId));

        int num = hs.insertSelectiveHouse(h);
        if(num>0){
            mm.put("info","新增成功！");
        }else{
            mm.put("info","新增失败！");
        }

        return mm;
    }

    //修改房屋  http://127.0.0.1:8090/house/updHouse
    @Login(check = true)
    @RequestMapping(value = "/updHouse" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> updHouse(@RequestParam Map<String,String> map,HttpSession session) throws Exception{
        Users u = (Users)session.getAttribute("userInfo");//获取用户信息
        House h = new House();
        Map<String,Object> mm = new HashMap<>();//保存数据

        h.setUserId(u.getId());
        try{
            h.setPubdate(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(map.get("pubdate")));//房产日期
        }catch (Exception e){
            map.put("dataError","日期格式错误！");
        }
        h.setId(Integer.parseInt(map.get("id")));
        h.setTypeId(Integer.parseInt(map.get("typeId")));
        h.setTitle(map.get("title"));
        h.setDescription(map.get("description"));
        h.setPrice(Integer.parseInt(map.get("price")));
        h.setFloorage(Integer.parseInt(map.get("floorage")));
        h.setContact(map.get("contact"));
        h.setStreetId(Integer.parseInt(map.get("streetId")));

        int num = hs.updateByPrimaryKeySelectiveHouse(h);
        if(num>0){
            mm.put("info","修改成功！");
        }else{
            mm.put("info","修改失败！");
        }

        return mm;
    }

    //删除房屋  http://127.0.0.1:8090/house/delHouse
    @Login(check = true)
    @RequestMapping(value = "/delHouse/{hid}" , produces = {"application/json;charset/UTF-8"})
    public Map<String,Object> delHouse(@PathVariable Integer hid) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int num = hs.deleteByPrimaryKeyHouse(hid);
        if(num>0){
            map.put("info","删除成功！");
        }else {
            map.put("info","删除失败！");
        }
        return map;
    }
}
