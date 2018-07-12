package cn.jboa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import cn.jboa.common.Constants;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.claim.ClaimVoucherService;

@SuppressWarnings("rawtypes")
public class ClaimVoucherAction extends BaseAction 
{
	private static final long serialVersionUID = 1L;
	private ClaimVoucher claimVoucher;
	private List<ClaimVoucherDetail> detailList;
	private String status;
	private Date startDate;
	private Date endDate;
	
	private static Map<String, ClaimVoucherService> claimVoucherServices;
	
	
	private static Map<String, String> statusMap;
	static 
	{
		statusInit();
		serviceInit();
	}
	
	// 初始化状态
	private static void statusInit()
	{
		statusMap = new LinkedHashMap<String, String>();
		statusMap.put(Constants.CLAIMVOUCHER_CREATED, "新创建");
		statusMap.put(Constants.CLAIMVOUCHER_SUBMITTED, "已提交");
		statusMap.put(Constants.CLAIMVOUCHER_APPROVING, "待审批");
		statusMap.put(Constants.CLAIMVOUCHER_BACK, "已打回");
		statusMap.put(Constants.CLAIMVOUCHER_APPROVED, "已审批");
		statusMap.put(Constants.CLAIMVOUCHER_PAID, "已付款");
		statusMap.put(Constants.CLAIMVOUCHER_TERMINATED, "已终止");
	}
	
	// 初始化不同角色业务对象
	private static void serviceInit()
	{
		claimVoucherServices = new HashMap<String , ClaimVoucherService>();
		claimVoucherServices.put(Constants.POSITION_STAFF_EN, claimVoucherServiceStaff);
		claimVoucherServices.put(Constants.POSITION_FM_EN, claimVoucherServiceFM);
		claimVoucherServices.put(Constants.POSITION_GM_EN, claimVoucherServiceGM);
		claimVoucherServices.put(Constants.POSITION_CASHIER_EN, claimVoucherServiceCashier);
	}
	
	
	public String toCheck()
	{
		claimVoucher = this.getClaimVoucherService().findClaimVoucherById(claimVoucher.getId());
		return "toCheck";
	}
	
	// 
	public String saveClaimVoucher()
	{
		claimVoucher.setCreator(getCurrentEmployee());
		if (claimVoucher.getStatus().equals(Constants.CLAIMVOUCHER_SUBMITTED))
		{
			claimVoucher.setNextDeal(getCurrentEmployeeManager());
		}
		else
		{
			claimVoucher.setNextDeal(getCurrentEmployee());
		}
		
		claimVoucher.setDetailList(detailList);
		for (ClaimVoucherDetail detail : detailList) 
		{
			detail.setBizClaimVoucher(claimVoucher);
		}
		this.getClaimVoucherService().addClaimVoucher(claimVoucher);
		return "redirectList";
	}
	
	@SuppressWarnings("unchecked")
	public String searchClaimVoucher()
	{
		switch(this.getCurrentEmployee().getSysPosition().getNameCn())
		{
			case Constants.POSITION_CASHIER:
				this.cashierStatusInit();
				break;
			case Constants.POSITION_GM:
				this.gmStatusInit();
				break;
			case Constants.POSITION_FM:
				this.fmStatusInit();
				break;
			default:
				this.stuffStatusInit();
				break;
		}
		this.setPageSupport(this.getClaimVoucherService().getClaimVoucherByPage(status, startDate, endDate, pageNo, pageSize,
				this.getCurrentEmployee()));
		return "list";
	}
	
	public String getClaimVoucherById()
	{
		claimVoucher = this.getClaimVoucherService().findClaimVoucherById(claimVoucher.getId());
		return "view";
	}
	
	public String toUpdate()
	{
		claimVoucher = this.getClaimVoucherService().findClaimVoucherById(claimVoucher.getId());
		return "toUpdate";
	}
	
	public String updateClaimVoucher()
	{
		if (claimVoucher.getStatus().equals(Constants.CLAIMVOUCHER_SUBMITTED))
		{
			claimVoucher.setNextDeal(getCurrentEmployeeManager());
		}
		claimVoucher.setDetailList(detailList);
		
		this.getClaimVoucherService().updateClaimVoucher(claimVoucher);
		return "redirectList";
	}
	
	public String deleteClaimVoucherById()
	{
		this.getClaimVoucherService().deleteClaimVoucher(claimVoucher);
		return "redirectList";
	}
	
	
	protected void stuffStatusInit()
	{
		
	}
	
	protected void fmStatusInit()
	{
		
	}
	
	protected void gmStatusInit()
	{
		if (status == null)
		{
			this.setStatus(Constants.CLAIMVOUCHER_APPROVING);
		}
	}
	
	protected void cashierStatusInit()
	{
		if (status == null)
		{
			this.setStatus(Constants.CLAIMVOUCHER_APPROVED);
		}
	}
	
	
	
	
	
	
	public ClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}
	public void setClaimVoucher(ClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}
	public List<ClaimVoucherDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getStartDate() {
		return startDate;
	}

	public ClaimVoucherService getClaimVoucherService() {
		return claimVoucherServices.get(this.getPositionForShort());
	}

	public static Map<String, ClaimVoucherService> getClaimVoucherServices() {
		return claimVoucherServices;
	}

	public static void setClaimVoucherServices(Map<String, ClaimVoucherService> claimVoucherServices) {
		ClaimVoucherAction.claimVoucherServices = claimVoucherServices;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	public Map<String, String> getStatusMap() {
		return statusMap;
	}

}
