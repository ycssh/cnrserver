package cn.cnr.admin.base.mapper;

import cn.cnr.admin.base.model.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Resource record);

    Long insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);
    
    List<Resource> selectAll();

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

	List<Resource> selectByParent(Long resourceId);

    /**
     * @param id
     * @return
     */
    List<Resource> selectByUser(Long id);

	List<Resource> selectByRole(Long roleId);
}