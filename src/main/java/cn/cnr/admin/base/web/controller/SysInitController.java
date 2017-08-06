package cn.cnr.admin.base.web.controller;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.model.SysInit;
import cn.cnr.admin.base.service.SysInitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysinit")
public class SysInitController {

	@Resource
	private SysInitService sysInitService;

	@RequestMapping("/list")
	@RequiresPermissions("sysinit")
	public String list(Model model, HttpServletRequest request) {
		model.addAttribute("inits", Constants.cache.get("sys_initlist"));
		return "sys/sysinit";
	}

	@RequestMapping("/update/{id}/{value}")
	@ResponseBody
	@RequiresPermissions("sysinit")
	public String update(@PathVariable int id, @PathVariable String value, HttpServletRequest request) {
		List<SysInit> list = (List<SysInit>) Constants.cache.get("sys_initlist");
        Map<String,String> inits = (Map<String, String>) Constants.cache.get("sys_init");
		for (SysInit init : list) {
			if (init.getId() == id) {
				init.setValue(value);
				inits.put(init.getType(), value);
			}
		}
		sysInitService.update(id, value);
		return "1";
	}
}
