package com.wuyou.userRoleMapping.web;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.userRoleMapping.bean.UserRoleMappingBean;
import com.wuyou.userRoleMapping.bean.UserRoleMappingPageBean;
import com.wuyou.userRoleMapping.service.UserRoleMappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserRoleMappingController,the web actions.
 *
 * @author Autocode
 * 2022-03-23 11:20:27
 */
@Api(tags = "权限和功能映射接口")
@RestController
@RequestMapping("/userRoleMapping")
@CrossOrigin
public class UserRoleMappingController {


    @Autowired
    private UserRoleMappingService userRoleMappingService;

    @ApiOperation("增加权限和功能映射")
    @PostMapping(value = "/addUserRoleMapping", produces = {"application/json;charset=UTF-8"})
    public Object addUserRoleMapping(@RequestBody UserRoleMappingBean userRoleMapping) {
        userRoleMappingService.deleteUserRoleMapping(userRoleMapping);
        return Result.ok().data("rows", userRoleMappingService.addUserRoleMapping(userRoleMapping));
    }

    @ApiOperation("增加权限和功能映射")
    @PostMapping(value = "/addUserRoleMappingList", produces = {"application/json;charset=UTF-8"})
    public Object addUserRoleMappingList(@RequestBody JSONObject jsonObject) {
        if (jsonObject != null && jsonObject.size() > 0) {
            int role_id = (Integer) jsonObject.get("role_id");
            List<String> modList = (List<String>) jsonObject.get("mod_list");
            if (modList != null) {
                AtomicInteger i = new AtomicInteger();
                modList.forEach(data -> {
                    UserRoleMappingBean userRoleMappingBean = new UserRoleMappingBean();
                    userRoleMappingBean.setRole_code(role_id);
                    userRoleMappingBean.setMod_code(data);
                    int i1 = userRoleMappingService.addUserRoleMapping(userRoleMappingBean);
                    if (i1 > 0) {
                        i.getAndIncrement();
                    }
                });
                return Result.ok().data("rows", i);
            }
        }
        return Result.error();
    }

    @ApiOperation("根据ID删除权限和功能映射")
    @PostMapping(value = "/deleteUserRoleMapping", produces = {"application/json;charset=UTF-8"})
    public Object deleteUserRoleMapping(@RequestBody UserRoleMappingBean userRoleMapping) {
        return Result.ok().data("rows", userRoleMappingService.deleteUserRoleMapping(userRoleMapping));
    }

    @ApiOperation("根据ID修改权限和功能映射")
    @PostMapping(value = "/updateUserRoleMapping", produces = {"application/json;charset=UTF-8"})
    public Object updateUserRoleMapping(@RequestBody UserRoleMappingBean userRoleMapping) {
        return Result.ok().data("rows", userRoleMappingService.updateUserRoleMapping(userRoleMapping));
    }

    /**
     * @PostMapping(value = "/queryUserRoleMappingCount",produces = {"application/json;charset=UTF-8"})
     * public Object queryUserRoleMappingCount(@RequestBody UserRoleMappingBean userRoleMapping) {
     * return Result.ok().data("rows",userRoleMappingService.queryUserRoleMappingCount(userRoleMapping));
     * }
     */

    @ApiOperation("查询权限和功能映射列表")
    @PostMapping(value = "/queryUserRoleMappingList", produces = {"application/json;charset=UTF-8"})
    public Object queryUserRoleMappingList(@RequestBody UserRoleMappingPageBean userRoleMapping) {
        List<UserRoleMappingBean> list = userRoleMappingService.queryUserRoleMappingList(userRoleMapping);
        PaginationBean page = userRoleMapping.getPage();
        return Result.ok().data("dataList", list).data("page", page);
    }

    @ApiOperation("根据ID查询单个权限和功能映射")
    @PostMapping(value = "/querySingleUserRoleMapping", produces = {"application/json;charset=UTF-8"})
    public Object querySingleUserRoleMapping(@RequestBody UserRoleMappingBean userRoleMapping) {
        return Result.ok().data("userRoleMapping", userRoleMappingService.querySingleUserRoleMapping(userRoleMapping));
    }


}
