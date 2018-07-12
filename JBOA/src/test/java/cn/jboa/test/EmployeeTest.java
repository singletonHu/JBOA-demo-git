package cn.jboa.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jboa.entity.Employee;
import cn.jboa.service.emp.EmployeeService;
import cn.jboa.service.emp.EmployeeServiceImpl;

public class EmployeeTest
{

	@Test
	public void testDoLogin() 
	{
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeService es = (EmployeeServiceImpl) ctx.getBean("empService");
		Employee emp = es.doLogin("001");
		System.out.println(emp);
	}
}
