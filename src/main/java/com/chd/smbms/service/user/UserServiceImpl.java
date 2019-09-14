package com.chd.smbms.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chd.smbms.dao.user.UserMapper;
import com.chd.smbms.pojo.User;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByUserCode(String userCode) {
		return userMapper.getUserByUserCode(userCode);
	}

	@Override
	public int updUserPasswordById(Integer id, String newpassword) {
		return userMapper.updUserPasswordById(id, newpassword);
	}

	@Override
	public List<User> getUserList(String userName, Integer userRole) {
		return userMapper.getUserList(userName, userRole);
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public int delUserById(Integer id) {
		return userMapper.delUserById(id);
	}

	@Override
	public int addUser(User user) {
		return userMapper.addUser(user);
	}

	@Override
	public int updUserById(User user) {
		return userMapper.updUserById(user);
	}
	
	

}
