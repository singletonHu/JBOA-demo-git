package cn.jboa.dao.emp;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import cn.jboa.common.Constants;
import cn.jboa.dao.BaseHibernateDaoSupport;
import cn.jboa.entity.Employee;

@Repository("empDao")
public class EmployeeDaoImpl extends BaseHibernateDaoSupport<Employee> implements EmployeeDao 
{

	private static Employee GM;
	
	public EmployeeDaoImpl() {}
	
	@Autowired
	public EmployeeDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory)
	{
		this.setSessionFactory(sessionFactory);
	}
	
	public Employee getManager(Employee emp) throws Exception
	{
		Employee manager = null;
		String hql = "from Employee where sysPosition.nameCn = ? and sysDepartment = ? and status = ?";
		@SuppressWarnings("unchecked")
		List<Employee> emps = this.getHibernateTemplate().find(hql,Constants.POSITION_FM,emp.getSysDepartment(),Constants.EMPLOYEE_STAY);
		if (emps != null && emps.size() > 0)
		{
			manager = emps.get(0);	
		}
		return manager;
	}

	@Override
	public Employee getCashier()
	{
		String hql = "from Employee where status = ? and sysPosition.nameCn = ?";
		return (Employee) this.find(hql, Constants.EMPLOYEE_STAY, Constants.POSITION_CASHIER).get(0);
	}

	@Override
	public Employee getGM()
	{
		if (GM == null)
		{
			String hql = "from Employee where status = ? and sysPosition.nameCn = ?";
			GM = (Employee) this.find(hql, Constants.EMPLOYEE_STAY, Constants.POSITION_GM).get(0);
		}
		return GM;
	}

}
