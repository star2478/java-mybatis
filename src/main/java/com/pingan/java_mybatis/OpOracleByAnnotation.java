package com.pingan.java_mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OpOracleByAnnotation {
    public static void main(String[] args) throws IOException {
    	
    	//mybatis的配置文件
        String resource = "conf.xml";

        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = OpOracleByAnnotation.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        //创建能执行映射文件中sql的sqlSession
        //true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务
        //false或不加参数 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.commit()提交事务
        SqlSession session = sessionFactory.openSession(true);
      
        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperInterface.class)动态构建出来
        UserMapperInterface mapper = session.getMapper(UserMapperInterface.class);
        
        //getById
        User user = mapper.getById(14);
        System.out.println("getById(By Anno): " + user.toString());
        
        //addUser
        User addUserObj = new User();
        addUserObj.setName("testUser4Anno");
        addUserObj.setAge(98);
        int retResult = mapper.add(addUserObj);
        System.out.println(addUserObj.getName() + "========");
        System.out.println("add(By Anno): " + retResult);
        
        //getALl
        List<User> usersList = mapper.getAll();
        for (User oneUser : usersList) {
        	System.out.println("getAll(By Anno): " + oneUser.toString());
        }
        
        session.close();
    }
}
