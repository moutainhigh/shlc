package com.hedong.hedongwx.web.controller.webpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hedong.hedongwx.entity.Admin;
import com.hedong.hedongwx.service.AdminService;
import com.hedong.hedongwx.utils.CommUtil;
import com.hedong.hedongwx.utils.JedisUtils;

/**
 * 管理员处理
 * @author RoarWolf
 *
 */
@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/addAdmin")
	public Object addAdmin(Admin admin) {
		return adminService.insertAdmin(admin);
	}
	
	@RequestMapping("/editAdmin")
	public Object editAdmin(Admin admin) {
		return adminService.updateAdmin(admin);
	}
	
	@RequestMapping("/selectAdmin")
	public Object selectAdmin(Integer adminid) {
		return adminService.selectAdminById(adminid);
	}
	
	@RequestMapping("/selectAdminlist")
	public Object selectAdminlist(Admin admin) {
		return adminService.selectAdminList(admin);
	}
	
	@RequestMapping("/selectAdminMenu")
	public Object selectAdminMenu(Integer adminid) {
		return adminService.selectAdminMenu(adminid);
	}
	
	@RequestMapping("/selectSetAdminMenu")
	public Object selectSetAdminMenu(Integer adminid) {
		return adminService.selectSetAdminMenu(adminid);
	}
	
	@RequestMapping("/updateAdminMenu")
	public Object updateAdminMenu(Integer adminid, @RequestParam(value = "menulist") String menulist) {
		List<Integer> menuidlist = (List<Integer>) JSON.parse(menulist);
		try {
			adminService.updateAdminMenu(adminid, menuidlist);
			return CommUtil.responseBuildInfo(200, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return CommUtil.responseBuildInfo(201, "修改失败", null);
		}
	}
}
