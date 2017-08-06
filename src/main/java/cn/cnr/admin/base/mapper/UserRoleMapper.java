package cn.cnr.admin.base.mapper;

import cn.cnr.admin.base.model.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRole key);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(UserRole key);

    void deleteByUser(Long id);

	void insertBatch(List<UserRole> userRoles);

}