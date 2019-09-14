package com.chd.smbms.service.role;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chd.smbms.pojo.Role;
import com.chd.smbms.dao.role.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

}
