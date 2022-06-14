package com.wuyou.fileInfo.dao;

import com.wuyou.fileInfo.bean.FileInfoBean;
import com.wuyou.fileInfo.bean.FileInfoPageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * FileInfoMapper,the mappers.
 * @author Autocode
 * 2022-04-12 20:11:58
 */

@Mapper
public interface FileInfoMapper {

	public int addFileInfo(FileInfoBean fileInfo);

	public int deleteFileInfo(FileInfoBean fileInfo);
	int deleteFileInfoById(int id);
	public int updateFileInfo(FileInfoBean fileInfo);

	public int queryFileInfoCount(FileInfoBean fileInfo);

	public List<FileInfoBean> queryFileInfoList(FileInfoPageBean fileInfo);

	public FileInfoBean querySingleFileInfo(FileInfoBean fileInfo);


}
