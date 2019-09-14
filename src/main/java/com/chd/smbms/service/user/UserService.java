package com.chd.smbms.service.user;

import java.util.List;

import com.chd.smbms.pojo.User;

public interface UserService {
	
	/**
	 * 根据用户编码查询用户
	 * @param userCode
	 * @return
	 */
	User getUserByUserCode(String userCode);
	
	/**
	 * 根据用户id修改密码
	 * @param id
	 * @param newpassword
	 * @return
	 */
	int updUserPasswordById(Integer id,String newpassword);
	
	/**
	 * 根据用户名和用户角色查询用户
	 * @param userName
	 * @param userRole
	 * @return
	 */
	List<User> getUserList(String userName,Integer userRole);
	
	/**
	 * 根据用户id查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	
	/**
	 * 根据用户id删除用户信息
	 * @param id
	 * @return
	 */
	int delUserById(Integer id);
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	int addUser(User user);
	
	/**
	 *根据用户id修改用户信息 
	 * @param user
	 * @return
	 */
	int updUserById(User user);

}
