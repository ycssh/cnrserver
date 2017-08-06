package cn.cnr.admin.base.service.impl;

import com.github.pagehelper.Page;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Role;
import cn.cnr.admin.base.model.RoleResource;
import cn.cnr.admin.base.service.RoleService;
import cn.cnr.admin.base.util.Pagination;
import cn.cnr.admin.base.mapper.ResourceMapper;
import cn.cnr.admin.base.mapper.RoleMapper;
import cn.cnr.admin.base.mapper.RoleResourceMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	
	public Role createRole(Role role) {
		roleMapper.insert(role);
		return role;
	}

	
	public Role updateRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
		return role;
	}

	
	public void deleteRole(Long roleId) {
		roleMapper.deleteByPrimaryKey(roleId);
	}

	
	public Role findOne(Long roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	
	public List<Role> findAll(Role param) {
		 return roleMapper.findAll(param);
	}

	public List<Resource> findResByRole(Long roleId) {
		return resourceMapper.selectByRole(roleId);
	}

	
	public Page<Role> find(Pagination page, Role role) {
		RowBounds bounds = new RowBounds(page.getStart()/page.getLength()+1,page.getLength());
		Page<Role> pages = roleMapper.selectByPage(role, bounds);
		return pages;
	}

	
	public void saveResources(Long roleId, String resources) {
		roleResourceMapper.deleteByRole(roleId);
		if(StringUtils.hasLength(resources)){
			List<RoleResource> roleResources = new ArrayList<RoleResource>();
			for(String resource:resources.split(",")){
				if(StringUtils.hasLength(resource)){
					RoleResource roleResource = new RoleResource();
					roleResource.setRoleId(roleId);
					roleResource.setResourceId(Long.parseLong(resource));
					roleResources.add(roleResource);
				}
			}
			if(roleResources.size()>0)
			roleResourceMapper.insertBatch(roleResources);
		}
	}
}