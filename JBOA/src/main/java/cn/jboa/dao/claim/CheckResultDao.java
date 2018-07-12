package cn.jboa.dao.claim;

import cn.jboa.dao.BaseDao;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucher;

public interface CheckResultDao extends BaseDao<CheckResult> 
{
	/**
	 * 根据报销单id，删除审核记录
	 * @param claimVoucher
	 */
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher);
}
