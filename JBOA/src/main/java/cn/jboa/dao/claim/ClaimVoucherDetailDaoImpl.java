package cn.jboa.dao.claim;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.jboa.dao.BaseHibernateDaoSupport;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;

@Repository("claimVoucherDetailDao")
public class ClaimVoucherDetailDaoImpl extends BaseHibernateDaoSupport<ClaimVoucherDetail>
		implements ClaimVoucherDetailDao 
		{

	public ClaimVoucherDetailDaoImpl() {
	}
	@Autowired
	public ClaimVoucherDetailDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory)
	{
		this.setSessionFactory(sessionFactory);
	}
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher) 
	{
		String hql = "delete from ClaimVoucherDetail where bizClaimVoucher.id = ?";
		return this.getHibernateTemplate().bulkUpdate(hql, claimVoucher.getId());
	}

}
