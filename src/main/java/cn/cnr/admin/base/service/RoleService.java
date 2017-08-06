package cn.cnr.admin.base.service;

import com.github.pagehelper.Page;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Role;
import cn.cnr.admin.base.util.Pagination;

import java.util.List;

public interface RoleService {


    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    
    public List<Role> findAll(Role param);
    
	public List<Resource> findResByRole(Long roleId);
	
	/**
	 * 分页查询
	 * @param page
	 * @param role
	 * @return
	 */
	public Page<Role> find(Pagination page, Role role);
	
	public void saveResources(Long roleId, String resources);
}