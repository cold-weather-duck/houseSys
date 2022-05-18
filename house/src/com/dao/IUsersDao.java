package com.dao;

import com.entity.*;
import java.util.*;

public interface IUsersDao {

    Users selectLogin(Map<String,Object> map);

    int insert(Users u);
}
