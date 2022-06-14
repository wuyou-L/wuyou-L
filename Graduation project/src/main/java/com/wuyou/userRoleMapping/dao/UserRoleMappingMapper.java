package com.wuyou.userRoleMapping.dao;
import com.wuyou.userRoleMapping.bean.UserRoleMappingBean;
import com.wuyou.userRoleMapping.bean.UserRoleMappingPageBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * UserRoleMappingMapper,the mappers.
 * @author Autocode
 * 2022-03-23 11:20:27
 */

@Mapper
public interface UserRoleMappingMapper {

	public int addUserRoleMapping(UserRoleMappingBean userRoleMapping);

	public int deleteUserRoleMapping(UserRoleMappingBean userRoleMapping);

	public int updateUserRoleMapping(UserRoleMappingBean userRoleMapping);

	public int queryUserRoleMappingCount(UserRoleMappingBean userRoleMapping);

	public List<UserRoleMappingBean> queryUserRoleMappingList(UserRoleMappingPageBean userRoleMapping);

	public UserRoleMappingBean querySingleUserRoleMapping(UserRoleMappingBean userRoleMapping);


}
