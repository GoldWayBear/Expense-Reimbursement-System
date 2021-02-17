/**
 * 
 */
package com.revature.repositoryTest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.repository.EmployeeDAOImpl;

/**
 * @author Jinwei Xiong
 *
 */
public class EmployeeDAOTest {
	private static EmployeeDAOImpl emplDaoImpl;
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeDAOTest.class);

	@BeforeClass
	public static void setup() {
		/*
		 * This runs before an instance of our test class is created.
		 */		
		emplDaoImpl = new EmployeeDAOImpl();
		LOGGER.info("Info: This is tests for Employee DAO.");
	}
	
	@Before
	public void beforeSetup() {
		/*
		 * This runs once before every single @Test method.
		 * 
		 */
	}	
	
	@Ignore
	@Test 
	public void testAddEmployee() {
		Employee mgr1 = new Employee();
		mgr1.setUsername("m1");
		mgr1.setPassword("m1");
		mgr1.setFirstname("m1");
		mgr1.setLastname("m1");
		mgr1.setEmail("m1");
		mgr1.setWorkyears(2);
		mgr1.setRoleId(2);//manager
		int employeeId = emplDaoImpl.addEmployee(mgr1);
		Assert.assertEquals(1, employeeId);
		LOGGER.info("testing for adding a manager.");
		/*
		Employee empl1 = new Employee();
		mgr1.setUsername("e11");
		mgr1.setPassword("e11");
		mgr1.setFirstname("e11");
		mgr1.setLastname("e11");
		mgr1.setEmail("e11");
		mgr1.setWorkyears(5);
		mgr1.setRoleId(1);//employee
		employeeId = emplDaoImpl.addEmployee(empl1);
		Assert.assertEquals(2, employeeId);
		LOGGER.info("testing for adding an emplyee.");
		
		Employee empl2 = new Employee();
		mgr1.setUsername("e12");
		mgr1.setPassword("e12");
		mgr1.setFirstname("e12");
		mgr1.setLastname("e12");
		mgr1.setEmail("e12");
		mgr1.setWorkyears(15);
		mgr1.setRoleId(1);//employee
		employeeId = emplDaoImpl.addEmployee(empl2);
		Assert.assertEquals(2, employeeId);
		LOGGER.info("testing for adding an emplyee.");
		*/
	}

	@Ignore
	@Test
	public void testUpdateEmployee() {
		Employee mgr1 = new Employee();
		mgr1.setUsername("m1");
		mgr1.setPassword("m1");
		mgr1.setFirstname("m1m1");
		mgr1.setLastname("m1m1");
		mgr1.setEmail("m1@m.com");
		mgr1.setWorkyears(2);
		mgr1.setRoleId(2);//manager
		Assert.assertTrue(emplDaoImpl.updateEmployee(mgr1));		
		LOGGER.info("testing for updating an emplyee.");
	}
	
	@Ignore
	@Test 
	public void testFindAllEmployees() {	
		List<Employee> empls = emplDaoImpl.findAllEmployees();		
		for(Employee empl:empls) {
			Assert.assertNotEquals(0, (double)empl.getEmployeeId());			
		}
		LOGGER.info("testing for finding all emplyees.");
	}
	
	@Ignore
	@Test
	public void testFindEmployeesByUsername() {
		String username = "e";
		List<Employee> empls = emplDaoImpl.findEmployeesByUsername(username);
		for(Employee empl:empls) {
			Assert.assertEquals(username,empl.getUsername());
		}		
		LOGGER.info("testing for updating an emplyee.");		
	}
	
	@Ignore
	@Test 
	public void testFindEmployeeByUsername() {	
		String username = "e11";
		Employee empl = emplDaoImpl.findEmployeeByUsername(username);
		Assert.assertEquals(username, empl.getUsername());
		LOGGER.info("testing for finding an emplyee by username.");		
	}

	@Ignore
	@Test
	public void testFindEmployeeByEmail() {
		String email = "m1@m.com";
		Employee empl = emplDaoImpl.findEmployeeByEmail(email);
		Assert.assertNotNull(empl);
		LOGGER.info("testing for finding an emplyee by username.");		
	}
	
	@Ignore
	@Test
	public void testFindEmployeeById() {
		Integer employeeId = 1;
		Employee empl = emplDaoImpl.findEmployeeById(employeeId);
		Assert.assertEquals(employeeId, empl.getEmployeeId());
		LOGGER.info("testing for finding an emplyee by employee Id.");		
	}

	@After
	public void teardownAfterEachMethod() {
		/*
		 * This runs once after every single @Test method.
		 */
	}
	
	@AfterClass
	public static void teardownAfterclass() {
		/*
		 * This runs once after all other methods in this class have been
		 * run. Now is the time to close any resources that have been opened.
		 */
	}
}
