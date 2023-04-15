package com.ihrm.atte.service;

import com.ihrm.atte.dao.AttendanceConfigDao;
import com.ihrm.atte.dao.AttendanceDao;
import com.ihrm.atte.dao.UserDao;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.poi.ExcelImportUtil;
import com.ihrm.common.utils.DateUtil;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.Attendance;
import com.ihrm.domain.atte.entity.AttendanceConfig;
import com.ihrm.domain.atte.vo.AtteUploadVo;
import com.ihrm.domain.system.User;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ExcelImportService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private AttendanceConfigDao attendanceConfigDao;

	@Autowired
	private IdWorker idWorker;

	@Value("${atte.holidays}")
	private String holidays;

	@Value("${atte.wordingDays}")
	private String wordingDays;

	/**
	 * 处理考勤数据的文件上传
	 *      参数 :excel文件
	 *           企业
	 *
	 */
	public void importAttendaneExcel(MultipartFile file, String companyId) throws Exception {
		//1.将导入的excel文件解析为vo的list集合
		List<AtteUploadVo> list = new ExcelImportUtil<AtteUploadVo>(AtteUploadVo.class).readExcel(file.getInputStream(), 1, 0);
		//2.循环list集合
		for (AtteUploadVo atteUploadVo : list) {
			//2.1 根据上传的手机号码查询用户
			User user = userDao.findByMobile(atteUploadVo.getMobile());
			//2.2 构造考勤对象
			Attendance attendance = new Attendance(atteUploadVo,user);
			attendance.setDay(atteUploadVo.getAtteDate());
			//2.3 判断是否休假
			/**
			 * 1.将国家的假日记录到数据库
			 * 2.文件
			 */
			if(holidays.contains(atteUploadVo.getAtteDate())) {
				attendance.setAdtStatu(23); //休息
			}else if(DateUtil.isWeekend(atteUploadVo.getAtteDate()) && !wordingDays.contains(atteUploadVo.getAtteDate())){
				attendance.setAdtStatu(23);
			}else{
				//2.4 判断迟到,早退的状态
				//i 查询当前员工部门的上班时间,查询当前员工部门的下班时间
				AttendanceConfig ac = attendanceConfigDao.findByCompanyIdAndDepartmentId(companyId,user.getDepartmentId());
				//ii 比较,上班时间是否晚于规定上班时间 (迟到)
				if(!DateUtil.comparingDate(ac.getAfternoonStartTime(),atteUploadVo.getInTime())) { //第一个参数 : 规定时间 , 第二参数 : 打卡时间
					attendance.setAdtStatu(3);//迟到
				}else if(DateUtil.comparingDate(ac.getAfternoonEndTime(),atteUploadVo.getOutTime())) {
					attendance.setAdtStatu(4);//早退
				} else{
					//正常
					attendance.setAdtStatu(1);
				}
			}

			//2.5 查询用户是否已经有考勤记录,如果不存在,保存数据库
			Attendance byUserIdAndDay = attendanceDao.findByUserIdAndDay(user.getId(), atteUploadVo.getAtteDate());

			if(byUserIdAndDay == null) {
				attendance.setId(idWorker.nextId()+"");
				attendanceDao.save(attendance);
			}
		}
	}
}
