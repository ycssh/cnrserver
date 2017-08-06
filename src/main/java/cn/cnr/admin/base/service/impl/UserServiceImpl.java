package cn.cnr.admin.base.service.impl;

import cn.cnr.admin.base.mapper.ResourceMapper;
import cn.cnr.admin.base.mapper.RoleMapper;
import cn.cnr.admin.base.mapper.UserMapper;
import cn.cnr.admin.base.mapper.UserRoleMapper;
import com.github.pagehelper.Page;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Role;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.model.UserRole;
import cn.cnr.admin.base.service.RoleService;
import cn.cnr.admin.base.service.UserService;
import cn.cnr.admin.base.util.Pagination;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResourceMapper resourceMapper;

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public User createUser(User user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		Long id = userMapper.insert(user);
		return userMapper.selectByPrimaryKey(id);
	}

	
	public User updateUser(User user) {
		if(user.getPassword()!=null){
			passwordHelper.encryptPassword(user);
		}
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}

	
	public void deleteUser(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(Long userId, String newPassword) {
		User user = userMapper.selectByPrimaryKey(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userMapper.updatePwd(user);
	}

	
	public User findOne(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return userMapper.findByUsername(username);
	}

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return Collections.emptySet();
		}

		List<Role> roles =  findRolesByUse(user.getId());
		Set<String> set = new HashSet<String>();
		for(Role role:roles){
			set.add(role.getRole());
		}
		return set;
	}

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return Collections.emptySet();
		}
		List<Resource> resources = resourceMapper.selectByUser(user.getId());
		Set<String> set = new HashSet<String>();
		for(Resource resource:resources){
			set.add(resource.getPermission());
		}
		return set;
		
	}

	
	public Page<User> find(User user, Pagination page) {
		RowBounds bounds = new RowBounds(page.getStart()/page.getLength()+1,page.getLength());
		return userMapper.select(user, bounds);
	}

	public List<Role> findRolesByUse(Long userId) {
		return roleMapper.selectByUser(userId);
	}

	public List<Resource> findResByUse(Long userId) {
		return resourceMapper.selectByUser(userId);
	}

	
	public void saveRoles(Long id, String roles) {
		userRoleMapper.deleteByUser(id);
		if(StringUtils.hasLength(roles)){
			List<UserRole> userRoles = new ArrayList<UserRole>();
			for(String role:roles.split(",")){
				if(StringUtils.hasLength(role)){
					UserRole userRole = new UserRole();
					userRole.setUserId(id);
					userRole.setRoleId(Long.parseLong(role));
					userRoles.add(userRole);
				}
			}
			if(userRoles.size()>0)
			userRoleMapper.insertBatch(userRoles);
		}
	}

	
	public List<Role> findRolesByUser(Long userId) {
		return roleMapper.selectByUser(userId);
	}

	
	public void updateState(int i, String username) {
		User user = findByUsername(username);
		userMapper.updateByPrimaryKeySelective(user);
	}

}