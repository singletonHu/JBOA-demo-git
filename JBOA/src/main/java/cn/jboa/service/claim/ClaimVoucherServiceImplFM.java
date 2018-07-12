package cn.jboa.service.claim;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;

@Service("claimVoucherServiceFM")
@Transactional
public class ClaimVoucherServiceImplFM extends ClaimVoucherServiceImpl 
{
	@Override
	protected void buildBaseHql(Employee emp, String status, Date startDate, Date endDate, StringBuilder hql,
			List<Object> values) 
	{
		hql.append("select v from ClaimVoucher v, Employee e where v.creator.sn = e.sn and e.sysDepartment.id = ?");
		values.add(emp.getSysDepartment().getId());
		if (status != null && status.length() != 0)
		{
			hql.append(" and v.status = ?");
			values.add(status);
		}
		else
		{
			hql.append("and v.status >= ?");
			values.add(Constants.CLAIMVOUCHER_SUBMITTED);
		}
		
		if (startDate != null)
		{
			hql.append(" and v.createTime >= ?");
			values.add(startDate);
		}
		
		if (endDate != null)
		{
			hql.append(" and v.createTime < ?");
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
			hql.append(" order by v.status, v.createTime desc");
		else
			hql.append(" order by v.createTime desc");
	}

}
