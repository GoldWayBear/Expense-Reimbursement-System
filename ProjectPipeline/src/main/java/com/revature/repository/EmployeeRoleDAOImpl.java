/**
 * 
 */
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.model.Employee;
import com.revature.model.EmployeeRole;
import com.revature.util.HibernateUtil;

/**
 * @author Jinwei Xiong
 *
 */
public class EmployeeRoleDAOImpl implements EmployeeRoleDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeRoleDAOImpl.class);
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session currentSession;

	
	public EmployeeRoleDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<EmployeeRole> findAllRoles() {
		// TODO Auto-generated method stub
		List<EmployeeRole> emplList = new ArrayList<EmployeeRole>();
		try {
	        currentSession = sessionFactory.openSession();
	        emplList = currentSession.createQuery("From EmployeeRole").getResultList();
		}catch(Exception e) {
			LOGGER.error("Error at finding all employeeroles.",e);
			e.printStackTrace();
			emplList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplList;
	}

	public EmployeeRole findEmployeeRoleById(int roleid) {
		// TODO Auto-generated method stub
		EmployeeRole role = null;
		try {
	        currentSession = sessionFactory.openSession();
	        //empl_role
	        String hql = "Select * From project1.empl_role as emplRole where emplRole.roleId=:roleId";
	        //String hql = "Select * From EmployeeRole where roleId=:roleId";
			Query<EmployeeRole> query = currentSession.createNativeQuery(hql,EmployeeRole.class);
			query.setParameter("roleId", roleid);
			role = query.uniqueResult();
		}catch(Exception e) {
			LOGGER.error("Error at finding role by role ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return role;
	}

	public boolean addRole(EmployeeRole role) {
		// TODO Auto-generated method stub
		try {
	        currentSession = sessionFactory.openSession();
	        currentSession.beginTransaction();
	        LOGGER.debug(currentSession.isConnected());
	        System.out.println("currentSession connected:"+currentSession.isConnected());
	        currentSession.save(role);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully adding a role.");
			return true;
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding a role, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean updateRole(EmployeeRole role) {
		// TODO Auto-generated method stub
		try {
			// Getting Session Object From SessionFactory
	        currentSession = sessionFactory.openSession();
	        // Getting Transaction Object From Session Object
	        currentSession.beginTransaction();
	        currentSession.update(role);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully updating a role.");
			return true;
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating a role, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteRoleById(int roleid) {
		try {
			// Getting Session Object From SessionFactory
			//currentSession.close();
	        currentSession = sessionFactory.openSession();
	        // Getting Transaction Object From Session Object
	        currentSession.beginTransaction();
	        EmployeeRole role = findEmployeeRoleById(roleid);

	        currentSession = sessionFactory.openSession();
	        currentSession.beginTransaction();	        
	        System.out.println("DADADA: "+role.getRoleId() +" , "+role.getRole());
	        if(role !=null) {
		        currentSession.delete(role);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting a role.");	        	
				return true;
	        }else {
				LOGGER.info("This role Id doesn't exsit.");
				return false;	        	
	        }
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting a role, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

}
