package cn.cnr.admin.base.mapper;

import cn.cnr.admin.base.model.RoleResource;

import java.util.List;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(RoleResource key);

    int insert(RoleResource record);

    RoleResource selectByPrimaryKey(RoleResource key);

	void deleteByRole(Long roleId);

	void insertBatch(List<RoleResource> userRoles);
}