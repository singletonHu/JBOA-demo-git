package cn.jboa.service.emp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.jboa.common.Constants;
import cn.jboa.dao.emp.EmployeeDao;
import cn.jboa.entity.Employee;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	@Qualifier("empDao")
	private EmployeeDao employeeDao;

	public Employee doLogin(String sn) 
	{
		Employee emp = null;
		try 
		{
			emp = employeeDao.get(sn);
			if (emp != null)
			{ 
				if (Constants.EMPLOYEE_LEAVE.equals(emp.getStatus()))
				{
					emp = null;
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			emp = null;
		}
		return emp;
	}

	public Employee getManager(Employee emp) 
	{
		Employee manager = null;
		try 
		{
			manager = employeeDao.getManager(emp);
		} catch (Exception e) 
		{
			e.printStackTrace();
			manager = null;
		}
		return manager;
	}
	
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
