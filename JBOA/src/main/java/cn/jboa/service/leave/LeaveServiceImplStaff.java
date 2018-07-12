package cn.jboa.service.leave;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;

@Repository("leaveServiceStaff")
@Transactional
public class LeaveServiceImplStaff extends LeaveServiceImpl 
{

	@Override
	public void saveLeave(Leave leave) 
	{
		leave.setCreateTime(new Date());
		getLeaveDao().save(leave);
	}

	@Override
	protected void buildBaseHql(Employee emp, Leave leave, Date startDate, Date endDate, StringBuilder hql,
			List<Object> values) 
	{
		hql.append("from Leave where creator.sn = ?");
		values.add(emp.getSn());
		
		if (!(leave == null || leave.getLeaveType().length() == 0 || leave.getLeaveType() == null))
		{
			hql.append(" and leaveType = ?");
			values.add(leave.getLeaveType());
		}
		
		if (startDate != null)
		{
			hql.append(" and createTime >= ?");
			values.add(startDate);
		}
		
		if (endDate != null)
		{
			hql.append(" and createTime < ?");
			Calendar cld = Calendar.getInstance();
			cld.setTime(endDate);
			cld.add(Calendar.DAY_OF_MONTH, 1);
			values.add(cld.getTime());
		}
	}

	@Override
	protected void addOrders(Leave leave, StringBuilder hql) 
	{
		hql.append(" order by createTime desc");
	}

}
