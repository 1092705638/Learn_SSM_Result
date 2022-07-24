package com.mybatis.mapper;


import com.alibaba.druid.sql.visitor.functions.Insert;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.pojo.User;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName UserMapper
 * @Description TODO
 * @Author Oneby
 * @Date 2022/7/21 10:33
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper {

    int insertuser();

    int insertuserKey(User user);

    List<User> selectAllUser();

    List<Map<String, Object>> selectByIds(@Param("ids") String ids);

    User selectById(int id);








}
