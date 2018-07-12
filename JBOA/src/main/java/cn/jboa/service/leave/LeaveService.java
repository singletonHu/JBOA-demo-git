package cn.jboa.service.leave;

import java.util.Date;

import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;
import cn.jboa.util.PaginationSupport;

public interface LeaveService 
{
	/**
	 * 申请请假
	 * @param leave
	 */
	public void saveLeave(Leave leave);
	
	/**
	 * 按条件分页查询请假列表
	 * @param status	状态
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param pageNo	当前页
	 * @param pageSize	页面大小
	 * @param createsn	员工编号
	 * @return
	 */
	public PaginationSupport<Leave> getLeaveByPage(Date startDate, Date endDate,
			Integer pageNo, Integer pageSize, Employee emp, Leave leave);
	
	public Leave findLeaveDetail(Leave leave);
	
	public void checkLeave(Leave leave);
}
