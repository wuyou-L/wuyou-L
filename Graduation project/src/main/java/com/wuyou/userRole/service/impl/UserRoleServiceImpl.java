package com.wuyou.userRole.service.impl;

import com.wuyou.userRole.bean.UserRoleBean;
import com.wuyou.userRole.bean.UserRolePageBean;
import com.wuyou.userRole.dao.UserRoleMapper;
import com.wuyou.userRole.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserRoleService,the service impl class.
 * @author Autocode
 * 2022-02-26 23:23:31
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Transactional
	@Override
	public int addUserRole(UserRoleBean userRole) {
		return userRoleMapper.addUserRole(userRole);
	}
	@Transactional
	@Override
	public int deleteUserRole(UserRoleBean userRole) {
		return userRoleMapper.deleteUserRole(userRole);
	}
	@Transactional
	@Override
	public int updateUserRole(UserRoleBean userRole) {
		return userRoleMapper.updateUserRole(userRole);
	}
	@Transactional
	@Override
	public int queryUserRoleCount(UserRoleBean userRole) {
		return userRoleMapper.queryUserRoleCount(userRole);
	}
	@Transactional
	@Override
	public List<UserRoleBean> queryUserRoleList(UserRolePageBean userRole) {
		if(userRole.getPage() != null){
			int totalRows = userRoleMapper.queryUserRoleCount(userRole);
			userRole.getPage().setTotalRows(totalRows);
			userRole.getPage().repaginate();
		}
		return userRoleMapper.queryUserRoleList(userRole);
	}
	@Transactional
	@Override
	public UserRoleBean querySingleUserRole(UserRoleBean userRole) {
		return userRoleMapper.querySingleUserRole(userRole);
	}


}
