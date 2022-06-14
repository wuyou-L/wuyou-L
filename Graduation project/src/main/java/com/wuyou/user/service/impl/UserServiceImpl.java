package com.wuyou.user.service.impl;

import com.wuyou.user.bean.UserBean;
import com.wuyou.user.bean.UserPageBean;
import com.wuyou.user.dao.UserMapper;
import com.wuyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService,the service impl class.
 * @author Autocode
 * 2022-02-26 23:22:43
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Transactional
	@Override
	public int addUser(UserBean user) {
		if (user.getUser_img_name() == null || "".equals(user.getUser_img_name())) {
			user.setUser_img_name("/2.jpg");
		}
		return userMapper.addUser(user);
	}
	@Transactional
	@Override
	public int deleteUser(UserBean user) {
		return userMapper.deleteUser(user);
	}
	@Transactional
	@Override
	public int updateUser(UserBean user) {
		return userMapper.updateUser(user);
	}
	@Transactional
	@Override
	public int queryUserCount(UserBean user) {
		return userMapper.queryUserCount(user);
	}
	@Transactional
	@Override
	public List<UserBean> queryUserList(UserPageBean user) {
		if(user.getPage() != null){
			int totalRows = userMapper.queryUserCount(user);
			user.getPage().setTotalRows(totalRows);
			user.getPage().repaginate();
		}
		return userMapper.queryUserList(user);
	}
	@Transactional
	@Override
	public UserBean querySingleUser(UserBean user) {
		return userMapper.querySingleUser(user);
	}


}
