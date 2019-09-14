package com.chd.smbms.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.chd.smbms.pojo.Role;

@Mapper
public interface RoleMapper {
	
	@Select("select * from smbms_role")
	List<Role> getRoleList();

}
