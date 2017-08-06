package cn.cnr.admin.base.web.shiro.filter;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.service.UserService;
import cn.cnr.admin.base.mapper.SysOperLogMapper;
import cn.cnr.admin.base.model.SysOperLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;
import java.util.Map;

public class SysUserFilter extends PathMatchingFilter {

    @Resource
    private UserService userService;
	@Resource
	private SysOperLogMapper sysOperLogMapper;

    
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject(); 
        Session session = subject.getSession();

        Map<String,String> inits = (Map<String, String>) Constants.cache.get("sys_init");
        String sessionTimeout = inits.get("sessionTimeout");
        session.setTimeout(Integer.parseInt(sessionTimeout)*60*1000);
        if(session.getAttribute(Constants.CURRENT_USER)==null){
        	String currentIP = request.getRemoteHost();
        	if("0:0:0:0:0:0:0:1".equals(currentIP)){
        		currentIP = "127.0.0.1";
        	}
        	User user = userService.findByUsername(username);
        	session.setAttribute(Constants.CURRENT_USER, user);
			SysOperLog _log = new SysOperLog();
			_log.setOperType("登录");
			_log.setIp(currentIP);
			_log.setUsername(user.getUsername());
			_log.setContent("登录");
			_log.setOperModel("登录");
			_log.setOperateTime(new Date());
			sysOperLogMapper.insert(_log);
        }else{
        	User user = (User) session.getAttribute(Constants.CURRENT_USER);
        	request.setAttribute(Constants.CURRENT_USER, user);
        }
        return true;
    }
}
