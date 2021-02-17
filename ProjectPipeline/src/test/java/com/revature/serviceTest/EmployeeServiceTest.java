/**
 * 
 */
package com.revature.serviceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Employee;
import com.revature.repository.EmployeeDAOImpl;
import com.revature.service.EmployeeService;

/**
 * @author Jinwei Xiong
 *
 */
public class EmployeeServiceTest {
	/*
	 * identified the object under test as the EmployeeService type.
	 */
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeServiceTest.class);

	/*
	 * Use the @InjectMocks annotation to inject our mocks into 
	 */
	@InjectMocks
	private static EmployeeService emplServ;
	
	/*
	 * We do NOT need an instance of EmployeeDAO; we're NOT testing this.
	 * We are mocking it!
	 * 
	 * Use Mockito to mock out the dependencies. In this case,
	 * emplDaoImpl is dependency of the EmployeeService as our EmployeeService 
	 * implementation depends heavily on the EmployeeDAOImpl implementation. 
	 */
	@Mock 
	EmployeeDAOImpl emplDaoImpl;

	@BeforeClass
	public static void setup() {
		/*
		 * This runs before an instance of our test class is created.
		 */		
		emplServ = new EmployeeService();
		LOGGER.info("Info: this is the test for Employee Service.");
	}
	
	@Before
	public void beforeSetup() {
		/*
		 * This runs once before every single @Test method.
		 * 
		 * Yes, Mockito is annotation-driven, but you still need to initialize the mocks
		 * in your setup.
		 */
		
		MockitoAnnotations.openMocks(this);
	}
	
	@Test 
	public void testfindAllEmployees() {
		/*
		 * We can use Mockito to stub the return value for the 
		 * findAllEmployees method. This means we can control the 
		 * output when this method is called And that 
		 */
		List<Employee> mock_empls = new ArrayList<Employee>(Arrays.asList(
				new Employee("m1","m1","m1","m1", "m1", 18,2),
				new Employee("e11","e11","e11","e11", "e11", 14,1),
				new Employee("e12","e12","e12","e12", "e12", 3,1))
				);
		Mockito.when(emplDaoImpl.findAllEmployees()).thenReturn(mock_empls);
		
		List<Employee> empls = emplServ.findAllEmployees();
		Assert.assertEquals("e11",empls.get(1).getUsername());
		LOGGER.info("testing for findAllEmployees()");
	}
	
	@Test
	public void testFindEmployeeById() {
		int employeeId = 2;
		Mockito.when(emplDaoImpl.findEmployeeById(employeeId)).thenReturn(
				new Employee("e11","e11","e11","e11", "e11", 14,1)
				);
		Employee empl = emplServ.findEmployeeById(employeeId);
		Assert.assertEquals("e11", empl.getUsername());
		LOGGER.info("testing for findEmployeeById()");
	}

	@Test 
	public void testFindEmployeesByUsername() {
		List<Employee> mock_empls = new ArrayList<Employee>(Arrays.asList(
				new Employee("e11","e11","e11","e11", "e11", 14,1),
				new Employee("e12","e12","e12","e12", "e12", 3,1))
				);
		
		Mockito.when(emplDaoImpl.findEmployeesByUsername("e")).thenReturn(mock_empls);
		List<Employee> empls = emplServ.findEmployeesByUsername("e");
		Assert.assertEquals("e12", empls.get(1).getUsername());
		LOGGER.info("testing for findEmployeesByUsername()");
	}
	
	@Test
	public void testFindEmployeeByUsername() {
		String username = "e11";
		Mockito.when(emplDaoImpl.findEmployeeByUsername(username)).thenReturn(
				new Employee("e11","e11","e11","e11", "e11", 14,1)
				);
		Employee empl = emplServ.findEmployeeByUsername(username);
		Assert.assertEquals("e11", empl.getUsername());		
		LOGGER.info("testing for findEmployeeByUsername()");
	}
	
	@Test
	public void testFindEmployeeByEmail() {
		String email = "e11";
		Mockito.when(emplDaoImpl.findEmployeeByEmail(email)).thenReturn(
				new Employee("e11","e11","e11","e11", "e11", 14,1)
				);
		Employee empl = emplServ.findEmployeeByUsername(email);
		Assert.assertEquals("e11", empl.getEmail());		
		LOGGER.info("testing for findEmployeeByEmail()");
	}
	
	@Test
	public void testAddEmployee() {
		Employee employee = new Employee("e11","e11","e11","e11", "e11", 14,1);
		Mockito.when(emplDaoImpl.addEmployee(employee)).thenReturn(2);
		int id = emplServ.addEmployee(employee);
		Assert.assertEquals(2, id);		
		LOGGER.info("testing for addEmployee()");
	}
		
	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee("e11","e11","e11","e11", "e11", 14,1);
		Mockito.when(emplDaoImpl.updateEmployee(employee)).thenReturn(true);
		boolean flag = emplServ.updateEmployee(employee);
		Assert.assertEquals(true, flag);		
		LOGGER.info("testing for updateEmployee()");
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
