package cn.cnr.admin.init;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.model.SysInit;
import cn.cnr.admin.spring.SpringUtils;
import cn.cnr.admin.base.mapper.SysInitMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InitDataListener  implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
//		SysInitMapper sysInitMapper = SpringUtils.getBean(SysInitMapper.class);
//		List<SysInit> list = sysInitMapper.selectAll();
//		Map<String, String> inits = new HashMap<String, String>();
//		for (SysInit init : list) {
//			inits.put(init.getType(), init.getValue());
//		}
//		Constants.cache.put("sys_init", inits);
//		Constants.cache.put("sys_initlist", list);
//
//		PrepaidCardMapper prepaidCardMapper = SpringUtils.getBean(PrepaidCardMapper.class);
//		PromotersMapper promotersMapper = SpringUtils.getBean(PromotersMapper.class);
//		Integer batch = prepaidCardMapper.selectMaxBatch();
//		Long cardNo = prepaidCardMapper.selectMaxCardNo();
//		if(batch!=null){
//			Constants.batch = new AtomicInteger(batch+1);
//		}else{
//			Constants.batch = new AtomicInteger(1);
//		}
//		Integer promotersNo = promotersMapper.selectMaxCardNo();
//		if(promotersNo!=null){
//			Constants.promotersNo = new AtomicInteger(promotersNo+1);
//		}else{
//			Constants.promotersNo = new AtomicInteger(10001);
//		}
//		if(cardNo!=null&&cardNo!=0){
//			Constants.cardNo = new AtomicLong(cardNo+1);
//		}else{
//			Constants.cardNo = new AtomicLong(100000);
//		}
	}

}