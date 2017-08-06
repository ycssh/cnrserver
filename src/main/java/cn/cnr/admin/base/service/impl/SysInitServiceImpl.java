package cn.cnr.admin.base.service.impl;

import cn.cnr.admin.base.model.SysInit;
import cn.cnr.admin.base.service.SysInitService;
import cn.cnr.admin.base.mapper.SysInitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysInitServiceImpl implements SysInitService {

    @Autowired
    private SysInitMapper sysInitMapper;
	
	public void update(int id, String value) {
		SysInit init = new SysInit();
		init.setId(id);
		init.setValue(value);
		sysInitMapper.updateByPrimaryKeySelective(init);
	}

}
