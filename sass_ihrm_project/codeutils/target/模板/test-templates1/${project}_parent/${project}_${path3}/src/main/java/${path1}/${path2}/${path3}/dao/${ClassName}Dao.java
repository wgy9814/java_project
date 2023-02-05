package ${pPackage}.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ${pPackage}.pojo.${ClassName};
/**
 * [comment]数据访问接口
 * @author Administrator
 *
 */
public interface ${ClassName}Dao extends JpaRepository<${ClassName},String>,JpaSpecificationExecutor<${ClassName}>{
	
}
