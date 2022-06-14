package com.wuyou.user.dao;

import com.wuyou.login.pojo.JwtUser;
import com.wuyou.user.bean.UserBean;
import com.wuyou.user.bean.UserPageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * UserMapper,the mappers.
 * @author Autocode
 * 2022-02-26 23:22:43
 */

@Mapper
public interface UserMapper {

	public int addUser(UserBean user);

	public int deleteUser(UserBean user);

	public int updateUser(UserBean user);

	public int queryUserCount(UserBean user);

	public List<UserBean> queryUserList(UserPageBean user);

	public UserBean querySingleUser(UserBean user);

	JwtUser loadByUsername(String username);


}
