/**
 * 
 */
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;

import com.revature.model.Employee;
import com.revature.util.HibernateUtil;

/**
 * @author Jinwei Xiong
 *
 */
public class EmployeeDAOImpl implements EmployeeDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeDAOImpl.class);
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session currentSession;
	
	public EmployeeDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
    @SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		List<Employee> emplList = new ArrayList<Employee>();
		try {
	        currentSession = sessionFactory.openSession();
	        emplList = currentSession.createQuery("From Employee").getResultList();
		}catch(Exception e) {
			LOGGER.error("Error at finding all employees.",e);
			e.printStackTrace();
			emplList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplList;
	}

	public List<Employee> findEmployeesByUsername(String username) {
		List<Employee> emplList = new ArrayList<Employee>();
		try {
	        currentSession = sessionFactory.openSession();
	        String searchCriteria = "username";
	        String hql = "From Employee as empl where empl."+searchCriteria+" like :searchField";
			Query<Employee> query = currentSession.createQuery(hql);
			query.setParameter("searchField", "%"+username+"%");
	        emplList = query.getResultList();
		}catch(Exception e) {
			LOGGER.error("Error at finding employees by user name.",e);
			e.printStackTrace();
			emplList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplList;
	}

	public Employee findEmployeeById(int employeeId) {
		Employee employee = null;
		try {
	        currentSession = sessionFactory.openSession();
	        String hql = "Select * From Employee as empl where empl.employeeId=:employeeld";
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter("employeeld", employeeId);
			employee = (Employee)query.uniqueResult();
		}catch(Exception e) {
			LOGGER.error("Error at finding employee by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return employee;
	}

	public Employee findEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		Employee employee = null;
		try {
	        currentSession = sessionFactory.openSession();
	        String hql = "Select * From Employee as empl where empl.username=:username";
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter("username", username);
			employee = (Employee)query.uniqueResult();
		}catch(Exception e) {
			LOGGER.error("Error at finding employee by user name.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return employee;
	}

	public Employee findEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		Employee employee = null;
		try {
	        currentSession = sessionFactory.openSession();
	        String hql = "Select * From Employee as empl where empl.email=:email";
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter("email", email);
			employee = (Employee)query.uniqueResult();
		}catch(Exception e) {
			LOGGER.error("Error at finding employee by Email.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return employee;
	}

	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		int id = 0;
		try {
	        currentSession = sessionFactory.openSession();
	        currentSession.beginTransaction();
	        LOGGER.debug(currentSession.isConnected());
	        System.out.println("currentSession connected:"+currentSession.isConnected());
	        currentSession.save(employee);
	        currentSession.getTransaction().commit();
			id = employee.getEmployeeId(); 
			LOGGER.info("Successfully adding an employee.");
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
		}finally {
			//if(currentSession != null)
				currentSession.close();			
		}
		return id;
	}

	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
			// Getting Session Object From SessionFactory
	        currentSession = sessionFactory.openSession();
	        // Getting Transaction Object From Session Object
	        currentSession.beginTransaction();
	        currentSession.update(employee);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully updating an employee.");
			return true;
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteEmployeeById(int employeeId) {
		try {
			// Getting Session Object From SessionFactory
	        currentSession = sessionFactory.openSession();
	        // Getting Transaction Object From Session Object
	        currentSession.beginTransaction();
	        Employee employee = findEmployeeById(employeeId);
	        if(employee !=null) {
		        currentSession.delete(employee);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting an employee.");	        	
				return true;
	        }else {
				LOGGER.info("This emplyee Id doesn't exsit.");
				return false;	        	
	        }
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		try {
			// Getting Session Object From SessionFactory
	        currentSession = sessionFactory.openSession();
	        // Getting Transaction Object From Session Object
	        currentSession.beginTransaction();
	        Employee employee = findEmployeeByUsername(username);
	        if(employee !=null) {
		        currentSession.delete(employee);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting an employee.");	        	
				return true;
	        }else {
				LOGGER.info("This username of employee doesn't exsit.");
				return false;	        	
	        }
		}catch(Exception e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting an employee, transaction is Being Rolled Back.",e);
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
