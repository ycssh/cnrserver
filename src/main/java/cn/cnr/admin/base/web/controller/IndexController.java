package cn.cnr.admin.base.web.controller;

import cn.cnr.admin.Constants;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Resource.ResourceType;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.service.UserService;
import cn.cnr.admin.base.web.bind.annotation.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/console")
    public String index(@CurrentUser User loginUser, Model model) {
        return "admin/index";
    }
    
    @RequestMapping(value={"/index","/"})
    public String index2(Model model) {
        List<Resource> resources2 = new ArrayList<Resource>();
        if(SecurityUtils.getSubject().getSession().getAttribute("resources")==null){
            User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
            List<Resource> resources = userService.findResByUse(user.getId());
            Set<Long> set = new HashSet<Long>();
            for (Resource resource : resources) {
                while (!set.contains(resource.getId())) {
                    set.add(resource.getId());
                    List<Resource> children = new ArrayList<Resource>();
                    for (Resource eduChaptInfo2 : resources) {
                        if (resource.getId().equals(eduChaptInfo2.getParentId())) {
                            children.add(eduChaptInfo2);
                        }
                    }
                    resource.setChildren(children);
                }
                if (resource.getParentId()==null) {
                    resources2.add(resource);
                }
            }
            SecurityUtils.getSubject().getSession().setAttribute("resources",resources2);
        }else{
            resources2 = (List<Resource>)SecurityUtils.getSubject().getSession().getAttribute("resources");
        }
        model.addAttribute("resources",resources2);
        return "admin/index2";
    }
    
    @RequestMapping("layout/menus")
    public @ResponseBody List<Resource> resources(@CurrentUser User loginUser) {
    	List<Resource> resources = userService.findResByUse(loginUser.getId());
    	for(int i=resources.size()-1;i>=0;i--){
    		if(ResourceType.button.equals(resources.get(i).getType())){
    			resources.remove(i);
    		}
    	}
    	return resources;
    }
    

    @RequestMapping("/welcome")
    public String welcome() {
       // return "project";
    	return "project/welcome";
    }
}
