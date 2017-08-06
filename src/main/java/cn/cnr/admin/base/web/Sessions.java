package cn.cnr.admin.base.web;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.model.User;
import org.apache.shiro.SecurityUtils;

public class Sessions {
	public User getUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
	}
	public String getIP(){
		return SecurityUtils.getSubject().getSession().getHost();
	}
}
