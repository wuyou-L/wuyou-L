package com.wuyou.userRole.web;

import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.userRole.bean.UserRoleBean;
import com.wuyou.userRole.bean.UserRolePageBean;
import com.wuyou.userRole.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserRoleController,the web actions.
 * @author Autocode
 * 2022-02-26 23:23:31
 */
@Api(tags = "权限接口")
@RestController
@RequestMapping("/userRole")
@CrossOrigin
public class UserRoleController {


	@Autowired
	private UserRoleService userRoleService;

	@ApiOperation("增加权限")
	@PostMapping(value = "/addUserRole",produces = {"application/json;charset=UTF-8"})
	public Object addUserRole(@RequestBody UserRoleBean userRole) {
		return Result.ok().data("rows",userRoleService.addUserRole(userRole));
	}

	@ApiOperation("根据ID删除权限")
	@PostMapping(value = "/deleteUserRole",produces = {"application/json;charset=UTF-8"})
	public Object deleteUserRole(@RequestBody UserRoleBean userRole) {
		return Result.ok().data("rows",userRoleService.deleteUserRole(userRole));
	}

	@ApiOperation("根据ID修改权限")
	@PostMapping(value = "/updateUserRole",produces = {"application/json;charset=UTF-8"})
	public Object updateUserRole(@RequestBody UserRoleBean userRole) {
		return Result.ok().data("rows",userRoleService.updateUserRole(userRole));
	}

	/**

	@PostMapping(value = "/queryUserRoleCount",produces = {"application/json;charset=UTF-8"})
	public Object queryUserRoleCount(@RequestBody UserRoleBean userRole) {
		return Result.ok().data("rows",userRoleService.queryUserRoleCount(userRole));
	}

	*/

	@ApiOperation("查询权限列表")
	@PostMapping(value = "/queryUserRoleList",produces = {"application/json;charset=UTF-8"})
	public Object queryUserRoleList(@RequestBody UserRolePageBean userRole) {
		List<UserRoleBean> list = userRoleService.queryUserRoleList(userRole);
		PaginationBean page = userRole.getPage();
		return Result.ok().data("dataList",list).data("page", page);
	}

	@ApiOperation("根据ID查询单个权限")
	@PostMapping(value = "/querySingleUserRole",produces = {"application/json;charset=UTF-8"})
	public Object querySingleUserRole(@RequestBody UserRoleBean userRole) {
		return Result.ok().data("userRole",userRoleService.querySingleUserRole(userRole));
	}


}
