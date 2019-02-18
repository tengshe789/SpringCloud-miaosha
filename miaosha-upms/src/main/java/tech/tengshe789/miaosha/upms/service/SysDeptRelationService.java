package tech.tengshe789.miaosha.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.tengshe789.miaosha.upms.entity.SysDept;
import tech.tengshe789.miaosha.upms.entity.SysDeptRelation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lengleng
 * @since 2018-02-12
 */
public interface SysDeptRelationService extends IService<SysDeptRelation> {

	/**
	 * 新建部门关系
	 *
	 * @param sysDept 部门
	 */
	void insertDeptRelation(SysDept sysDept);

	/**
	 * 通过ID删除部门关系
	 *
	 * @param id
	 */
	void deleteAllDeptRealtion(Integer id);

	/**
	 * 更新部门关系
	 *
	 * @param relation
	 */
	void updateDeptRealtion(SysDeptRelation relation);
}
