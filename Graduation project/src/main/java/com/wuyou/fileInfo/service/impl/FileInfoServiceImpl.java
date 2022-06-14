package com.wuyou.fileInfo.service.impl;

import com.wuyou.fileInfo.bean.FileInfoBean;
import com.wuyou.fileInfo.bean.FileInfoPageBean;
import com.wuyou.fileInfo.dao.FileInfoMapper;
import com.wuyou.fileInfo.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FileInfoService,the service impl class.
 * @author Autocode
 * 2022-04-12 20:11:58
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	public int addFileInfo(FileInfoBean fileInfo) {
		return fileInfoMapper.addFileInfo(fileInfo);
	}

	@Transactional
	@Override
	public int addFileInfoList(List<FileInfoBean> fileInfo, int id) {
		if (fileInfo != null && fileInfo.size() > 0) {
			fileInfo.forEach(data -> {
				data.setRel_id(id);
				fileInfoMapper.addFileInfo(data);
			});
		}
		return 1;
	}
	@Transactional
	public int deleteFileInfo(FileInfoBean fileInfo) {
		return fileInfoMapper.deleteFileInfo(fileInfo);
	}
	@Transactional
	@Override
	public int deleteFileInfoById(int id) {
		return fileInfoMapper.deleteFileInfoById(id);
	}
	@Transactional
	@Override
	public int updateFileInfo(FileInfoBean fileInfo) {
		return fileInfoMapper.updateFileInfo(fileInfo);
	}
	@Transactional
	@Override
	public int queryFileInfoCount(FileInfoBean fileInfo) {
		return fileInfoMapper.queryFileInfoCount(fileInfo);
	}

	@Transactional
	@Override
	public List<FileInfoBean> queryFileInfoList(FileInfoPageBean fileInfo) {
		if(fileInfo.getPage() != null){
			int totalRows = fileInfoMapper.queryFileInfoCount(fileInfo);
			fileInfo.getPage().setTotalRows(totalRows);
			fileInfo.getPage().repaginate();
		}
		return fileInfoMapper.queryFileInfoList(fileInfo);
	}
	@Transactional
	@Override
	public FileInfoBean querySingleFileInfo(FileInfoBean fileInfo) {
		return fileInfoMapper.querySingleFileInfo(fileInfo);
	}


}
