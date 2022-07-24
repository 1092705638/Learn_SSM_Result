package com.mybatis.test1;

import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.User;
import com.mybatis.util.sqlsesionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @ClassName test
 * @Description TODO
 * @Author 陈明伟
 * @Date 2022/7/21 10:59
 * @Version 1.0
 */
public class test1 {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() throws IOException {
        //获取字节输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取工厂会话构造器
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //通过字节流创建工厂会话
        SqlSessionFactory factory = builder.build(inputStream);
        //通过工厂会话获取sql会话,形参自动打开会话
        //SqlSession openSession(boolean autoCommit);
        SqlSession sqlSession = factory.openSession(true);
        //通过动态代理获取代理类
//        int insert1 = sqlSession.insert("com.mybatis.mapper.UserMapper.insertuser");
//        通过mybatis,获取mapper接口代理实现类
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper下的方法
        int insert = mapper.insertuser();
        /*list的遍历方法*/
        List<User> list = mapper.selectAllUser();
        list.forEach(System.out::println);
        System.out.println("结果：" + insert);
        //批量查询
        String s = new String("1,2,3,4");
        System.out.println(s);
        List<Map<String, Object>> maps = mapper.selectByIds(s);
        System.out.println("map集合：" + maps);
        //添加返回字自增主键

        //关闭会话，关闭字节流
        sqlSession.close();
        inputStream.close();
    }


    /**
     * 自动获取主键
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null, "xiaoxioa", 20, "江苏南京");
        int i = mapper.insertuserKey(user);
        System.out.println("结果：" + i);
        System.out.println("主键：" + user.getId());

        sqlSession.close();
        inputStream.close();

    }

    /**
     * 测试不用sql会话-问题1-封装了不能关闭sqlsesion会话
     */
    @Test
    public void test3() throws IOException {
        UserMapper mapper = sqlsesionUtil.mapper();
        User user = mapper.selectById(1);
        System.out.println(user);
    }

    /**
     * 测试根据id查询对象
     */
    @Test
    public void test4() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(1);
        System.out.println("根据id查询对象：" + user);
        sqlSession.close();
        inputStream.close();
    }

    /**
     * 测试封装类根据id查询对象
     */
    @Test
    public void test5() throws IOException {
        User userById = sqlsesionUtil.getUserById(1);
        System.out.println(userById);
    }


}
