package cn.jboa.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.opensymphony.xwork2.ActionContext;
import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.service.emp.EmployeeService;

@SuppressWarnings("rawtypes")
public class EmployeeAction extends BaseAction 
{
	@Autowired
	@Qualifier("empService")
	private EmployeeService empService;
	private static final long serialVersionUID = 1L;
	private Map<String, Object> result = new HashMap<>();
	private String sn;
	private String password;
	private Employee employee;
	// 验证码
	private String random;				
	
	public String login()
	{
		ActionContext ac = ActionContext.getContext();
		
		Employee loginEmp = null;
		Map<String, Object> session = ac.getSession();
		if (!random.equals(session.get("random"))) 
		{
			result.put("msg", "验证码输入有误，请重新输入！");
			return SUCCESS;
		}

		loginEmp = empService.doLogin(sn);
		if (null != loginEmp) 
		{
			if (loginEmp.getPassword().equals(password)) 
			{
				Employee manager = empService.getManager(loginEmp);
				session.put(Constants.AUTH_EMPLOYEE, loginEmp);
				session.put(Constants.EMPLOYEE_POSITION, loginEmp.getSysPosition().getNameCn());
				session.put(Constants.AUTH_EMPLOYEE_MANAGER, manager);
				result.put("msg", "");
				return SUCCESS;
			} else
			{
				result.put("msg", "密码输入有误，请重新输入！");
				return SUCCESS;
			}
		} else 
		{
			result.put("msg", "员工号输入有误或已离职，请重新输入！");
			return SUCCESS;
		}
	}
	
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}



	public Map<String, Object> getResult() {
		return result;
	}



	public void setResult(Map<String, Object> result) {
		this.result = result;
	}



	public String getSn() {
		return sn;
	}



	public void setSn(String sn) {
		this.sn = sn;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setEmpService(EmployeeService empService) {
		this.empService = empService;
	}
	
}
