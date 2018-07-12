package cn.jboa.dao.claim;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.jboa.dao.BaseHibernateDaoSupport;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucher;

@Repository("checkResultDao")
public class CheckResultDaoImpl extends BaseHibernateDaoSupport<CheckResult> implements CheckResultDao 
{
	public CheckResultDaoImpl() {
	}
	@Autowired
	public CheckResultDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory)
	{
		this.setSessionFactory(sessionFactory);
	}
	
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher) 
	{
		String hql = "delete from CheckResult where claimId.id = ?";
		return this.getHibernateTemplate().bulkUpdate(hql, claimVoucher.getId());
	}

}
