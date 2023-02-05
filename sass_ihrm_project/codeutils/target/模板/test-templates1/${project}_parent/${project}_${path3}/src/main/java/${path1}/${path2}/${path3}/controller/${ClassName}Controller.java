<#assign tableNameLower = ClassName ? uncap_first>
package ${pPackage}.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${pPackage}.pojo.${ClassName};
import ${pPackage}.service.${ClassName}Service;

import entity.PageResult;
import entity.Result;
/**
 * [comment]控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/${tableNameLower}")
public class ${ClassName}Controller {

	@Autowired
	private ${ClassName}Service ${tableNameLower}Service;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,1000,"查询成功",${tableNameLower}Service.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,1000,"查询成功",${tableNameLower}Service.findById(id));
	}


	/**
	 * 分页查询全部数据
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/{page}/{size}",method=RequestMethod.GET)
	public Result findPage(@PathVariable int page,@PathVariable int size){
		Page<${ClassName}> pageList = ${tableNameLower}Service.findPage(page, size);
		return new Result(true,1000,"查询成功",new PageResult<${ClassName}>(pageList.getTotalElements(), pageList.getContent() ) );
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<${ClassName}> pageList = ${tableNameLower}Service.findSearch(searchMap, page, size);
		return  new Result(true,1000,"查询成功",  new PageResult<${ClassName}>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
	 * 增加
	 * @param ${tableNameLower}
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody ${ClassName} ${tableNameLower}  ){
		${tableNameLower}Service.add(${tableNameLower});
		return new Result(true,1000,"增加成功");
	}
	
	/**
	 * 修改
	 * @param ${tableNameLower}
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody ${ClassName} ${tableNameLower}, @PathVariable String id ){
		${tableNameLower}.setId(id);
		${tableNameLower}Service.update(${tableNameLower});		
		return new Result(true,1000,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		${tableNameLower}Service.deleteById(id);
		return new Result(true,1000,"删除成功");
	}
	
}
