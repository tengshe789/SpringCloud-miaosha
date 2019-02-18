package tech.tengshe789.miaosha.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.tengshe789.miaosha.upms.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 * @author lengleng
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

	/**
	 * 关联dept——relation
	 *
	 * @return 数据列表
	 */
	List<SysDept> listDepts();
}
