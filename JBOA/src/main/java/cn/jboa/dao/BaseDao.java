package cn.jboa.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T> 
{
	/**
	 * 根据实例保存
	 * @param instance
	 */
	public void save(T instance);
	
	/**
	 * 根据实例修改
	 * @param instance
	 */
	public void update(T instance);
	
	/**
	 * 根据实例保存或修改
	 * @param instance
	 */
	public void saveOrUpdate(T instance);
	
	/**
	 * 根据实例通过marge修改
	 * @param instance
	 * @return
	 */
	public T merge(T instance);
	
	/**
	 * 根据实例删除
	 * @param instance
	 */
	public void delete(T instance);
	
	/**
	 * 根据实例集合进行多个实例的删除
	 * @param instances
	 */
	public void delete(Collection<T> instances);
	
	/**
	 * 根据实例id数组，实现多个记录的删除
	 * @param cls
	 * @param ids
	 * @return
	 */
	public Integer delete(Object[] ids); // Class<T> cls, 
	
	/**
	 * 根据id查询
	 * @param cls
	 * @param id
	 * @return
	 */
	public T get(Serializable id); //Class<T> cls, 
	
	/**
	 * 根据实例查询所有
	 * @return
	 */
	public List<T> findAll(); // from Xxx Class<T> cls
	
	// 投影查询，自定义
	// List<User> users = findForPage()
	// List<Order> orders = findForPage()
	/**
	 * 根据接收类型返回分页查询集合
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public <E> List<E> findForPage(String hql, int pageNo, int pageSize, Object... values); // ?
//	public <E> List<E> findForPage(String hql, int pageNo, int pageSize);
//	public <E> List<E> findForPage(String hql, int pageNo, int pageSize, Object value); // :属性名
//	public <E> List<E> findForPage(String hql, int pageNo, int pageSize, Map<String, Object> values); // :key
//	public <E> List<E> findForPage(String hql, int pageNo, int pageSize, Object[] values); // ?
	
	/**
	 * 查询总记录数
	 * @param hql
	 * @param values
	 * @return
	 */
	public Number getTotalCount(String hql, Object... values);
	
	/**
	 * 根据接收类型返回查询集合
	 * @param hql
	 * @param values
	 * @return
	 */
	public <E> List<E> find(String hql, Object... values);
}
