package com.chd.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chd.smbms.dao.role.RoleMapper;
import com.chd.smbms.dao.user.UserMapper;
import com.chd.smbms.pojo.Role;
import com.chd.smbms.pojo.User;
import com.chd.smbms.service.role.RoleService;
import com.chd.smbms.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	

	/**
	 * 登录
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.html")
	public String login(String userCode,String userPassword,HttpServletRequest request) {
		String error = "";
		User loginUser = userService.getUserByUserCode(userCode);
		if (loginUser != null) {
			if (loginUser.getUserPassword().equals(userPassword)) {
				request.getSession().setAttribute("userSession", loginUser);
				return "frame";
			}
			error = "用户名或密码错误";
		}else {
			error = "用户名不存在";
		}
		request.setAttribute("error", error);
		return "forward:login.jsp";
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute("userSession");
		return "redirect:login.jsp";
	}
	
	/**
	 * 跳转密码修改
	 * @return
	 */
	@RequestMapping("/pwdmodify.html")
	public String pwdmodify() {
		return "pwdmodify";
	}
	
	/**
	 * 验证旧密码
	 * @param oldpassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkpwd.json")
	@ResponseBody
	public Object checkpwd(String oldpassword,HttpSession session) {
		User loginUser = (User) session.getAttribute("userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		if (loginUser == null) {
			map.put("result", "seesionerror");
		}else if (oldpassword == null || "".equals(oldpassword)) {
			map.put("result", "error");
		}else if (!loginUser.getUserPassword().equals(oldpassword)) {
			map.put("result", "false");
		}else {
			map.put("result", "true");
		}
		return map;
	}
	
	/**
	 * 密码修改
	 * @param newpassword
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/savepwd.html")
	public String savepwd(String newpassword,HttpSession session,HttpServletRequest request) {
		User loginUser = (User) session.getAttribute("userSession");
		if (userService.updUserPasswordById(loginUser.getId(), newpassword) > 0) {
			request.setAttribute("message", "修改成功");
			loginUser.setUserPassword(newpassword);
		}else {
			request.setAttribute("message", "修改失败");
		}
		return "redirect:login.jsp";
	}
	
	/**
	 * 用户列表
	 * @param request
	 * @param pageIndex
	 * @param queryname
	 * @param queryUserRole
	 * @return
	 */
	@RequestMapping("/userlist.html")
	public String userlist(HttpServletRequest request,
			@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
			@RequestParam(required = false)String queryname,
			@RequestParam(required = false)Integer queryUserRole) {
		List<Role> roleList = roleService.getRoleList();
		PageHelper.startPage(pageIndex, 10);
		List<User> userList = userService.getUserList(queryname, queryUserRole);
		PageInfo<User> pi = new PageInfo<User>(userList);
		request.setAttribute("currentPageNo", pi.getPageNum());
		request.setAttribute("totalCount", pi.getTotal());
		request.setAttribute("totalPageCount", pi.getPages());
		request.setAttribute("roleList",roleList);
		request.setAttribute("userList",userList);
		return "userlist";
	}
	
	/**
	 * 查看用户
	 * @param id
	 * @param request
	 * @return
	 */
	@GetMapping("/user/{id}")
	public String userview(@PathVariable Integer id,
			HttpServletRequest request) {
		
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		
		return "userview";
		
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping("/user/{id}")
	@ResponseBody
	public Map<String, Object> deluser(@PathVariable Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int delResult = userService.delUserById(id);
		if (delResult == 0) {
			map.put("delResult", "notexist");
		}else if (delResult > 0) {
			map.put("delResult", "true");
		}else {
			map.put("delResult", "false");
		}
		return map;
	}
	
	/**
	 * 跳转用户添加
	 * @return
	 */
	@RequestMapping("/useradd.html")
	public String useradd() {
		return "useradd";
	}
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@GetMapping("/getrolelist.json")
	@ResponseBody
	public Object getrolelist() {
		return roleService.getRoleList();
	}
	
	/**
	 * 验证用户编码
	 * @param userCode
	 * @return
	 */
	@GetMapping("/ucexist.json")
	@ResponseBody
	public Object ucexist(String userCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userService.getUserByUserCode(userCode) == null) {
			map.put("userCode", "noexist");
		}else {
			map.put("userCode", "exist");
		}
		return map;
	}
	
	/**
	 * 用户添加
	 * @param user
	 * @param session
	 * @return
	 */
	@PostMapping("/usersave.html")
	public String usersave(User user,HttpSession session) {
		User loginUser = (User) session.getAttribute("userSession");
		user.setCreatedBy(loginUser.getId());
		user.setCreationDate(new Date());
		if (userService.addUser(user) > 0) {
			return "redirect:userlist.html";
		}else {
			return "useradd";
		}
	}
	
	/**
	 * 跳转用户修改
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/usermodify/{id}")
	public String usermodify(@PathVariable Integer id,
			HttpServletRequest request) {
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		return "usermodify";
	}
	
	/**
	 * 用户修改
	 * @param user
	 * @param session
	 * @return
	 */
	@PostMapping("/userupdate.html")
	public String userupdate(User user,HttpSession session) {
		User loginUser = (User) session.getAttribute("userSession");
		user.setModifyBy(loginUser.getId());
		user.setModifyDate(new Date());
		if(userService.updUserById(user)>0) {
			return "redirect:userlist.html";
		}else {
			return "redirect:usermodify/"+user.getId();
		}
	}
}
