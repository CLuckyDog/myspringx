package com.chapter13.dao;

import com.chapter13.pojo.DatabaseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	
	public DatabaseUser getUser(String userName);
}
