package cn.jboa.service.leave;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;

@Repository("leaveServiceFM")
@Transactional
public class LeaveServiceImplFM extends LeaveServiceImpl
{

	@Override
	protected void buildBaseHql(Employee emp, Leave leave, Date startDate, Date endDate, StringBuilder hql,
			List<Object> values)
	{
		hql.append("select l from Leave l, Employee e where l.creator.sn = e.sn and e.sysDepartment.id = ?");
		values.add(emp.getSysDepartment().getId());
		
		if (!(leave.getStatus().length() == 0))
		{
			hql.append(" and l.status = ?");
			values.add(leave.getStatus());
		}
		
		if (startDate != null)
		{
			hql.append(" and l.createTime >= ?");
			values.add(startDate);
		}
		
		if (endDate != null)
		{
			hql.append(" and l.createTime < ?");
			Calendar cld = Calendar.getInstance();
			cld.setTime(endDate);
			cld.add(Calendar.DAY_OF_MONTH, 1);
			values.add(cld.getTime());
		}
	}

	@Override
	protected void addOrders(Leave leave, StringBuilder hql) 
	{
		if (leave.getStatus().length() == 0)
		{
			hql.append(" order by status, createTime desc");
		}
		else
		{
			hql.append(" order by createTime desc");
		}
	}
	
	@Override
	public void checkLeave(Leave leave) 
	{
		Leave updateLeave =  getLeaveDao().get(leave.getId());
		if (leave.getStatus().equals(Constants.LEAVESTATUS_APPROVED))
		{
			updateLeave.setStatus(Constants.LEAVESTATUS_APPROVED);
			updateLeave.setNextDeal(null);
		}
		else
		{
			updateLeave.setStatus(Constants.LEAVESTATUS_BACK);
			updateLeave.setNextDeal(null);
		}
		updateLeave.setApproveOpinion(leave.getApproveOpinion());
		updateLeave.setModifyTime(new Date());
	}

}
