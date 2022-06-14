package com.wuyou.sysModule.dao;

import com.wuyou.sysModule.bean.SysModuleBean;
import com.wuyou.sysModule.bean.SysModulePageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * SysModuleMapper,the mappers.
 * @author Autocode
 * 2022-03-23 11:23:04
 */

@Mapper
public interface SysModuleMapper {

	public int addSysModule(SysModuleBean sysModule);

	public int deleteSysModule(SysModuleBean sysModule);

	public int updateSysModule(SysModuleBean sysModule);
	public int updateSysModuleByCode(SysModuleBean sysModule);

	public int querySysModuleCount(SysModuleBean sysModule);

	public List<SysModuleBean> querySysModuleList(SysModulePageBean sysModule);

	public SysModuleBean querySingleSysModule(SysModuleBean sysModule);

	String queryMaxCode(String modCode);

}
