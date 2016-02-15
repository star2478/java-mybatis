package com.pingan.java_mybatis;

import java.util.List;
import com.pingan.java_mybatis.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author gacl
 * 定义sql映射的接口，使用注解指明方法要执行的SQL
 */
public interface UserMapperInterface {

    //使用@Insert注解指明add方法要执行的SQL
    @Insert("insert into test_user(id, name, age) values(test_user_sequence.nextval, #{name}, #{age})")
    public int add(User user);
    
    //使用@Delete注解指明deleteById方法要执行的SQL
    @Delete("delete from test_user where id=#{id}")
    public int deleteById(int id);
    
    //使用@Update注解指明update方法要执行的SQL
    @Update("update test_user set name=#{name},age=#{age} where id=#{id}")
    public int update(User user);
    
    //使用@Select注解指明getById方法要执行的SQL
    @Select("select * from test_user where id=#{id}")
    public User getById(int id);
    
    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from test_user order by id")
    public List<User> getAll();
}