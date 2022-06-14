package com.wuyou.sysModule.web;

import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.sysModule.bean.SysModuleBean;
import com.wuyou.sysModule.bean.SysModulePageBean;
import com.wuyou.sysModule.service.SysModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * SysModuleController,the web actions.
 * @author Autocode
 * 2022-03-23 11:23:04
 */
@Api(tags = "系统功能模块接口")
@RestController
@RequestMapping("/sysModule")
@CrossOrigin    // 解决跨域
public class SysModuleController {


	@Autowired
	private SysModuleService sysModuleService;

	@ApiOperation("增加系统功能模块")
	@PostMapping(value = "/addSysModule",produces = {"application/json;charset=UTF-8"})
	public Object addSysModule(@RequestBody SysModuleBean sysModule) {
		return Result.ok().data("rows",sysModuleService.addSysModule(sysModule));
	}

	@ApiOperation("根据ID删除系统功能模块")
	@PostMapping(value = "/deleteSysModule",produces = {"application/json;charset=UTF-8"})
	public Object deleteSysModule(@RequestBody SysModuleBean sysModule) {
		return Result.ok().data("rows",sysModuleService.deleteSysModule(sysModule));
	}

	@ApiOperation("根据ID修改系统功能模块")
	@PostMapping(value = "/updateSysModule",produces = {"application/json;charset=UTF-8"})
	public Object updateSysModule(@RequestBody SysModuleBean sysModule) {
		return Result.ok().data("rows",sysModuleService.updateSysModule(sysModule));
	}

	@ApiOperation("更新模块状态")
	@PostMapping(value = "/updateStatus",produces = {"application/json;charset=UTF-8"})
	public Object updateStatus(@RequestBody SysModuleBean sysModule) {
		try {
			updateStatusChild(sysModule);
			return Result.ok().data("rows", 1);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error().message("修改失败！");
		}
	}

	/**

	@PostMapping(value = "/querySysModuleCount",produces = {"application/json;charset=UTF-8"})
	public Object querySysModuleCount(@RequestBody SysModuleBean sysModule) {
		return Result.ok().data("rows",sysModuleService.querySysModuleCount(sysModule));
	}

	*/

	@ApiOperation("查询系统功能模块列表")
	@PostMapping(value = "/querySysModuleList",produces = {"application/json;charset=UTF-8"})
	public Object querySysModuleList(@RequestBody SysModulePageBean sysModule) {
		List<SysModuleBean> list = sysModuleService.querySysModuleList(sysModule);
		PaginationBean page = sysModule.getPage();
		return Result.ok().data("dataList",list).data("page", page);
	}

	@ApiOperation("根据ID查询单个系统功能模块")
	@PostMapping(value = "/querySingleSysModule",produces = {"application/json;charset=UTF-8"})
	public Object querySingleSysModule(@RequestBody SysModuleBean sysModule) {
		return Result.ok().data("sysModule",sysModuleService.querySingleSysModule(sysModule));
	}

	@PostMapping("/treeList")
	public Object treeList(@RequestBody SysModuleBean sysModuleBean){
		System.out.println(sysModuleBean.toString());
		SysModuleBean sysModule = sysModuleService.querySingleSysModule(sysModuleBean);
		SysModuleBean recursion = recursion(sysModule);
		List<SysModuleBean> sysModuleBeans = new ArrayList<>();
		sysModuleBeans.add(recursion);
		return Result.ok().data("dataList",sysModuleBeans);
	}

	private SysModuleBean recursion(SysModuleBean sysModuleBean){
		SysModulePageBean sysModulePageBean = new SysModulePageBean();
		sysModulePageBean.setMod_parent_code(sysModuleBean.getMod_code());
		List<SysModuleBean> sysModuleBeans = sysModuleService.querySysModuleList(sysModulePageBean);
		if (sysModuleBeans != null && sysModuleBeans.size() > 0) {
			sysModuleBean.setSysModuleBeans(sysModuleBeans);
			for(SysModuleBean data : sysModuleBeans){
				recursion(data);
			}
		}
		return sysModuleBean;
	}

	private void updateStatusChild(SysModuleBean sysModuleBean){
		sysModuleService.updateSysModule(sysModuleBean);
		if (sysModuleBean.getIs_using() == 0) {
			if (sysModuleBean.getSysModuleBeans() != null && sysModuleBean.getSysModuleBeans().size() > 0) {
				sysModuleBean.getSysModuleBeans().forEach(data -> {
					data.setIs_using(0);
					updateStatusChild(data);
				});
			}
		}else {
			SysModuleBean sysModuleBean1 = new SysModuleBean();
			sysModuleBean1.setMod_code(sysModuleBean.getMod_parent_code());
			sysModuleBean1.setIs_using(1);
			System.out.println("======================================");
			System.out.println(sysModuleBean1.toString());
			sysModuleService.updateSysModuleByCode(sysModuleBean1);
		}
	}


}
