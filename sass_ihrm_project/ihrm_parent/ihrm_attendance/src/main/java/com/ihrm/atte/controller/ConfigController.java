package com.ihrm.atte.controller;

import com.ihrm.atte.service.ConfigurationService;
import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.atte.entity.AttendanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置考勤设置的controller
 */
@RestController
@RequestMapping("/cfg")
public class ConfigController extends BaseController{

	@Autowired
	private ConfigurationService configurationService;

	/**
	 * 获取考勤设置
	 *  cfg/atte/item
	 *  post
	 *    参数 departmentId
	 */
	@RequestMapping(value = "/atte/item" ,method = RequestMethod.POST)
	public Result atteConfig(String departmentId) {
		AttendanceConfig ac = configurationService.getAtteConfig(companyId,departmentId);
		return new Result(ResultCode.SUCCESS,ac);
	}

	/**
	 * 保存考勤设置
	 *  cfg/atte
	 *  put
	 *    参数 AttendanceConfig
	 */
	@RequestMapping(value = "/atte" ,method = RequestMethod.PUT)
	public Result saveAtteConfig(@RequestBody AttendanceConfig attendanceConfig) {
		attendanceConfig.setCompanyId(companyId);
		configurationService.saveAtteConfig(attendanceConfig);
		return new Result(ResultCode.SUCCESS);
	}
}
