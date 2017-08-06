package cn.cnr.admin.base.service;

import com.github.pagehelper.Page;
import cn.cnr.admin.base.model.Resource;
import cn.cnr.admin.base.model.Role;
import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.util.Pagination;

import java.util.List;
import java.util.Set;

public interface UserService {

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	User createUser(User user);

	User updateUser(User user);

	void deleteUser(Long userId);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	void changePassword(Long userId, String newPassword);

	User findOne(Long userId);

	/**
	 * 根据用户名查找用户
	 *
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);

	/**
	 * 条件查询
	 *
	 * @param user
	 *            是否级联查询子部门
	 * @param page
	 * @return
	 */
	Page<User> find(User user, Pagination page);

	List<Role> findRolesByUse(Long userId);

	List<Resource> findResByUse(Long userId);

	/**
	 * 保存分配的角色
	 * 
	 * @param id
	 * @param roles
	 */
	public void saveRoles(Long id, String roles);

	/**
	 * 查找某个人的所有角色
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findRolesByUser(Long id);

	public void updateState(int i, String username);

}