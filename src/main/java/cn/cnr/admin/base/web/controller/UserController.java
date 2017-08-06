package cn.cnr.admin.base.web.controller;

import com.github.pagehelper.Page;
import cn.cnr.admin.Constants;
import cn.cnr.admin.base.entity.Msg;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Role;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.realm.AES;
import cn.cnr.admin.base.realm.UserRealm;
import cn.cnr.admin.base.service.RoleService;
import cn.cnr.admin.base.service.UserService;
import cn.cnr.admin.base.util.PageResult;
import cn.cnr.admin.base.util.Pagination;
import cn.cnr.admin.base.model.SysOperLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRealm userRealm;

	@Autowired
	private CredentialsMatcher credentialsMatcher;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	@RequiresPermissions("user:index")
	public String index(Model model) {
		return "user/index";
	}

	@RequestMapping("list")
	@RequiresPermissions("user:index")
	public @ResponseBody
	PageResult<User> list(Model model, User user, Pagination page) {
		Page<User> users = userService.find(user, page);
		int total = (int) users.getTotal();
		return new PageResult<User>(users, total,total);
	}

	@RequestMapping("/resources")
	public @ResponseBody
	List<Resource> frontList() {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		User user = userService.findByUsername(username);
		List<Resource> resources = userService.findResByUse(user.getId());

		List<Resource> resources2 = new ArrayList<Resource>();
		Set<Long> set = new HashSet<Long>();
		for (Resource resource : resources) {
			while (!set.contains(resource.getId())) {
				set.add(resource.getId());
				List<Resource> children = new ArrayList<Resource>();
				for (Resource resource2 : resources) {
					if (resource2.getParentId().equals(resource.getId())
							&& "menu".equals(resource2.getType())) {
						children.add(resource2);
					}
				}
				resource.setChildren(children);
			}
			System.out.println(resource.getId()+"\t"+resource.getParentId());
			if (resource.getParentId() == 0) {
				resources2.add(resource);
			}
		}
		return resources2;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@RequiresPermissions("user:index")
	public @ResponseBody Msg create(User user, HttpServletRequest request)
			throws Exception {
		String msg = "";
		SysOperLog _log = new SysOperLog();
		if (user.getId() == null) {
			User u = userService.findByUsername(user.getUsername());
			if (u != null) {
				return new Msg(false,"用户名为" + user.getUsername() + "的用户已经存在，请输入其他用户名！");
			}
			String password = "nls123456";
			user.setPassword(password);
			userService.createUser(user);
			msg = "新增成功";
			_log.setOperType("新增后台用户");
			_log.setContent("新增后台用户："+user.getUsername());
			_log.setOperModel("后台用户管理");
			request.setAttribute(Constants.LOG_RECORD, _log);

		} else {
			userService.updateUser(user);
			msg = "编辑成功";
			_log.setOperType("编辑后台用户");
			_log.setContent("编辑后台用户："+user.getUsername());
			_log.setOperModel("后台用户管理");
			request.setAttribute(Constants.LOG_RECORD, _log);
		}
		return new Msg(true, msg);
	}

	@RequestMapping(value = "/edit/{id}")
	@RequiresPermissions("user:index")
	public @ResponseBody User showUpdateForm(@PathVariable("id") Long id, Model model) {
		User user = userService.findOne(id);
		user.setPassword(null);user.setSalt(null);
		return user;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@RequiresPermissions("user:index")
	public @ResponseBody Msg delete(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		User user = userService.findOne(id);
		userService.deleteUser(id);
		SysOperLog _log = new SysOperLog();
		_log.setOperType("删除后台用户");
		_log.setContent("删除后台用户："+user.getUsername());
		_log.setOperModel("后台用户管理");
		request.setAttribute(Constants.LOG_RECORD, _log);
		return new Msg(true, "删除成功");
	}
/*

	@RequestMapping(value = "/resetPassword/{id}", method = RequestMethod.POST)
	@RequiresPermissions("user:index")
	public @ResponseBody String resetPassword(@PathVariable("id") Long id, Model model, String passwordaa) throws InvalidSessionException, Exception {
		User user = userService.findOne(id);
		String password = AES.Decrypt(passwordaa, SecurityUtils.getSubject().getSession().getAttribute("aesKey").toString());
		if (password.length() < 8 || password.length() > 20) {
			return "密码必须在8-20位.";
		}
		user.setPassword(password);
		userService.updateUser(user);
		return "";
	}
*/

	/***
	 * 修改自己密码
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody Msg changePassword(Model model, String newpassword, String oldpassword,
			String md5, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		if (session.getAttribute("aesKey") == null) {
			String key = null;
			key = new SecureRandomNumberGenerator().nextBytes().toHex()
					.substring(0, 16);
			session.setAttribute("aesKey", key);
		}
		newpassword = AES.Decrypt(newpassword, SecurityUtils.getSubject()
				.getSession().getAttribute("aesKey").toString());
		String pwd = AES.Decrypt(oldpassword, SecurityUtils.getSubject()
				.getSession().getAttribute("aesKey").toString());
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(subject.getPrincipal().toString(), oldpassword);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user.getUsername(), user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				userRealm.getName());
		boolean matchs = credentialsMatcher.doCredentialsMatch(token, info);
		if (matchs) {
			userService.changePassword(user.getId(), newpassword);
			return new Msg(true,"修改密码成功");
		} else {
			return new Msg(false,"原始密码不正确，请重新输入");
		}
	}


	/**
	 * 保存分配角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("saverole/{id}")
	@RequiresPermissions("user:index")
	public @ResponseBody
	Msg saveRole(@PathVariable("id") Long id, String roles,
			HttpServletRequest request) {
		User user = userService.findOne(id);
		userService.saveRoles(id, roles);
		SysOperLog _log = new SysOperLog();
		_log.setOperType("编辑用户角色");
		_log.setContent("编辑用户角色："+user.getUsername());
		_log.setOperModel("后台用户管理");
		request.setAttribute(Constants.LOG_RECORD, _log);
		return new Msg(true, "");
	}

	/**
	 * 跳转到分配角色
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user:index")
	@RequestMapping("role/{id}")
	public String role(@PathVariable("id") Long id, Model model) {
		List<Role> roles = userService.findRolesByUser(id);
		List<Role> all = roleService.findAll(new Role());
		all.removeAll(roles);
		all.removeAll(roles);
		model.addAttribute("roles", roles);
		model.addAttribute("notIn", all);
		model.addAttribute("userId", id);
		return "user/role";
	}

	/**
	 * 跳转到分配角色
	 * 
	 * @return
	 */
	@RequiresPermissions("user:index")
	@RequestMapping("state/{state}/{username}")
	public @ResponseBody void updateState(@PathVariable int state,
			@PathVariable String username) {
		userService.updateState(state, username);
	}
}
