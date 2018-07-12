package cn.jboa.service.leave;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import cn.jboa.dao.leave.LeaveDao;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;
import cn.jboa.util.PaginationSupport;

@Transactional
public abstract class LeaveServiceImpl implements LeaveService 
{

	@Autowired
	@Qualifier("leaveDao")
	private LeaveDao leaveDao;
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	
	public LeaveDao getLeaveDao() {
		return leaveDao;
	}

	public Leave findLeaveDetail(Leave leave)
	{
		return leaveDao.get(leave.getId());
	}
	
	@Override
	public void checkLeave(Leave leave) {
		
	}

	@Override
	public void saveLeave(Leave leave) 
	{
	}

	@Override
	public PaginationSupport<Leave> getLeaveByPage(Date startDate, Date endDate, Integer pageNo,
			Integer pageSize, Employee emp, Leave leave) {
		PaginationSupport<Leave> result = new PaginationSupport<Leave>();
		if (pageNo > 0)
		{
			result.setCurrPageNo(pageNo);
		}
		if (pageSize > 0)
		{
			result.setPageSize(pageSize);
		}
		
		StringBuilder hql = new StringBuilder("");
		List<Object> values = new ArrayList<Object>();
		
		this.buildBaseHql(emp, leave, startDate, endDate, hql, values);
		
		int count = leaveDao.getTotalCount(hql.toString(), values.toArray()).intValue();
		result.setTotalCount(count);
		if (count > 0)
		{
			int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
			result.setTotalPageCount(pageCount);
			if (result.getCurrPageNo() > pageCount)
			{
				result.setCurrPageNo(pageCount);
			}
			
			this.addOrders(leave, hql);
			
			List<Leave> items = leaveDao.findForPage(hql.toString(), result.getCurrPageNo(), 
					result.getPageSize(), values.toArray());
			result.setItems(items);
		}
		
		return result;
	}

	protected abstract void buildBaseHql(Employee emp, Leave leave,
			Date startDate, Date endDate, StringBuilder hql, List<Object> values);

	protected abstract void addOrders(Leave leave, StringBuilder hql);

}
