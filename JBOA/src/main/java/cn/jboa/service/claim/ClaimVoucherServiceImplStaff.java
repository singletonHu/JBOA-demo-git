package cn.jboa.service.claim;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.jboa.entity.Employee;

@Service("claimVoucherServiceStaff")
@Transactional
public class ClaimVoucherServiceImplStaff extends ClaimVoucherServiceImpl 
{
	@Override
	protected void buildBaseHql(Employee emp, String status, Date startDate, Date endDate, StringBuilder hql,
			List<Object> values) 
	{
		hql.append("from ClaimVoucher where creator.sn = ?");
		values.add(emp.getSn());
		if (status != null && status.length() != 0)
		{
			hql.append(" and status = ?");
			values.add(status);
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
	protected void addOrders(String status, StringBuilder hql) 
	{
		if (status == null || status.length() == 0)
			hql.append(" order by status, createTime desc");
		else
			hql.append(" order by createTime desc");
	}
}
