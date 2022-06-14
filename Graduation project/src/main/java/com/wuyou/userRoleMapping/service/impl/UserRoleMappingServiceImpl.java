package com.wuyou.userRoleMapping.service.impl;

import com.wuyou.userRoleMapping.bean.UserRoleMappingBean;
import com.wuyou.userRoleMapping.bean.UserRoleMappingPageBean;
import com.wuyou.userRoleMapping.dao.UserRoleMappingMapper;
import com.wuyou.userRoleMapping.service.UserRoleMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserRoleMappingService,the service impl class.
 * @author Autocode
 * 2022-03-23 11:20:27
 */
@Service
public class UserRoleMappingServiceImpl implements UserRoleMappingService {

	@Autowired
	private UserRoleMappingMapper userRoleMappingMapper;
	@Transactional
	@Override
	public int addUserRoleMapping(UserRoleMappingBean userRoleMapping) {
		return userRoleMappingMapper.addUserRoleMapping(userRoleMapping);
	}
	@Transactional
	@Override
	public int deleteUserRoleMapping(UserRoleMappingBean userRoleMapping) {
		return userRoleMappingMapper.deleteUserRoleMapping(userRoleMapping);
	}
	@Transactional
	@Override
	public int updateUserRoleMapping(UserRoleMappingBean userRoleMapping) {
		return userRoleMappingMapper.updateUserRoleMapping(userRoleMapping);
	}
	@Transactional
	@Override
	public int queryUserRoleMappingCount(UserRoleMappingBean userRoleMapping) {
		return userRoleMappingMapper.queryUserRoleMappingCount(userRoleMapping);
	}
	@Transactional
	@Override
	public List<UserRoleMappingBean> queryUserRoleMappingList(UserRoleMappingPageBean userRoleMapping) {
		if(userRoleMapping.getPage() != null){
			int totalRows = userRoleMappingMapper.queryUserRoleMappingCount(userRoleMapping);
			userRoleMapping.getPage().setTotalRows(totalRows);
			userRoleMapping.getPage().repaginate();
		}
		return userRoleMappingMapper.queryUserRoleMappingList(userRoleMapping);
	}
	@Transactional
	@Override
	public UserRoleMappingBean querySingleUserRoleMapping(UserRoleMappingBean userRoleMapping) {
		return userRoleMappingMapper.querySingleUserRoleMapping(userRoleMapping);
	}


}
