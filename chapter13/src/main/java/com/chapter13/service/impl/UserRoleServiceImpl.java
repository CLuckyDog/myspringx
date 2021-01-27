package com.chapter13.service.impl;

import com.chapter13.dao.RoleDao;
import com.chapter13.dao.UserDao;
import com.chapter13.pojo.DatabaseRole;
import com.chapter13.pojo.DatabaseUser;
import com.chapter13.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserDao userDao = null;
	@Autowired
	private RoleDao roleDao = null;

	@Override
//	@Cacheable(value = "redisCache", key = "'redis_user_'+#userName")
	@Transactional
	public DatabaseUser getUserByName(String userName) {
		return userDao.getUser(userName);
	}

	@Override
//	@Cacheable(value = "redisCache", key = "'redis_user_role_'+#userName")
	@Transactional
	public List<DatabaseRole> findRolesByUserName(String userName) {
		return roleDao.findRolesByUserName(userName);
	}

}
