package com.wuyou.userRole.dao;
import com.wuyou.userRole.bean.UserRoleBean;
import com.wuyou.userRole.bean.UserRolePageBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * UserRoleMapper,the mappers.
 * @author Autocode
 * 2022-02-26 23:23:31
 */

@Mapper
public interface UserRoleMapper {

	public int addUserRole(UserRoleBean userRole);

	public int deleteUserRole(UserRoleBean userRole);

	public int updateUserRole(UserRoleBean userRole);

	public int queryUserRoleCount(UserRoleBean userRole);

	public List<UserRoleBean> queryUserRoleList(UserRolePageBean userRole);

	public UserRoleBean querySingleUserRole(UserRoleBean userRole);


}
