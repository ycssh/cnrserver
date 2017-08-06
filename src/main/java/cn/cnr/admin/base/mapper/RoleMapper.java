package cn.cnr.admin.base.mapper;

import com.github.pagehelper.Page;
import cn.cnr.admin.base.model.Role;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> selectByUser(Long userId);

	Page<Role> selectByPage(Role role, RowBounds rowBounds);

	List<Role> findAll(Role param);
}