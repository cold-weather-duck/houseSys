package com.service.impl;

import com.dao.*;
import com.entity.*;
import com.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("houseService")
public class HouseService implements IHouseService{

    @Autowired
    private IDistrictDao districtDao;
    @Autowired
    private IHouseDao houseDao;
    @Autowired
    private IStreetDao streetDao;
    @Autowired
    private IType2Dao type2Dao;
    @Autowired
    private IUsersDao usersDao;

    @Override
    public List<House> selectAll() {
        return houseDao.selectAll();
    }

    @Override
    public House selectByPrimaryKeyHouse(Integer hid) {
        return houseDao.selectByPrimaryKey(hid);
    }

    @Override
    public District selectByPrimaryKeyDistrict(Integer id) {
        return districtDao.selectByPrimaryKey(id);
    }

    @Override
    public List<House> selectActiveHouse(Map<String, Object> map) {
        return houseDao.selectActive(map);
    }

    @Override
    public List<Street> selectActiveStreet(Map<String, Object> map) {
        return streetDao.selectActive(map);
    }

    @Override
    public Street selectByPrimaryKeyStreet(Integer sid) {
        return streetDao.selectByPrimaryKey(sid);
    }

    @Override
    public Users selectLoginUsers(Map<String, Object> map) {
        return usersDao.selectLogin(map);
    }

    @Override
    public int insertUsers(Users u) {
        return usersDao.insert(u);
    }

    @Override
    public List<Type2> selectAllType2() {
        return type2Dao.selectAll();
    }

    @Override
    public List<District> selectAllDistrict() {
        return districtDao.selectAll();
    }

    @Override
    public List<Street> selectByDistrictIdStreet(int id) {
        return streetDao.selectByDistrictId(id);
    }

    @Override
    public int insertSelectiveHouse(House house) {
        return houseDao.insertSelective(house);
    }

    @Override
    public int deleteByPrimaryKeyHouse(Integer hid) {
        return houseDao.deleteByPrimaryKey(hid);
    }

    @Override
    public int updateByPrimaryKeySelectiveHouse(House house) {
        return houseDao.updateByPrimaryKeySelective(house);
    }


}
