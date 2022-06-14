package com.wuyou.sysModule.service;
import com.wuyou.sysModule.bean.SysModuleBean;
import com.wuyou.sysModule.bean.SysModulePageBean;
import java.util.List;
/**
 * SysModuleService,the services.
 * @author Autocode
 * 2022-03-23 11:23:04
 */
public interface SysModuleService {

	public int addSysModule(SysModuleBean sysModule);

	public int deleteSysModule(SysModuleBean sysModule);

	public int updateSysModule(SysModuleBean sysModule);
	public int updateSysModuleByCode(SysModuleBean sysModule);

	public int querySysModuleCount(SysModuleBean sysModule);

	public List<SysModuleBean> querySysModuleList(SysModulePageBean sysModule);

	public SysModuleBean querySingleSysModule(SysModuleBean sysModule);


}
