package cn.jboa.service.claim;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import cn.jboa.common.Constants;
import cn.jboa.dao.claim.CheckResultDaoImpl;
import cn.jboa.dao.claim.ClaimVoucherDao;
import cn.jboa.dao.claim.ClaimVoucherDetailDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.entity.Employee;
import cn.jboa.util.PaginationSupport;

@Transactional
public abstract class ClaimVoucherServiceImpl implements ClaimVoucherService 
{

	@Autowired
	@Qualifier("claimVoucherDao")
	private ClaimVoucherDao claimVoucherDao;
	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}
	
	@Autowired
	@Qualifier("claimVoucherDetailDao")
	private ClaimVoucherDetailDao claimVoucherDetailDao;
	public void setClaimVoucherDetailDao(ClaimVoucherDetailDao claimVoucherDetailDao) {
		this.claimVoucherDetailDao = claimVoucherDetailDao;
	}
	
	@Autowired
	@Qualifier("checkResultDao")
	private CheckResultDaoImpl checkResultDao;
	public void setCheckResultDao(CheckResultDaoImpl checkResultDao) {
		this.checkResultDao = checkResultDao;
	}
	
	public ClaimVoucher findClaimVoucherById(Long id) 
	{
		return claimVoucherDao.get(id);
	}
	
	public void addClaimVoucher(ClaimVoucher claimVoucher) 
	{
		claimVoucher.setCreateTime(new Date());
		claimVoucher.setModifyTime(claimVoucher.getCreateTime());
		claimVoucherDao.save(claimVoucher);
	}

	
	/*查询*/
	public PaginationSupport<ClaimVoucher> getClaimVoucherByPage(String status, Date startDate, Date endDate,
			Integer pageNo, Integer pageSize, Employee emp) 
	{
		PaginationSupport<ClaimVoucher> result = new PaginationSupport<ClaimVoucher>();
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
		
		this.buildBaseHql(emp, status, startDate, endDate, hql, values);
		
		int count = claimVoucherDao.getTotalCount(hql.toString(), values.toArray()).intValue();
		result.setTotalCount(count);
		if (count > 0)
		{
			int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
			result.setTotalPageCount(pageCount);
			if (result.getCurrPageNo() > pageCount)
			{
				result.setCurrPageNo(pageCount);
			}
			
			this.addOrders(status, hql);
			
			List<ClaimVoucher> items = claimVoucherDao.findForPage(hql.toString(), result.getCurrPageNo(), 
					result.getPageSize(), values.toArray());
			result.setItems(items);
		}
		
		return result;
	}

	protected abstract void buildBaseHql(Employee emp, String status,
			Date startDate, Date endDate, StringBuilder hql, List<Object> values);

	protected abstract void addOrders(String status, StringBuilder hql);

	
	
	
	
	
	
	public void updateClaimVoucher(ClaimVoucher claimVoucher) 
	{
		// 删除原报销单明细
		claimVoucherDetailDao.deleteByClaimVoucher(claimVoucher);
		
		// 获得持久态报销单对象,利用脏检查的特性实现更新
		ClaimVoucher updateClaim = claimVoucherDao.get(claimVoucher.getId());
		updateClaim.setModifyTime(new Date());
		updateClaim.setDetailList(claimVoucher.getDetailList());
		updateClaim.setEvent(claimVoucher.getEvent());
		updateClaim.setTotalAccount(claimVoucher.getTotalAccount());
		if (claimVoucher.getStatus().equals(Constants.CLAIMVOUCHER_SUBMITTED))
		{
			updateClaim.setNextDeal(claimVoucher.getNextDeal());
			updateClaim.setStatus(claimVoucher.getStatus());
		}
		
		for (ClaimVoucherDetail d : updateClaim.getDetailList()) 
		{
			d.setBizClaimVoucher(updateClaim);
		}
	}

	public void deleteClaimVoucher(ClaimVoucher claimVoucher) 
	{
		// 删除明细
		claimVoucherDetailDao.deleteByClaimVoucher(claimVoucher);
		// 删除审核记录
		checkResultDao.deleteByClaimVoucher(claimVoucher);
		// 删除报销单
		claimVoucherDao.delete(claimVoucher);
	}
}
