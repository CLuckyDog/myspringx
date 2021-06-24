package com.springsecurity.dao;

import com.springsecurity.pojo.DatabaseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	
	public DatabaseUser getUser(String userName);
	public boolean addUser(DatabaseUser user);
}
