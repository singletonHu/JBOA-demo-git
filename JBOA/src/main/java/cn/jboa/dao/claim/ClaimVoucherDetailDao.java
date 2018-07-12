package cn.jboa.dao.claim;

import cn.jboa.dao.BaseDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;

public interface ClaimVoucherDetailDao extends BaseDao<ClaimVoucherDetail> 
{
	/**
	 * 根据报销单编号删除明细
	 * @param claimVoucher
	 * @return
	 */
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher);
}
