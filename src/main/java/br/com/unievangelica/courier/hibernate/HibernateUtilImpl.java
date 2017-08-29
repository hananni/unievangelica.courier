package br.com.unievangelica.courier.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;

@Repository("hibernateUtil")
public class HibernateUtilImpl<T, ID extends Serializable> implements HibernateUtil<T, ID> {

	@PersistenceContext //(type = javax.persistence.PersistenceContextType.EXTENDED)
	javax.persistence.EntityManager entityManager;
	
	static Logger log = Logger.getLogger(HibernateUtilImpl.class);
	private Session session = null;
	
	private Class<T> persistedClass;

	protected HibernateUtilImpl() {
	// TODO Auto-generated constructor stub
	}

	public HibernateUtilImpl(Class<T> persistedClass) {
		this();
	    this.persistedClass = persistedClass;
	}
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	public Session openSession() {
		try {
			session = (Session) entityManager.getDelegate();
			return session;	
		} catch (Exception e) {
			log.error("Não é possível abrir a sessão de hibernação", e);
		}
		return null;
	}

	public void closeSession() {
		try {
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	protected void finalize() throws Throwable {
		if(session.isOpen()) {
			closeSession();
		}
	}

	@Override
	public void save(@Valid T entity) {
		try {
			log.debug("Salvando Bean " + entity.getClass().getName());
            entityManager.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Ocorreu um erro ao tentar salvar. MSG ORIGINAL: " + e.getMessage());
        }
	}

	@Override
	public void update(T entity) {
		try {
			entityManager.merge(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("updte failed", re);
			throw re;
		}
	}

	@Override
	public void saveOrUpdate(T entity) {
		try {
			entityManager.merge(entity);
		    entityManager.flush();
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("updte failed", re);
			throw re;
		}
	       
	}
	
	public void delete(T entity) {
		try {
			entityManager.remove(entity);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void deleteById(Long id) {
		T instance = (T) findById(id);
		delete(instance);
	}
	
	public void deleteByCriteria(Criterion... criterion) {
		try {
			Criteria criteria = session.createCriteria(persistedClass);
			for (Criterion ct : criterion) {
				criteria.add(ct);
			}
			
			@SuppressWarnings("unchecked")
			List<T> items = (List<T>) criteria.list();

			for (T item : items) {
				session.delete(item);
			}

		} catch (RuntimeException re) {
			log.error("delete failed", re);
			closeSession();
			throw re;
		}
	}
	
	public T findById(Integer id) {
		return entityManager.find(persistedClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public T findById(Long id, Projection... projection) {
		try {
	    
			Criteria criteria = openSession().createCriteria(persistedClass);
			
			ProjectionList pj = Projections.projectionList();
			
			for (Projection ct : projection) {
				pj.add((Projection) ct);
			}
			
			criteria.add(Restrictions.eq("id", id));
			criteria.setProjection(pj);
			
			return (T) criteria.setResultTransformer(new AliasToBeanResultTransformer(persistedClass)).uniqueResult();
	
			//endTransaction();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public T findBy(Object ... criterion) {
		log.debug("finding " + persistedClass + " instance by criteria");
		try {
			
			Criteria criteria = openSession().createCriteria(persistedClass);

			ArrayList<Criterion> ct = new ArrayList<Criterion>();
			ArrayList<Order> od = new ArrayList<Order>();
			ProjectionList pj = Projections.projectionList();

			for (Object o : criterion) {
				if (o instanceof Criterion) {
					ct.add((Criterion) o);
				} else if (o instanceof Order) {
					od.add((Order) o);
				} else if (o instanceof Projection) {
					pj.add((Projection) o);
				}
			}

			for (Criterion c : ct) {
				criteria.add(c);
			}

			for (Order o : od) {
				criteria.addOrder(o);
			}

			if (pj.getLength() != 0)
				criteria.setProjection(pj);

			return (T) criteria.setResultTransformer(new AliasToBeanResultTransformer(persistedClass)).uniqueResult();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<T> findAll(Object ... criterion) {
		log.debug("finding " + persistedClass + " instance by criteria");
		try {
			
			Criteria criteria = openSession().createCriteria(persistedClass);

			ArrayList<Criterion> ct = new ArrayList<Criterion>();
			ArrayList<Order> od = new ArrayList<Order>();
			ProjectionList pj = Projections.projectionList();

			for (Object o : criterion) {
				if (o instanceof Criterion) {
					ct.add((Criterion) o);
				} else if (o instanceof Order) {
					od.add((Order) o);
				} else if (o instanceof Projection) {
					pj.add((Projection) o);
				}
			}

			for (Criterion c : ct) {
				criteria.add(c);
			}

			for (Order o : od) {
				criteria.addOrder(o);
			}

			if (pj.getLength() != 0)
				criteria.setProjection(pj);

			return criteria.setResultTransformer(new AliasToBeanResultTransformer(persistedClass)).list();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List findByQuery(String query, Hashtable<String, Object> params) {
		log.debug("finding by query " + query);
		try {
			
			Query hql = openSession().createQuery(query);

			if (params != null) {
				for (String key : params.keySet()) {
					hql.setParameter(key, params.get(key));
				}
			}
			
			return hql.list();
			
		
		} catch (RuntimeException re) {
			log.error("find by query failed", re);
			throw re;
		}
	}
	
	public int executeUpdate(String query, Hashtable<String, Object> params) {
		log.debug("executing update " + query);
		try {
			Query hql = openSession().createQuery(query);

			for (String key : params.keySet()) {
				hql.setParameter(key, params.get(key));
			}

			int n = hql.executeUpdate();

			return n;

		} catch (RuntimeException re) {
			log.error("execute update failed", re);

			throw re;
		}
	}
	
	   @SuppressWarnings("unchecked")
	    public List<T> findAll() {
	            return entityManager.createQuery("Select t from " + persistedClass.getSimpleName() + " t").getResultList();
	    }

}
