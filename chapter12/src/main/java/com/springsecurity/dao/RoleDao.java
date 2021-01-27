package com.springsecurity.dao;

import java.util.List;

import com.springsecurity.pojo.DatabaseRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {
	
	public List<DatabaseRole> findRolesByUserName(String userName);
}
