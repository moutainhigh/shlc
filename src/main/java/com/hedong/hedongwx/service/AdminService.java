package com.hedong.hedongwx.service;

import java.util.List;
import java.util.Map;

import com.hedong.hedongwx.entity.Admin;

public interface AdminService {

	/**
	 * 添加管理员
	 * @param admin
	 * @return
	 */
	Map<String,Object> insertAdmin(Admin admin);
    
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return
	 */
	Map<String,Object> updateAdmin(Admin admin);
    
    /**
	 * 查询管理员信息
	 * @param id
	 * @return admin
	 */
	Map<String,Object> selectAdminById(Integer id);
	
	/**
	 * 查询管理员信息菜单信息
	 * @param id
	 * @return admin
	 */
	Map<String,Object> selectAdminMenu(Integer id);
	
	/**
	 * 查询管理员信息配置菜单信息
	 * @param id
	 * @return admin
	 */
	Map<String,Object> selectSetAdminMenu(Integer id);
    
    /**
     * 根据条件查询管理员
     * @param admin
     * @return
     */
	Map<String,Object> selectAdminList(Admin admin);
	
	/**
	 * 修改管理员权限
	 * @param id
	 * @param menuidlist
	 * @return
	 */
	int updateAdminMenu(Integer id, List<Integer> menuidlist);
    
}
