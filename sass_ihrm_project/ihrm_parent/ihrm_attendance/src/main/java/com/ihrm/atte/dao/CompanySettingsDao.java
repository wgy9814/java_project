package com.ihrm.atte.dao;


import com.ihrm.domain.atte.entity.CompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CompanySettingsDao extends JpaRepository<CompanySettings,String> ,JpaSpecificationExecutor<CompanySettings> {
}
