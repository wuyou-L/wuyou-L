package com.wuyou.sysModule.service.impl;

import com.wuyou.sysModule.bean.SysModuleBean;
import com.wuyou.sysModule.bean.SysModulePageBean;
import com.wuyou.sysModule.dao.SysModuleMapper;
import com.wuyou.sysModule.service.SysModuleService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysModuleService,the service impl class.
 * @author Autocode
 * 2022-03-23 11:23:04
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Autowired
	private SysModuleMapper sysModuleMapper;
	@Transactional
	@Override
	public int addSysModule(SysModuleBean sysModule) {
		if (!StringUtil.isNullOrEmpty(sysModule.getMod_parent_code())) {
			String s = sysModuleMapper.queryMaxCode(sysModule.getMod_parent_code());
			if (!StringUtil.isNullOrEmpty(s)) {
				String lastCode = s.substring(s.length() - 3, s.length());
				int cateCodeInt = Integer.parseInt(lastCode) + 1;
				String cateCode = s.substring(0, s.length()-3) + String.format("%03d", cateCodeInt);
				sysModule.setMod_code(cateCode);
			}else {
				sysModule.setMod_code(sysModule.getMod_parent_code() + "001");
			}
		}else {
			return -1;
		}
		return sysModuleMapper.addSysModule(sysModule);
	}
	@Transactional
	@Override
	public int deleteSysModule(SysModuleBean sysModule) {
		return sysModuleMapper.deleteSysModule(sysModule);
	}
	@Transactional
	@Override
	public int updateSysModule(SysModuleBean sysModule) {
		return sysModuleMapper.updateSysModule(sysModule);
	}

	@Transactional
	@Override
	public int updateSysModuleByCode(SysModuleBean sysModule) {
		return sysModuleMapper.updateSysModuleByCode(sysModule);
	}
	@Transactional
	@Override
	public int querySysModuleCount(SysModuleBean sysModule) {
		return sysModuleMapper.querySysModuleCount(sysModule);
	}
	@Transactional
	@Override
	public List<SysModuleBean> querySysModuleList(SysModulePageBean sysModule) {
		if(sysModule.getPage() != null){
			int totalRows = sysModuleMapper.querySysModuleCount(sysModule);
			sysModule.getPage().setTotalRows(totalRows);
			sysModule.getPage().repaginate();
		}
		return sysModuleMapper.querySysModuleList(sysModule);
	}
	@Transactional
	@Override
	public SysModuleBean querySingleSysModule(SysModuleBean sysModule) {
		return sysModuleMapper.querySingleSysModule(sysModule);
	}


}
