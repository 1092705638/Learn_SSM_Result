package com.mybatis.util;

import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName sqlsesionUtil
 * @Description TODO
 * @Author 陈明伟
 * @Date 2022/7/22 14:18
 * @Version 1.0
 */
@Component
public class sqlsesionUtil {

    /**
     * 获取sqlsesion
     *
     * @return
     * @throws IOException
     */
    public static UserMapper mapper() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("sql对象：" + mapper);
        return mapper;
    }


    /**
     * 封装selectById方法
     */
    public static User getUserById(int id) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(id);
        sqlSession.close();
        inputStream.close();
        return user;
    }


}
