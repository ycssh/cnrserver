package cn.cnr.admin.base.web.exception;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.mapper.SysOperLogMapper;
import cn.cnr.admin.base.model.SysOperLog;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.service.UserService;
import cn.cnr.admin.spring.SpringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DefaultExceptionHandler extends SimpleMappingExceptionResolver {

	@Resource
	private UserService userService; 
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();

		String requestType = request.getHeader("X-Requested-With");  
		boolean ajaxMethod = false;
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {  
            ajaxMethod = true;  
        } else {  
            ajaxMethod = false;  
        }  
		if(UnauthorizedException.class.getName().equals(ex.getClass().getName())){
			ModelAndView mv = new ModelAndView();
	        mv.addObject("exception", ex);
	        mv.setViewName("unauthorized");
	        SysOperLog log = new SysOperLog();
	        Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			log.setIp(session.getHost());
			User user = (User) session.getAttribute(Constants.CURRENT_USER);
			log.setUsername(user.getUsername());
			log.setContent("越权访问:"+ex.getMessage());
			log.setOperType("越权");
			log.setOperModel("越权访问");
			SysOperLogMapper mapper = SpringUtils.getBean(SysOperLogMapper.class);
			mapper.insert(log);
			userService.updateState(1, user.getUsername());
			if(ajaxMethod){
	        	 // 增加异常判断
	        	response.setContentType("application/json;charset=utf-8");
	            try {
	            	 PrintWriter writer = response.getWriter();  
	                 writer.write("非常抱歉，您没有访问权限");  
					writer.flush(); 
				} catch (IOException e) {
					e.printStackTrace();
				}  
	            return null;  
	        }
	        return mv;
		}

        if(ajaxMethod){
        	 // 增加异常判断
        	response.setContentType("application/json;charset=utf-8");
            try {
            	 PrintWriter writer = response.getWriter();  
                 writer.write("非常抱歉，服务器错误");  
				writer.flush(); 
			} catch (IOException e) {
				e.printStackTrace();
			}  
            return null;  
        }
		return new ModelAndView("error");
	}
}