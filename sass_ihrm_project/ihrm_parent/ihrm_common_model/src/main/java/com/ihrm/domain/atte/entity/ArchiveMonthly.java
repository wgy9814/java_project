package com.ihrm.domain.atte.entity;


import com.ihrm.domain.atte.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name = "atte_archive_monthly")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveMonthly extends BaseEntity implements Serializable {

  @Id
  private String id;
  private String companyId;
  private String departmentId;

  private String archiveYear;
  private String archiveMonth;
  private Integer totalPeopleNum;

  private Integer fullAttePeopleNum;
  private Integer isArchived;
}
