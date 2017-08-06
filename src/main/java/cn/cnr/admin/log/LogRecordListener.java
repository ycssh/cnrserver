package cn.cnr.admin.log;

import cn.cnr.admin.spring.SpringUtils;
import cn.cnr.admin.base.mapper.SysOperLogMapper;
import cn.cnr.admin.base.model.SysOperLog;
import org.springframework.context.ApplicationListener;

public class LogRecordListener implements ApplicationListener<LogRecordEvent> {

	private static SysOperLogMapper mapper;
	
	public void onApplicationEvent(final LogRecordEvent event) {
		if(mapper==null){
			mapper = SpringUtils.getBean(SysOperLogMapper.class);
		}
		new Thread(new Runnable() {
			
			public void run() {
				final SysOperLog log = event.getSysOperLog();
				mapper.insert(log);
			}
		}).start();
	}
	
	
}
