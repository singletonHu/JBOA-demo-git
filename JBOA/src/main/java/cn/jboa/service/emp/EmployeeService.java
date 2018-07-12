package cn.jboa.service.emp;

import cn.jboa.entity.Employee;

public interface EmployeeService 
{
	/**
	 * 员工登录
	 * @param sn
	 * @param password
	 * @return
	 */
	public Employee doLogin(String sn);
	
	/**
	 * 获取部门经理
	 * @param emp
	 * @return
	 */
	public Employee getManager(Employee emp);
}
