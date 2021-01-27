package com.springsecurity.service;

import com.springsecurity.pojo.DatabaseRole;
import com.springsecurity.pojo.DatabaseUser;

import java.util.List;


public interface UserRoleService {

	public DatabaseUser getUserByName(String userName);

	public List<DatabaseRole> findRolesByUserName(String userName);
}
