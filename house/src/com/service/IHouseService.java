package com.service;

import com.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface IHouseService {

    List<House> selectAll();

    House selectByPrimaryKeyHouse(Integer hid);//查询单个房屋

    District selectByPrimaryKeyDistrict(Integer id);

    List<House> selectActiveHouse(Map<String,Object> map);//条件查询

    List<Street> selectActiveStreet(Map<String,Object> map);

    Street selectByPrimaryKeyStreet(Integer sid);

    Users selectLoginUsers(Map<String,Object> map);//登录

    int insertUsers(Users u);//注册用户（新增）

    List<Type2> selectAllType2();//查询所有房屋类型

    List<District> selectAllDistrict();

    List<Street> selectByDistrictIdStreet(int id);

    int insertSelectiveHouse(House house);//新增房屋

    int deleteByPrimaryKeyHouse(Integer hid);//删除房屋

    int updateByPrimaryKeySelectiveHouse(House house);//修改房屋
}
