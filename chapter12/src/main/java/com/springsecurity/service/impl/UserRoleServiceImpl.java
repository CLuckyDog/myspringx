package com.springsecurity.service.impl;

import java.util.List;

import com.springsecurity.dao.RoleDao;
import com.springsecurity.dao.UserDao;
import com.springsecurity.pojo.DatabaseRole;
import com.springsecurity.pojo.DatabaseUser;
import com.springsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
