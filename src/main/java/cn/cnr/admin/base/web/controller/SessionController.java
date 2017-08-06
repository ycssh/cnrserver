package cn.cnr.admin.base.web.controller;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.entity.Msg;
import cn.cnr.admin.base.model.OnlineUser;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.util.PageResult;
import cn.cnr.admin.base.util.Pagination;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/sessions")
public class SessionController {
	@Autowired
	private SessionDAO sessionDAO;

	@RequestMapping()
	@RequiresPermissions("admin:session")
	public String list(Model model) {
		return "sessions/list";
	}

	@RequestMapping("/list")
	@RequiresPermissions("admin:session")
	public @ResponseBody PageResult<OnlineUser> list(User user, Pagination page) {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		int start = page.getStart();
		int length = page.getLength();
		List<Session> list = new ArrayList<Session>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Session s : sessions) {
			if (s.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
				list.add(s);
			}
		}
		
		List<OnlineUser> lists = new ArrayList<OnlineUser>();
		int total = list.size();
		for(int i=start;i<start+length&&start+i<total;i++){
			Session s = list.get(i);
	        PrincipalCollection principalCollection =
	                (PrincipalCollection) s.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
	        String userName = (String) principalCollection.getPrimaryPrincipal();
	        if(StringUtils.hasLength(user.getUsername())){
	        	if(userName.equals(user.getUsername())){
		        	lists.add(new OnlineUser(s.getId().toString(),userName,s.getHost(),sdf.format(s.getLastAccessTime())));
		        	break;
	        	}
	        }else{
	        	lists.add(new OnlineUser(s.getId().toString(),userName,s.getHost(),sdf.format(s.getStartTimestamp())));
	        }
		}
		if(lists.size()==1)
			total=1;
		if(lists.size()==0)
			total=0;
		return new PageResult<OnlineUser>(lists, total,total);
	}

	@RequestMapping("/{sessionId}/forceLogout")
	@RequiresPermissions("admin:session")
	public @ResponseBody Msg forceLogout(@PathVariable("sessionId") String sessionId,
		RedirectAttributes redirectAttributes) {
		Session session = sessionDAO.readSession(sessionId);
		if (session != null) {
			session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY,
					Boolean.TRUE);
		}
		return new Msg();
	}

}
