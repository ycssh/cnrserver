package cn.cnr.admin.base.mapper;

import cn.cnr.admin.base.model.SysInit;

import java.util.List;

public interface SysInitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysInit record);

    int insertSelective(SysInit record);

    SysInit selectByPrimaryKey(Integer id);
    
    List<SysInit> selectAll();

    int updateByPrimaryKeySelective(SysInit record);

    int updateByPrimaryKey(SysInit record);
}