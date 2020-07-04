package com.xrb.dao;


import java.util.List;


import com.xrb.entity.User;

public interface UserDao 
{
	
 	public User queryUserByName(String userName);
    
    public Integer addUser(User user);
    
    public Integer deleteUser(String userId);
    
    public Integer updateUser(User user);
	   
 	public List<User> queryAllUsers();
	
}
