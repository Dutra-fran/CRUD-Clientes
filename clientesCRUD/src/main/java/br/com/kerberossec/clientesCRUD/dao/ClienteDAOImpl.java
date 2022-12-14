package br.com.kerberossec.clientesCRUD.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.kerberossec.clientesCRUD.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	public static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch(Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void adicionarCliente(Cliente cliente) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			session.save(cliente);
			session.getTransaction().commit();
		} catch(RuntimeException e) {
			if(trns != null)
				trns.rollback();
		} finally {
			session.close();
		}
	}

	public List<Cliente> listarClientes() {
		Session session = getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
		criteriaQuery.from(Cliente.class);
		
		return session.createQuery(criteriaQuery).getResultList();
	}

	public Cliente getCliente(String cpf) {
		Session session = getSessionFactory().openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
			Root<Cliente> root = criteriaQuery.from(Cliente.class);
			criteriaQuery.where(builder.equal(root.get("cpf"), cpf));
			return session.createQuery(criteriaQuery).getSingleResult();
		} catch(NoResultException e) {}
		
		return null;
	}

	public void excluirCliente(Cliente cliente) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			session.delete(cliente);
			session.getTransaction().commit();
		} catch(RuntimeException e) {
			if(trns != null)
				trns.rollback();
		} finally {
			session.close();
		}
	}

	public void atualizarCliente(Cliente cliente) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			session.update(cliente);
			session.getTransaction().commit();
		} catch(RuntimeException e) {
			if(trns != null)
				trns.rollback();
		} finally {
			session.close();
		}
	}
}
