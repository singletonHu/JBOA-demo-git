package cn.jboa.dao.leave;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.jboa.dao.BaseHibernateDaoSupport;
import cn.jboa.entity.Leave;

@Repository("leaveDao")
public class LeaveDaoImpl extends BaseHibernateDaoSupport<Leave> implements LeaveDao 
{
	public LeaveDaoImpl() {	}
	
	@Autowired
	public LeaveDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) 
	{
		this.setSessionFactory(sessionFactory);
	}
}
