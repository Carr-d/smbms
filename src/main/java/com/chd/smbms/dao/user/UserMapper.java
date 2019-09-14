package com.chd.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chd.smbms.pojo.User;

@Mapper
public interface UserMapper {
	
	/**
	 * 根据用户编码查询用户
	 * @param userCode
	 * @return
	 */
	@Select("select * from smbms_user where userCode = #{userCode}")
    User getUserByUserCode(String userCode);
	
	/**
	 * 修改密码
	 * @param id
	 * @param newpassword
	 * @return
	 */
	@Update("update smbms_user set userPassword = #{newpassword}")
	int updUserPasswordById(Integer id,String newpassword);
	
	/**
	 * 根据用户名和用户角色查询用户 模糊查询
	 * @param userName
	 * @param userRole
	 * @return
	 */
	@Select("<script> select u.*,r.roleName as userRoleName"
			+ " from smbms_user u,smbms_role r where u.userRole = r.id"
			+" <if test=\"userName!=null and userName!=''\">"
			+ "and userName like concat('%',#{userName},'%') </if>"
			+  " <if test=\"userRole!=null and userRole!=0\">"
			+ "and userRole = #{userRole} </if> </script>")
	List<User> getUserList(String userName,Integer userRole);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Select("select u.*,r.roleName as userRoleName"
			+ " from smbms_user u,smbms_role r"
			+ " where u.id = #{u.id} and u.userRole = r.id")
	User getUserById(Integer id);
	
	/**
	 * 根据用户id删除用户信息
	 * @param id
	 * @return
	 */
	@Delete("delete from smbms_user where id = #{id}")
	int delUserById(Integer id);
	
	/**
	 * 新增一条用户信息
	 * @param user
	 * @return
	 */
	@Insert("insert into smbms_user(userCode,userName,userPassword,"
			+ "gender,address,birethday,userRole,phone)"
			+ "values (#{userCode},#{userName},#{userPassword},"
			+ "#{gender},#{address},#{birthday},#{userRole},#{phone})")
	int addUser(User user);
	
	/**
	 * 根据用户id修改用户信息
	 * @param user
	 * @return
	 */
	@Update("update smbms_user set "
			+ "userName = #{userName},"
			+ "gender = #{gender},"
			+ "address = #{address},"
			+ "birthday = #{birthday},"
			+ "phone = #{phone},"
			+ "userRole = #{userRole} "
			+ "where id = #{id}")
	int updUserById(User user);
}
