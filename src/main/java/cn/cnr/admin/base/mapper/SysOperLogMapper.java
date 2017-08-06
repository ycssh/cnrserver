package cn.cnr.admin.base.mapper;


import cn.cnr.admin.base.model.SysOperLog;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;

public interface SysOperLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysOperLog record);

    int insertSelective(SysOperLog record);

    SysOperLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysOperLog record);

    int updateByPrimaryKey(SysOperLog record);

    Page<Map<String,Object>> selectByPage(Map<String, Object> params, RowBounds bounds);
}