package com.dao;

import com.entity.*;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

public interface IHouseDao {

    List<House> selectAll();

    House selectByPrimaryKey(@Param("id") Integer hid);

    List<House> selectActive(Map<String,Object> map);

    int insertSelective(House house);

    int deleteByPrimaryKey(@Param("id")Integer hid);

    int updateByPrimaryKeySelective(House house);
}
