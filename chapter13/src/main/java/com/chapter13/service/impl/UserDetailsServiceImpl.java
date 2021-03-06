package com.chapter13.service.impl;

import com.chapter13.pojo.DatabaseRole;
import com.chapter13.pojo.DatabaseUser;
import com.chapter13.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /*获取数据库用户信息*/
        DatabaseUser dbUser=userRoleService.getUserByName(userName);

        /*获取数据库角色信息*/
        List<DatabaseRole> roleList=userRoleService.findRolesByUserName(userName);
        return changeToUser(dbUser,roleList);
    }

    private UserDetails changeToUser(DatabaseUser dbUser,List<DatabaseRole> roleList){
        /*权限列表*/
        List<GrantedAuthority> authorityList=new ArrayList<>();
        /*赋予查询到的角色*/
        for (DatabaseRole role:roleList){
            GrantedAuthority authority= new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }
        /*创建UserDetails对象，设置用户名、密码和权限*/
        UserDetails userDetails=new User(dbUser.getUserName(),dbUser.getPwd(),authorityList);
        return userDetails;
    }
}
