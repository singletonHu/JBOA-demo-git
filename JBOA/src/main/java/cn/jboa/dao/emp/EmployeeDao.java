package cn.jboa.dao.emp;

import cn.jboa.dao.BaseDao;
import cn.jboa.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee>
{
	
	/**
	 * 获取部门经理
	 * @param emp
	 * @return
	 */
	public Employee getManager(Employee emp)throws Exception;
	
	/**
	 * 获取财务
	 * @return
	 * @throws Exception
	 */
	public Employee getCashier();
	
	/**
	 * 获取总经理
	 * @return
	 * @throws Exception
	 */
	public Employee getGM();
}
