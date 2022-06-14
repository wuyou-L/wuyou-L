package com.wuyou.fileInfo.web;

import com.wuyou.base.bean.PaginationBean;
import com.wuyou.base.resp.Result;
import com.wuyou.fileInfo.bean.FileInfoBean;
import com.wuyou.fileInfo.bean.FileInfoPageBean;
import com.wuyou.fileInfo.service.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FileInfoController,the web actions.
 * @author Autocode
 * 2022-04-12 20:11:58
 */
@Api(tags = "文件信息接口")
@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

	@Autowired
	private FileInfoService fileInfoService;

	@ApiOperation("增加文件信息")
	@PostMapping(value = "/addFileInfo",produces = {"application/json;charset=UTF-8"})
	public Object addFileInfo(@RequestBody FileInfoBean fileInfo) {
		return Result.ok().data("rows",fileInfoService.addFileInfo(fileInfo));
	}

	@PostMapping(value = "/addFileInfoList",produces = {"application/json;charset=UTF-8"})
	public Object addFileInfoList(@RequestBody List<FileInfoBean> fileInfoList) {
		if (fileInfoList != null && fileInfoList.size() > 0) {
			fileInfoList.forEach(data -> {
				fileInfoService.addFileInfo(data);
			});
		}
		return Result.ok().data("rows",1);
	}

	@ApiOperation("根据ID删除文件信息")
	@PostMapping(value = "/deleteFileInfo",produces = {"application/json;charset=UTF-8"})
	public Object deleteFileInfo(@RequestBody FileInfoBean fileInfo) {
		return Result.ok().data("rows",fileInfoService.deleteFileInfo(fileInfo));
	}

	@ApiOperation("根据ID修改文件信息")
	@PostMapping(value = "/updateFileInfo",produces = {"application/json;charset=UTF-8"})
	public Object updateFileInfo(@RequestBody FileInfoBean fileInfo) {
		return Result.ok().data("rows",fileInfoService.updateFileInfo(fileInfo));
	}

	/**

	@PostMapping(value = "/queryFileInfoCount",produces = {"application/json;charset=UTF-8"})
	public Object queryFileInfoCount(@RequestBody FileInfoBean fileInfo) {
		return Result.ok().data("rows",fileInfoService.queryFileInfoCount(fileInfo));
	}

	*/

	@ApiOperation("查询文件信息列表")
	@PostMapping(value = "/queryFileInfoList",produces = {"application/json;charset=UTF-8"})
	public Object queryFileInfoList(@RequestBody FileInfoPageBean fileInfo) {
		List<FileInfoBean> list = fileInfoService.queryFileInfoList(fileInfo);
		PaginationBean page = fileInfo.getPage();
		return Result.ok().data("dataList",list).data("page", page);
	}

	@ApiOperation("根据ID查询单个文件信息")
	@PostMapping(value = "/querySingleFileInfo",produces = {"application/json;charset=UTF-8"})
	public Object querySingleFileInfo(@RequestBody FileInfoBean fileInfo) {
		return Result.ok().data("fileInfo",fileInfoService.querySingleFileInfo(fileInfo));
	}


}
