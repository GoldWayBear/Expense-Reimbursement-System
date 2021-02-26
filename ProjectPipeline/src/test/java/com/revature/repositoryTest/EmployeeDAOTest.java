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
	
	//passed
	@Ignore
	@Test 
	public void testAddEmployee() {
		Employee mgr2 = new Employee();
		mgr2.setUsername("m3");
		mgr2.setPassword("m3");
		mgr2.setFirstname("m3");
		mgr2.setLastname("m3");
		mgr2.setEmail("m3@");
		mgr2.setWorkyears(21);
		mgr2.setRoleId(2);//manager
		int employeeId = emplDaoImpl.addEmployee(mgr2);
		//Assert.assertEquals(8, employeeId);
		
		
		Employee empl1 = new Employee();
		empl1.setUsername("e31");
		empl1.setPassword("e31");
		empl1.setFirstname("e31");
		empl1.setLastname("e31");
		empl1.setEmail("e31@");
		empl1.setWorkyears(15);
		empl1.setRoleId(1);//employee
		employeeId = emplDaoImpl.addEmployee(empl1);
		//Assert.assertEquals(9, employeeId);
		
		Employee empl2 = new Employee();
		empl2.setUsername("e32");
		empl2.setPassword("e32");
		empl2.setFirstname("e32");
		empl2.setLastname("e32");
		empl2.setEmail("e32@");
		empl2.setWorkyears(18);
		empl2.setRoleId(1);//employee
		employeeId = emplDaoImpl.addEmployee(empl2);
		//Assert.assertEquals(10, employeeId);
		
		LOGGER.info("testing for adding an emplyee.");
	}

	//passed
	@Ignore
	@Test
	public void testUpdateEmployee() {
		Employee mgr1 = new Employee();
		mgr1.setEmployeeId(8);
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
	
	//passed
	@Ignore
	@Test
	public void testDeleteeEmployee() {
		Assert.assertTrue(emplDaoImpl.deleteEmployeeById(7));
		LOGGER.info("testing for updating an emplyee.");
	}

	//passed
	//@Ignore
	@Test 
	public void testFindAllEmployees() {	
		List<Employee> empls = emplDaoImpl.findAllEmployees();		
		for(Employee empl:empls) {
			Assert.assertNotEquals(0, (double)empl.getEmployeeId());
			System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());
		}
		LOGGER.info("testing for finding all emplyees.");
	}
	
	//passed
	@Ignore
	@Test
	public void testFindEmployeesByUsername() {
		String username = "e";
		List<Employee> empls = emplDaoImpl.findEmployeesByUsername(username);
		for(Employee empl:empls) {
			Assert.assertNotEquals(0,(double)empl.getEmployeeId());
			System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());
		}		
		LOGGER.info("testing for findEmployeesByUsername.");		
	}
	
	//passed
	//@Ignore
	@Test 
	public void testFindEmployeeByUsername() {	
		String username = "e11";
		Employee empl = emplDaoImpl.findEmployeeByUsername(username);
		Assert.assertEquals(username, empl.getUsername());
		System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());
		LOGGER.info("testing for finding an emplyee by username.");		
	}

	//passed
	@Ignore
	@Test
	public void testFindEmployeeByEmail() {
		String email = "m1@m.com";
		Employee empl = emplDaoImpl.findEmployeeByEmail(email);
		Assert.assertNotNull(empl);
		System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());
		LOGGER.info("testing for finding an emplyee by username.");		
	}
	
	//passed
	@Ignore
	@Test
	public void testFindEmployeeById() {
		Integer employeeId = 10;
		Employee empl = emplDaoImpl.findEmployeeById(employeeId);
		Assert.assertEquals(employeeId, empl.getEmployeeId());
		System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());
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
