<#assign classNameLower = ClassName ? uncap_first>
package [pPackage].service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import [pPackage].dao.${ClassName}Dao;
import [pPackage].pojo.${ClassName};

/**
 * [comment]服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ${ClassName}Service {

	@Autowired
	private ${ClassName}Dao ${ClassName}Dao;
	
	@Autowired
	private IdWorker idWorker;

/**
	 * 查询全部列表
	 * @return
	 */
	public List<${ClassName}> findAll() {
		return ${ClassName}Dao.findAll();
	}

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<${ClassName}> findPage(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return ${ClassName}Dao.findAll(pageRequest);
	}


	/**
	 * 条件查询
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<${ClassName}> findSearch(Map whereMap, int page, int size) {
		Specification<${ClassName}> specification = where(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return ${ClassName}Dao.findAll(specification, pageRequest);
	}


	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ${ClassName} findById(String id) {
		return ${ClassName}Dao.findById(id).get();
	}

	/**
	 * 增加
	 * @param ${ClassName}
	 */
	public void add(${ClassName} ${ClassName}) {
		${ClassName}.setId( idWorker.nextId()+"" );
		${ClassName}Dao.save(${ClassName});
	}

	/**
	 * 修改
	 * @param ${ClassName}
	 */
	public void update(${ClassName} ${ClassName}) {
		${ClassName}Dao.save(${ClassName});
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		${ClassName}Dao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<${ClassName}> where(Map searchMap) {

		return new Specification<${ClassName}>() {

			@Override
			public Predicate toPredicate(Root<${ClassName}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
<条件查询.String.txt>				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
