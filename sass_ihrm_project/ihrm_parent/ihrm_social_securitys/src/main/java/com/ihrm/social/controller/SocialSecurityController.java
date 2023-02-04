package com.ihrm.social.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.social_security.*;
import com.ihrm.social.client.SystemFeignClient;
import com.ihrm.social.service.ArchiveService;
import com.ihrm.social.service.CompanySettingsService;
import com.ihrm.social.service.PaymentItemService;
import com.ihrm.social.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/social_securitys")
public class SocialSecurityController extends BaseController {

	@Autowired
	private CompanySettingsService companySettingsService;

	@Autowired
	private UserSocialService userSocialService;

	@Autowired
	private SystemFeignClient systemFeignClient;

	@Autowired
	private PaymentItemService paymentItemService;

	@Autowired
	private ArchiveService archiveService;

	/**
	 * 查询企业是否设置过社保
	 */
	@RequestMapping(value = "/settings",method = RequestMethod.GET)
	public Result settings() {
		CompanySettings cs = companySettingsService.findById(companyId);
		return new Result(ResultCode.SUCCESS,cs);
	}

	///historys/201908/newReport
	/**
	 * 制作新报表
	 *     切换统计周期
	 */
	@RequestMapping(value = "/historys/{yearMonth}/newReport",method = RequestMethod.PUT)
	public Result saveSettings(@PathVariable String yearMonth) {
		//修改企业设置
		CompanySettings cs = companySettingsService.findById(companyId);
		if(cs == null) {
			cs = new CompanySettings();
		}
		cs.setCompanyId(companyId);
		cs.setDataMonth(yearMonth);
		companySettingsService.save(cs);
		return new Result(ResultCode.SUCCESS);
	}

	/**
	 * 保存企业设置
	 */
	@RequestMapping(value = "/settings",method = RequestMethod.POST)
	public Result saveSettings(@RequestBody CompanySettings companySettings) {
		companySettings.setCompanyId(companyId);
		companySettingsService.save(companySettings);
		return new Result(ResultCode.SUCCESS);
	}

	/**
	 * 查询企业员工的社保信息列表
	 */
	@RequestMapping(value = "list",method = RequestMethod.POST)
	public Result list(@RequestBody Map map) {
		//1.获取请求参数,page,size
		Integer page = (Integer)map.get("page");
		Integer pageSize = (Integer)map.get("pageSize");
		//2.调用service查询
		PageResult pr = userSocialService.findAll(page,pageSize,companyId);
		return new Result(ResultCode.SUCCESS,pr);
	}

	/**
	 * 根据用户id查询用户的社保数据
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Result findById(@PathVariable String id) {
		Map map = new HashMap();
		//1.根据用户id查询用户数据
		Object obj = systemFeignClient.findById(id).getData();
		map.put("user",obj);
		//2.根据用户id查询社保数据
		UserSocialSecurity uss =  userSocialService.findById(id);
		map.put("userSocialSecurity",uss);
		return new Result(ResultCode.SUCCESS,map);
	}

	/**
	 * 保存或更新用户社保
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public Result saveUserSocialSecurity(@RequestBody UserSocialSecurity uss) {
		userSocialService.save(uss);
		return new Result(ResultCode.SUCCESS);
	}

	/**
	 * 根据城市id查询参保城市的参保项目
	 */
	@RequestMapping(value = "/payment_item/{id}",method = RequestMethod.GET)
	public Result findPaymentItem(@PathVariable String id) {
		List<CityPaymentItem> list = paymentItemService.findAllByCityId(id);
		return new Result(ResultCode.SUCCESS,list);
	}

	/**
	 * 查询月份数据报表
	 *  /historys/201907?yearMonth=201907&opType=1
	 *      opType : 1 (当月数据)
	 *             : 其他(历史归档的详细记录)
	 */
	@RequestMapping(value = "/historys/{yearMonth}",method = RequestMethod.GET)
	public Result historysDetail(@PathVariable String yearMonth,int opType) throws Exception {
		List<ArchiveDetail> list = new ArrayList<>();
		if(opType == 1) {
			//未归档,查询当月的详细数据
			list = archiveService.getReports(yearMonth,companyId);
		}else{
			//已归档的数据
			//1.根据月和企业id查询归档历史
			Archive archive = archiveService.findArchive(companyId,yearMonth);
			//2.如果归档历史存在,查询归档明细
			if(archive != null) {
				list = archiveService.findAllDetailByArchiveId(archive.getId());
			}
		}
		return new Result(ResultCode.SUCCESS,list);
	}

	/**
	 * 数据归档
	 *  /historys/201907/archive
	 */
	@RequestMapping(value = "/historys/{yearMonth}/archive",method = RequestMethod.POST)
	public Result historysDetail(@PathVariable String yearMonth) throws Exception {
		archiveService.archive(yearMonth,companyId);
		return new Result(ResultCode.SUCCESS);
	}

	/**
	 * 查询历史归档列表
	 *  /historys/2019/list
	 */
	@RequestMapping(value = "/historys/{year}/list",method = RequestMethod.GET)
	public Result historysList(@PathVariable String year) throws Exception {
		List<Archive> list = archiveService.findArchiveByYear(companyId,year);
		return new Result(ResultCode.SUCCESS,list);
	}

	/**
	 * 根据用户id和考勤年月查询用户考勤归档明细
	 *
	 * /social_securitys/historys/data?userId=1063705482939731968&yearMonth=201906
	 */
	@RequestMapping(value = "/historys/data",method = RequestMethod.GET)
	public ArchiveDetail historysData(String userId,String yearMonth) throws Exception {
		return archiveService.findUserArchiveDetail(userId,yearMonth);
	}
}
