package com.chapter13.service;

import com.chapter13.pojo.DatabaseRole;
import com.chapter13.pojo.DatabaseUser;

import java.util.List;


public interface UserRoleService {

	 DatabaseUser getUserByName(String userName);

	 List<DatabaseRole> findRolesByUserName(String userName);
}
