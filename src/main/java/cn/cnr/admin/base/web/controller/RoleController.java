package cn.cnr.admin.base.web.controller;

import com.github.pagehelper.Page;
import cn.cnr.admin.Constants;
import cn.cnr.admin.base.entity.Msg;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Role;
import cn.cnr.admin.base.service.ResourceService;
import cn.cnr.admin.base.service.RoleService;
import cn.cnr.admin.base.util.PageResult;
import cn.cnr.admin.base.util.Pagination;
import cn.cnr.admin.base.model.SysOperLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value="index", method = RequestMethod.GET)
	@RequiresPermissions("role:index")
	public String index(Model model) {
		return "role/index";
	}
	
	@RequestMapping(value="list")
	@RequiresPermissions("role:index")
	public @ResponseBody PageResult<Role> list(Model model, Pagination page,Role role) {
		Page<Role> roles = roleService.find(page,role);
		int total = (int) roles.getTotal();
		return new PageResult<Role>(roles, total,total);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("role:index")
	public @ResponseBody Msg create(Role role, HttpServletResponse response ,HttpServletRequest request) throws IOException {
		Role param = new Role();
		param.setRole(role.getRole());
		List<Role> roles = roleService.findAll(param);
		if(role.getId()!=null){
			if(roles!=null){
				Role r = roles.get(0);
				if(r.getId()==role.getId()&&!r.getRole().equals(role.getRole()))
				return new Msg(false, "该角色编码已存在");
			}
			roleService.updateRole(role);
			SysOperLog _log = new SysOperLog();
			_log.setOperType("编辑角色");
			_log.setContent("编辑角色："+role.getRole());
			_log.setOperModel("角色管理");
			request.setAttribute(Constants.LOG_RECORD, _log);
		}else{
			if(roles!=null&&roles.size()>0){
				return new Msg(false, "该角色编码已存在");
			}
			roleService.createRole(role);
			SysOperLog _log = new SysOperLog();
			_log.setOperType("新增角色");
			_log.setContent("新增角色："+role.getRole());
			_log.setOperModel("角色管理");
			request.setAttribute(Constants.LOG_RECORD, _log);
		}
		return new Msg(true, "保存成功");
	}

	@RequestMapping(value = "/edit/{id}")
	@RequiresPermissions("role:index")
	public @ResponseBody Role showUpdateForm(@PathVariable("id") Long id, Model model) {
		return roleService.findOne(id);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@RequiresPermissions("role:index")
	public @ResponseBody Msg delete(@PathVariable("id") Long id,HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		Role role = roleService.findOne(id);
		roleService.deleteRole(id);
		SysOperLog _log = new SysOperLog();
		_log.setOperType("删除角色");
		_log.setContent("删除角色："+role.getRole());
		_log.setOperModel("角色管理");
		request.setAttribute(Constants.LOG_RECORD, _log);
		return new Msg();
	}

	@RequiresPermissions("role:index")
	@RequestMapping(value = "/resources/{roleId}")
	public String resources(@PathVariable("roleId")Long roleId, Model model) {	
		model.addAttribute("resources",roleService.findResByRole(roleId));
		model.addAttribute("roleId",roleId);
		return "role/resources";
	}
	
	@RequiresPermissions("role:index")
	@RequestMapping(value = "/getres/{roleId}")
	public @ResponseBody List<Resource> getresources(@PathVariable("roleId")Long roleId, Model model) {	
		return roleService.findResByRole(roleId);
	}
	
	@RequestMapping(value = "/saveresources/{roleId}")
	@RequiresPermissions("role:index")
	public @ResponseBody Msg saveResources(@PathVariable("roleId")Long roleId,String resources, HttpServletRequest request) {
		roleService.saveResources(roleId,resources);
		Role role = roleService.findOne(roleId);
		SysOperLog _log = new SysOperLog();
		_log.setOperType("编辑角色权限");
		_log.setContent("编辑角色权限："+role.getRole()+","+resources);
		_log.setOperModel("角色管理");
		request.setAttribute(Constants.LOG_RECORD, _log);
		return new Msg();
	}
	
}
