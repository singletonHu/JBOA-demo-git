package cn.jboa.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.jboa.common.Constants;
import cn.jboa.entity.Leave;
import cn.jboa.service.leave.LeaveService;

public class LeaveAction extends BaseAction<Leave> 
{
	private static final long serialVersionUID = 1L;
	
	private Leave leave;
	private Date startDate;
	private Date endDate;
	@Autowired
	@Qualifier("leaveServiceFM")
	private LeaveService leaveServiceFM;
	
	@Autowired
	@Qualifier("leaveServiceStaff")
	private LeaveService leaveServiceStaff;

	private static Map<String, String> leaveTypeMap;
	static 
	{
		leaveTypeMap = new LinkedHashMap<String, String>();
		leaveTypeMap.put(Constants.LEAVE_SICK, Constants.LEAVE_SICK);
		leaveTypeMap.put(Constants.LEAVE_ANNUAL, Constants.LEAVE_ANNUAL);
		leaveTypeMap.put(Constants.LEAVE_CASUAL, Constants.LEAVE_CASUAL);
		leaveTypeMap.put(Constants.LEAVE_MARRIAGE, Constants.LEAVE_MARRIAGE);
		leaveTypeMap.put(Constants.LEAVE_MATERNITY, Constants.LEAVE_MATERNITY);
	}
	
	private static Map<String, String> statusMap;
	static
	{
		statusMap = new LinkedHashMap<String, String>();
		statusMap.put(Constants.LEAVESTATUS_APPROVING, "待审核");
		statusMap.put(Constants.LEAVESTATUS_APPROVED, "已审核");
		statusMap.put(Constants.LEAVESTATUS_BACK, "已打回");
	}
	
	public String toEdit()
	{
		return "toEdit";
	}
	
	public String toCheck()
	{
		leave = leaveServiceFM.findLeaveDetail(leave);
		return "toCheck";
	}
	
	public String checkLeave()
	{
		leaveServiceFM.checkLeave(leave);
		return "redirectList";
	}
	
	public String saveLeave()
	{
		leave.setCreator(getCurrentEmployee());
		leave.setStatus(Constants.LEAVESTATUS_APPROVING);
		leave.setNextDeal(getCurrentEmployeeManager());
		leaveServiceStaff.saveLeave(leave);
		return "redirectList";
	}
	
	public String searchLeave()
	{
		if (Constants.POSITION_FM.equals(this.getCurrentEmployee().getSysPosition().getNameCn()))
		{
			if (leave == null)
			{
				leave = new Leave();
			}
			
			if (leave.getStatus() == null)
			{
				leave.setStatus(Constants.LEAVESTATUS_APPROVING);
			}
			pageSupport = leaveServiceFM.getLeaveByPage(startDate, endDate, pageNo, pageSize, getCurrentEmployee(), leave);
		}
		else
		{
			pageSupport = leaveServiceStaff.getLeaveByPage(startDate, endDate, pageNo, pageSize, getCurrentEmployee(), leave);
		}
		return "list";
	}

	public String getLeaveById()
	{
		if (Constants.POSITION_FM.equals(this.getCurrentEmployee().getSysPosition().getNameCn()))
		{
			leave = leaveServiceFM.findLeaveDetail(leave);
		}
		else
		{
			leave = leaveServiceStaff.findLeaveDetail(leave);
		}
		return "leaveDetail";
	}
	
	public Map<String, String> getLeaveTypeMap() {
		return leaveTypeMap;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}



	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LeaveService getLeaveServiceFM() {
		return leaveServiceFM;
	}

	public void setLeaveServiceFM(LeaveService leaveServiceFM) {
		this.leaveServiceFM = leaveServiceFM;
	}

	public LeaveService getLeaveServiceStaff() {
		return leaveServiceStaff;
	}

	public void setLeaveServiceStaff(LeaveService leaveServiceStaff) {
		this.leaveServiceStaff = leaveServiceStaff;
	}

	
	
}
