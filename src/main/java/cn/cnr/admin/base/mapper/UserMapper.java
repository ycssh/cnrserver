package cn.cnr.admin.base.mapper;

import cn.cnr.admin.base.model.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    Page<User> select(User user, RowBounds bounds);
    
    int selectCount(User user);

	User findByUsername(String username);

    void updatePwd(User user);
}