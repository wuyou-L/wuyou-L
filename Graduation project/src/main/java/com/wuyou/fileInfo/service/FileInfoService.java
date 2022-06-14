package com.wuyou.fileInfo.service;
import com.wuyou.fileInfo.bean.FileInfoBean;
import com.wuyou.fileInfo.bean.FileInfoPageBean;
import java.util.List;
/**
 * FileInfoService,the services.
 * @author Autocode
 * 2022-04-12 20:11:58
 */
public interface FileInfoService {

	public int addFileInfo(FileInfoBean fileInfo);

	public int addFileInfoList(List<FileInfoBean> fileInfo, int id);

	public int deleteFileInfo(FileInfoBean fileInfo);
	public int deleteFileInfoById(int id);

	public int updateFileInfo(FileInfoBean fileInfo);

	public int queryFileInfoCount(FileInfoBean fileInfo);

	public List<FileInfoBean> queryFileInfoList(FileInfoPageBean fileInfo);

	public FileInfoBean querySingleFileInfo(FileInfoBean fileInfo);


}
