package com.chapter13.service;


import com.chapter13.pojo.User;

public interface ActiveMqUserService {

	public void sendUser(User user);

	public void receiveUser(User user);
}
