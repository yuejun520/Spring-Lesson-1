package com.hbnu;

import com.hbnu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void findAll() throws IOException {
//        1、获取配置文件输入流对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

//        2、通过配置文件输入流对象构建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//        3、通过会话工厂创建会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        4、通过会话对象执行数据库的CRUD操作
        String sql = "UserMapper.findAll";
        List<User> userList = sqlSession.selectList(sql);
        for (User user : userList) {
            System.out.println(user);
        }

//        5、关闭资源
        sqlSession.close();
        inputStream.close();
    }
}
