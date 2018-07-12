package cn.jboa.service.claim;

import java.util.Date;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.Employee;
import cn.jboa.util.PaginationSupport;

public interface ClaimVoucherService 
{
	/**
	 * 添加报销单
	 * @param claimVoucher
	 * @return
	 */
	public void addClaimVoucher(ClaimVoucher claimVoucher);
	
	/**
	 * 按条件分页查询报销单列表
	 * @param status	状态
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param pageNo	当前页
	 * @param pageSize	页面大小
	 * @param createsn	员工编号
	 * @return
	 */
	public PaginationSupport<ClaimVoucher> getClaimVoucherByPage(String status, Date startDate, Date endDate,
			Integer pageNo, Integer pageSize, Employee emp);
	
	/**
	 * 根据id获取报销单详细信息
	 * @param id
	 * @return
	 */
	public ClaimVoucher findClaimVoucherById(Long id);
	
	/**
	 * 更新报销单
	 * @param claimVoucher
	 */
	public void updateClaimVoucher(ClaimVoucher claimVoucher);
	
	/**
	 * 删除报销单
	 * @param claimVoucher
	 */
	public void deleteClaimVoucher(ClaimVoucher claimVoucher);
}
