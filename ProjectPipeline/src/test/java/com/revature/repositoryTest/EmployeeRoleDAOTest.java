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
import com.revature.model.EmployeeRole;
import com.revature.repository.EmployeeRoleDAOImpl;

/**
 * @author Jinwei Xiong
 *
 */
public class EmployeeRoleDAOTest {
	private static EmployeeRoleDAOImpl emplRoleDaoImpl;
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeRoleDAOTest.class);

	@BeforeClass
	public static void setup() {
		/*
		 * This runs before an instance of our test class is created.
		 */		
		emplRoleDaoImpl = new EmployeeRoleDAOImpl();
		LOGGER.info("Info: This is tests for EmployeeRole DAO.");
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
	public void testAddEmployeeRole() {
		EmployeeRole vp = new EmployeeRole();
		vp.setRole("Director");
		vp.setRoleId(3);
		Assert.assertEquals(true, emplRoleDaoImpl.addRole(vp));

		vp.setRole("VIP");
		vp.setRoleId(4);
		Assert.assertEquals(true, emplRoleDaoImpl.addRole(vp));

		LOGGER.info("testing for adding a vp.");
	}

	@Ignore
	@Test
	public void testUpdateEmployeeRole() {
		EmployeeRole vp = new EmployeeRole();
		vp.setRole("VP");
		vp.setRoleId(4);
		Assert.assertEquals(true, emplRoleDaoImpl.updateRole(vp));
		LOGGER.info("testing for updating a role.");
	}
	
	@Ignore
	@Test 
	public void testFindAllEmployeeRoles() {	
		List<EmployeeRole> roles = emplRoleDaoImpl.findAllRoles();		
		for(EmployeeRole role:roles) {
			System.out.println(role.getRoleId()+" ,"+role.getRole());
			Assert.assertNotEquals(0, (double)role.getRoleId());			
		}
		LOGGER.info("testing for finding all emplyees.");
	}
	
	@Ignore
	@Test
	public void testFindEmployeeRoleById() {
		Integer roleId = 3;
		EmployeeRole role = emplRoleDaoImpl.findEmployeeRoleById(roleId);
		System.out.println(roleId+" : "+role.getRoleId());
		Assert.assertEquals(roleId, role.getRoleId());
		LOGGER.info("testing for finding a role by Role Id.");		
	}

	@Ignore
	@Test
	public void testRemoveEmployeeRoleById() {
		Integer roleId = 4;
		Assert.assertEquals(true, emplRoleDaoImpl.deleteRoleById(roleId));
		LOGGER.info("testing for deleting a role by Role Id.");		
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
