package cn.jboa.dao.claim;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.jboa.dao.BaseHibernateDaoSupport;
import cn.jboa.entity.ClaimVoucher;

@Repository("claimVoucherDao")
public class ClaimVoucherDaoImpl extends BaseHibernateDaoSupport<ClaimVoucher> implements ClaimVoucherDao 
{
	public ClaimVoucherDaoImpl() {	}
	@Autowired
	public ClaimVoucherDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory)
	{
		this.setSessionFactory(sessionFactory);
	}
}
