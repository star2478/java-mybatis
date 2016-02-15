package com.pingan.java_mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OpOracleByXml {
    public static void main(String[] args) throws IOException {
    	
    	//mybatis的配置文件
        String resource = "conf.xml";

        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = OpOracleByXml.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        
        //创建能执行映射文件中sql的sqlSession
        //true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务
        //false或不加参数 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.commit()提交事务
        SqlSession session = sessionFactory.openSession(true);
        
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.pingan.java_mybatis.userMapper.getUser";//映射sql的标识字符串
        User user = session.selectOne(statement, 1);
        System.out.println("getUser: " + user.toString());
        
        //addUser
        String addUserStatement = "com.pingan.java_mybatis.userMapper.addUser";
        User addUserObj = new User();
        addUserObj.setName("testUser");
        addUserObj.setAge(99);
        int retResult = session.insert(addUserStatement, addUserObj);
        //session.commit();
        System.out.println("addUser: " + retResult);
        
        //getALlUsers
        String getAllUsersStatement = "com.pingan.java_mybatis.userMapper.getAllUsers";
        List<User> usersList = session.selectList(getAllUsersStatement);
        for (User oneUser : usersList) {
        	System.out.println("getALlUsers: " + oneUser.toString());
        }
        
        session.close();
    }
}
