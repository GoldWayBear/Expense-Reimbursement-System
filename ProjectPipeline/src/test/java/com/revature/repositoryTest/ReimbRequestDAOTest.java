/**
 * 
 */
package com.revature.repositoryTest;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

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
import com.revature.model.ReimbRequest;
import com.revature.repository.ReimbRequestDAO;
import com.revature.repository.ReimbRequestDAOImpl;

/**
 * @author Jinwei Xiong
 *
 */
public class ReimbRequestDAOTest {
	private static ReimbRequestDAO reimbReqDAO;
	private static final Logger LOGGER = LogManager.getFormatterLogger(ReimbRequestDAOTest.class);

	@BeforeClass
	public static void setup() {
		/*
		 * This runs before an instance of our test class is created.
		 */		
		reimbReqDAO = new ReimbRequestDAOImpl();
		LOGGER.info("Info: This is tests for ReimbRequestDAO.");
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
	public void testAddReimbRequest() {
		int emplId =8;
		int statusId =1;//pending
		ReimbRequest reimbRequest = new ReimbRequest();
				
		reimbRequest.setEmployeeId(emplId);
		reimbRequest.setLocation("NJ");
		reimbRequest.setRequestDate(Date.valueOf(LocalDate.now()));
		//reimbRequest.setApprovalDate(approvalDate);
		reimbRequest.setCost(2575.36);
		reimbRequest.setDescription("For tuition of college");
		//reimbRequest.setReceipt(receipt);
		reimbRequest.setStatusId(statusId);
		int reqId = reimbReqDAO.addReimbRequest(reimbRequest);
		//Assert.assertEquals(17, reqId);
		
		
		/*
		int emplId =9;
		int statusId =1;//pending
		ReimbRequest reimbRequest = new ReimbRequest();
				
		reimbRequest.setEmployeeId(emplId);
		reimbRequest.setLocation("NJ");
		reimbRequest.setRequestDate(Date.valueOf(LocalDate.now()));
		//reimbRequest.setApprovalDate(approvalDate);
		reimbRequest.setCost(312.38);
		reimbRequest.setDescription("For Exam");
		//reimbRequest.setReceipt(receipt);
		reimbRequest.setStatusId(statusId);
		int reqId = reimbReqDAO.addReimbRequest(reimbRequest);
		Assert.assertEquals(14, reqId);
		*/
	}

	//passed
	@Ignore
	@Test
	public void testUpdateEmployee() {
		int reqId = 13;
		int emplId =10;
		int statusId =1;//pending
		
		ReimbRequest reimbRequest = new ReimbRequest();
				
		reimbRequest.setReimbReqId(reqId);
		reimbRequest.setEmployeeId(emplId);
		reimbRequest.setLocation("NY");
		reimbRequest.setRequestDate(Date.valueOf(LocalDate.now()));
		//reimbRequest.setApprovalDate(approvalDate);
		reimbRequest.setCost(492.38);
		reimbRequest.setDescription("For tuition and exam");
		//reimbRequest.setReceipt(receipt);
		reimbRequest.setStatusId(statusId);
		
		
		boolean result = reimbReqDAO.updateReimbRequest(reimbRequest);
		Assert.assertEquals(true, result);
	}
	
	//passed
	@Ignore
	@Test 
	public void testDeleteReimbRequestByRequestId() {	
		boolean result = reimbReqDAO.deleteReimbRequestByRequestId(17);
		Assert.assertEquals(true, result);
	}
	
	//passed
	@Ignore
	@Test
	public void testFindAllReimbRequests() {
		List<ReimbRequest> reqs = reimbReqDAO.findAllReimbRequests();
		for(ReimbRequest req:reqs) {
			System.out.println(req.getReimbReqId()+" "+req.getLocation()+" "+req.getCost()+" "+req.getDescription());
			Assert.assertNotEquals(0, (double)req.getReimbReqId());
		}		
	}
	
	@Ignore
	@Test 
	public void testFindReimbRequestsByManagerId() {	
		List<ReimbRequest> reqs = reimbReqDAO.findReimbRequestsByManagerId(8);
		for(ReimbRequest req:reqs) {
			System.out.println(req);
			Assert.assertNotEquals(0, (double)req.getReimbReqId());
		}		
	}

	@Ignore
	@Test
	public void testFindReimbRequestsByEmployeeId() {
		List<ReimbRequest> reqs = reimbReqDAO.findReimbRequestsByEmployeeId(1);
		for(ReimbRequest req:reqs) {
			Assert.assertNotEquals(0, (double)req.getReimbReqId());
		}		
	}
	
	@Ignore
	@Test
	public void testFindReimbRequestsByStatusId() {
		List<ReimbRequest> reqs = reimbReqDAO.findReimbRequestsByStatusId(1);
		for(ReimbRequest req:reqs) {
			Assert.assertTrue(req.getStatusId()==1);
		}		
	}

	@Ignore
	@Test
	public void testFindReimbRequestByRequestId() {
		ReimbRequest req = reimbReqDAO.findReimbRequestByRequestId(1);
		Assert.assertTrue(req.getReimbReqId() == 1);
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
