package com.wuyou.user.service;
import com.wuyou.user.bean.UserBean;
import com.wuyou.user.bean.UserPageBean;
import java.util.List;
/**
 * UserService,the services.
 * @author Autocode
 * 2022-02-26 23:22:43
 */
public interface UserService {

	public int addUser(UserBean user);

	public int deleteUser(UserBean user);

	public int updateUser(UserBean user);

	public int queryUserCount(UserBean user);

	public List<UserBean> queryUserList(UserPageBean user);

	public UserBean querySingleUser(UserBean user);


}
