package com.dao;

import com.entity.District;
import com.entity.Street;
import java.util.List;

public interface IDistrictDao {

    List<District> selectAll();

    District selectByPrimaryKey(Integer id);

}
