package com.hedong.hedongwx.web.controller.applet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedong.hedongwx.entity.District;
import com.hedong.hedongwx.service.DistrictService;
import com.hedong.hedongwx.service.MeruserService;
import com.hedong.hedongwx.service.UserBankcardService;
import com.hedong.hedongwx.utils.BankcardUtil;
import com.hedong.hedongwx.utils.CommUtil;

/**
*author：RoarWolf
*createtime：2020年11月12日
*/

@RestController
@RequestMapping("/applet/meruser")
@EnableScheduling
public class AppletMeruserController {
	
	@Autowired
	private MeruserService meruserService;
	@Autowired
	private UserBankcardService userBankcardService;
	@Autowired
	private DistrictService districtService;
	
	@Scheduled(cron = "0 0 3 * * *")
	public void merEverydayCollect() {
		meruserService.merCollect();
	}

	@RequestMapping("/addMeruser")
	public Object addMeruser(Integer userid, Integer province, Integer city,
			Integer country, String realname, String idcardnum, String cardimgFront, 
			String cardimgBack, String areaname, String address, String bankcardnum,
			String bankname) {
		try {
			return meruserService.insertMeruserAndIdcard(userid, province, city, country, realname, idcardnum, cardimgFront, cardimgBack, areaname,address,bankcardnum,bankname);
		} catch (Exception e) {
			e.printStackTrace();
			return CommUtil.responseBuildInfo(1001, "添加失败，系统异常", null);
		}
	}
	
	@RequestMapping("/queryMeruser")
	public Object queryMeruser(Integer userid) {
		try {
			return meruserService.selectMeruserByUid(userid);
		} catch (Exception e) {
			return CommUtil.responseBuildInfo(1001, "查询失败，系统异常", null);
		}
	}
	
	@RequestMapping("/addBankcard")
	public Object addBankcard(Integer userid, String bankcardnum, String bankname) {
		try {
			return meruserService.insertBankcard(userid, bankcardnum, bankname);
		} catch (Exception e) {
			return CommUtil.responseBuildInfo(1001, "添加失败，系统异常", null);
		}
	}
	
	@RequestMapping("/queryBankcardlist")
	public Object addBankcard(Integer userid) {
		try {
			return meruserService.selectUserBankcardlist(userid, null);
		} catch (Exception e) {
			return CommUtil.responseBuildInfo(1001, "添加失败，系统异常", null);
		}
	}
	
	@RequestMapping("/queryDistrictlist")
	public Object queryDistrictlist(String districtId, String levelType) {
		try {
			Map<String,Object> map = new HashMap<>();
			District district = new District();
			district.setParentId(districtId);
			district.setLevelType(levelType);
			List<District> districtlist = districtService.selectDistrictByParam(district);
			map.put("districtlist", districtlist);
			return CommUtil.responseBuildInfo(1000, "获取成功", map);
		} catch (Exception e) {
			return CommUtil.responseBuildInfo(1001, "添加失败，系统异常", null);
		}
	}
	
	@RequestMapping("/withdraw")
	public Object withdraw(Integer bankcardid, Double money, Integer userid) {
		try {
			return meruserService.withdrawaccess(bankcardid, money, userid);
		} catch (Exception e) {
			e.printStackTrace();
			return CommUtil.responseBuildInfo(1001, "提现失败，系统异常", null);
		}
	}
	
	@RequestMapping("/queryMerCollect")
	public Object queryMerCollect(Integer userid) {
		try {
			return meruserService.selectEarnWallet(userid);
		} catch (Exception e) {
			e.printStackTrace();
			return CommUtil.responseBuildInfo(1001, "查询失败，系统异常", null);
		}
	}
	
	@RequestMapping("/queryMerBalanceDetail")
	public Object queryMerBalanceDetail(Integer userid, Integer startnum, Integer datetype) {
		try {
			return meruserService.selectMerEarnDetaillist(userid, startnum, datetype);
		} catch (Exception e) {
			e.printStackTrace();
			return CommUtil.responseBuildInfo(1001, "查询失败，系统异常", null);
		}
	}
	
	@RequestMapping("/queryMerEarnDetail")
	public Object queryMerEarnDetail(Integer userid, Integer startnum, String datetime) {
		try {
			return meruserService.selectMerEarnList(userid, startnum, datetime);
		} catch (Exception e) {
			e.printStackTrace();
			return CommUtil.responseBuildInfo(1001, "查询失败，系统异常", null);
		}
	}
	
	@RequestMapping("/checkBankcard")
	public Object checkBankcard(String bankcardnum) {
		return BankcardUtil.checkBankcardInfo(bankcardnum);
	}
}
