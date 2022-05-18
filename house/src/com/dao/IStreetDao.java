package com.dao;

import com.entity.Street;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface IStreetDao {

    List<Street> selectActive(Map<String,Object> map);

    Street selectByPrimaryKey(Integer sid);

    List<Street> selectByDistrictId(@Param("districtId") int id);
}
