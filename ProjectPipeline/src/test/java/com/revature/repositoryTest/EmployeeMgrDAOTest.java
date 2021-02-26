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
import com.revature.repository.EmployeeMgrDAO;
import com.revature.repository.EmployeeMgrDAOImpl;

/**
 * @author Jinwei Xiong
 *
 */
public class EmployeeMgrDAOTest {
	private static EmployeeMgrDAO emplMgrDao;
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeMgrDAOTest.class);

	@BeforeClass
	public static void setup() {
		/*
		 * This runs before an instance of our test class is created.
		 */		
		emplMgrDao = new EmployeeMgrDAOImpl();
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
	//@Ignore
	@Test 
	public void testAddEmployeeMgr() {
//		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(8, 9));
//		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(8, 10));
//		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(21, 22));
//		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(21, 23));
//		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(24, 25));
//		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(24, 26));
		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(1000, 1001));
		Assert.assertEquals(true, emplMgrDao.addEmployeeMgr(1000, 1002));
	}

	//passed
	//@Ignore
	@Test
	public void testDeleteEmployeeMgr() {
		Assert.assertTrue(emplMgrDao.deleteEmployeeMgr(1000, 1001));
		Assert.assertTrue(emplMgrDao.deleteEmployeeMgr(1000, 1002));		
	}
	
	//passed
	//@Ignore
	@Test 
	public void testFindManagersByEmplId() {	
		int emplId =10;
		List<Employee> mgrs = emplMgrDao.findManagersByEmplId(emplId);		
		for(Employee empl:mgrs) {
			Assert.assertNotEquals(0, (double)empl.getEmployeeId());	
			System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());			
		}
	}
	
	//passed
	//@Ignore
	@Test
	public void testFindEmployeesByMgrId() {
		int mgrId =8;
		List<Employee> empls = emplMgrDao.findEmployeesByMgrId(mgrId);
		for(Employee empl:empls) {
			Assert.assertNotEquals(0,(double)empl.getEmployeeId());
			System.out.println(empl.getEmployeeId()+" "+empl.getUsername()+" "+empl.getEmail());			
		}		
	}
	
}
