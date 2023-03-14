package com.ihrm.domain.atte.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 考勤扣款工资统计
 * @author itcast
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtteSalaryStatisticsBO  implements Serializable {


    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    private String  userId;

    /**
     * 计薪天数
     */
    private String salaryDays;


    /**
     * 实际出勤天数（非正式）
     */
    private String actualAtteUnofficialDays;


    /**
     * 应出勤工作日
     */
    private  String standardWorkDays;


    /**
     * 当月考勤扣款
     */
    private String attendanceDeductionMonthly;


}
