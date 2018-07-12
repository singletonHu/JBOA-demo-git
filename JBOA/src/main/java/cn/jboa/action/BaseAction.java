package cn.jboa.action;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.service.claim.ClaimVoucherService;
import cn.jboa.util.PaginationSupport;

public class BaseAction<T> extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	protected Integer pageNo = 1;
	protected Integer pageSize = 5;
	protected PaginationSupport<T> pageSupport;
	@Autowired
	@Qualifier("claimVoucherServiceStaff")
	protected static ClaimVoucherService claimVoucherServiceStaff;
	
	@Autowired
	@Qualifier("claimVoucherServiceFM")
	protected static ClaimVoucherService claimVoucherServiceFM;
	
	@Autowired
	@Qualifier("claimVoucherServiceGM")
	protected static ClaimVoucherService claimVoucherServiceGM;
	
	@Autowired
	@Qualifier("claimVoucherServiceCashier")
	protected static ClaimVoucherService claimVoucherServiceCashier;

	protected Map<String, Object> getSession()
	{
		return ActionContext.getContext().getSession();
	}
	
	protected Employee getCurrentEmployee()
	{
		return (Employee) this.getSession().get(Constants.AUTH_EMPLOYEE);
	}
	
	protected Employee getCurrentEmployeeManager()
	{
		return (Employee) this.getSession().get(Constants.AUTH_EMPLOYEE_MANAGER);
	}
	
	public String getPositionForShort() {
		switch (this.getCurrentEmployee().getSysPosition().getNameCn()) {
		case Constants.POSITION_CASHIER:
			return Constants.POSITION_CASHIER_EN;
		case Constants.POSITION_FM:
			return Constants.POSITION_FM_EN;
		case Constants.POSITION_GM:
			return Constants.POSITION_GM_EN;
		default:
			return Constants.POSITION_STAFF_EN;
		}
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PaginationSupport<T> getPageSupport() {
		return pageSupport;
	}

	public void setPageSupport(PaginationSupport<T> pageSupport) {
		this.pageSupport = pageSupport;
	}

	public static ClaimVoucherService getClaimVoucherServiceStaff() {
		return claimVoucherServiceStaff;
	}

	public static void setClaimVoucherServiceStaff(ClaimVoucherService claimVoucherServiceStaff) {
		BaseAction.claimVoucherServiceStaff = claimVoucherServiceStaff;
	}

	public static ClaimVoucherService getClaimVoucherServiceFM() {
		return claimVoucherServiceFM;
	}

	public static void setClaimVoucherServiceFM(ClaimVoucherService claimVoucherServiceFM) {
		BaseAction.claimVoucherServiceFM = claimVoucherServiceFM;
	}

	public static ClaimVoucherService getClaimVoucherServiceGM() {
		return claimVoucherServiceGM;
	}

	public static void setClaimVoucherServiceGM(ClaimVoucherService claimVoucherServiceGM) {
		BaseAction.claimVoucherServiceGM = claimVoucherServiceGM;
	}

	public static ClaimVoucherService getClaimVoucherServiceCashier() {
		return claimVoucherServiceCashier;
	}

	public static void setClaimVoucherServiceCashier(ClaimVoucherService claimVoucherServiceCashier) {
		BaseAction.claimVoucherServiceCashier = claimVoucherServiceCashier;
	}
}
