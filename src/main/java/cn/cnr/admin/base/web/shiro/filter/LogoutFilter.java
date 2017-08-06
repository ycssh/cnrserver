package cn.cnr.admin.base.web.shiro.filter;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.model.SysOperLog;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.spring.SpringUtils;
import cn.cnr.admin.base.mapper.SysOperLogMapper;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;

public class LogoutFilter extends
		org.apache.shiro.web.filter.authc.LogoutFilter {

	
	public String getRedirectUrl(ServletRequest request,
			ServletResponse response, Subject subject) {
		SysOperLog _log = new SysOperLog();
		_log.setIp(request.getRemoteHost());
		Session session = subject.getSession();
		User user = (User) session.getAttribute(Constants.CURRENT_USER);
		if (user != null) {
			_log.setUsername(user.getUsername());
			_log.setContent("退出登录");
			_log.setOperType("退出登录");
			_log.setOperateTime(new Date());
			_log.setOperModel("退出登录");
			SysOperLogMapper mapper = SpringUtils.getBean(SysOperLogMapper.class);
			mapper.insert(_log);
		}
		return "login";
	}
}
