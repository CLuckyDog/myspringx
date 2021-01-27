package com.chapter13.dao;

import com.chapter13.pojo.DatabaseRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
	
	public List<DatabaseRole> findRolesByUserName(String userName);
}
