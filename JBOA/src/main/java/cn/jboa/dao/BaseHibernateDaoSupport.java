package cn.jboa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("unchecked")
public abstract class BaseHibernateDaoSupport<T> extends HibernateDaoSupport implements BaseDao<T> 
{
	private Class<T> cls;
	public BaseHibernateDaoSupport() 
	{
		cls = (Class<T>) ((ParameterizedType)this.getClass().
				getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public void save(T instance) 
	{
		this.getHibernateTemplate().save(instance);
	}

	public void update(T instance)
	{
		this.getHibernateTemplate().update(instance);
	}

	public void saveOrUpdate(T instance) 
	{
		this.getHibernateTemplate().saveOrUpdate(instance);
	}

	public T merge(T instance)
	{
		return this.getHibernateTemplate().merge(instance);
	}

	public void delete(T instance)
	{
		this.getHibernateTemplate().delete(instance);
	}

	public void delete(Collection<T> instances)
	{
		this.getHibernateTemplate().deleteAll(instances);
	}

	public Integer delete(final Object[] ids) 
	{
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() 
		{

			public Integer doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String hql = "delete from " + cls.getSimpleName() + " where id in(:ids)";
				return session.createQuery(hql).setParameterList("ids", ids).executeUpdate();
			}
		});
	}

	public T get(Serializable id) 
	{
		
		return this.getHibernateTemplate().get(cls, id);
	}

	public List<T> findAll() 
	{
		String hql = "from " + cls.getSimpleName();
		return this.getHibernateTemplate().find(hql);
	}

	public <E> List<E> findForPage(final String hql, final int pageNo, final int pageSize, final Object... values)
	{
		return this.getHibernateTemplate().execute(new HibernateCallback<List<E>>()
		{
			public List<E> doInHibernate(Session session) throws HibernateException, SQLException 
			{
				Query query = session.createQuery(hql);
				if (values != null)
				{
					for (int i = 0; i < values.length; i++) 
					{
						query.setParameter(i, values[i]);
					}
				}
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

	public Number getTotalCount(String hql, Object... values)
	{
		if (hql.startsWith("select "))
			hql = "select count(*) " + hql.substring(hql.indexOf(" from ") + 1);
		else
			hql = "select count(*) " + hql;
		return (Number) this.getHibernateTemplate().find(hql, values).get(0);
	}

	public <E> List<E> find(String hql, Object... values) 
	{
		return this.getHibernateTemplate().find(hql, values);
	}

}
