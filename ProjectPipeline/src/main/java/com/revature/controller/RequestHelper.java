/**
 * 
 */
package com.revature.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.revature.model.Employee;
import com.revature.model.ReimbRequest;
import com.revature.service.EmployeeMgrService;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbRequestService;

/**
 * @author Jinwei Xiong
 *
 */

public class RequestHelper {
	private static List<ReimbRequest> reimbReqs = null;
	private static ReimbRequest reimbReq = null;
	private static Employee employee = null;
	private static List emplMgrList = null;
	
	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
//		List<ReimbRequest> reimbReqs = null;
		final String URI = request.getRequestURI();
		System.out.println(URI);
		final String RESOURCE = URI.replace("/ProjectPipeline/app", "");
		System.out.println("resource in get :"+RESOURCE);

		switch (RESOURCE) {
		//The followings are about emplyee 		
		case "/employee/home":
			return doEmployeeHome(request, response);
		case "/employee/reimbRequest":
			System.out.println("Request from /employee/reimbRequest");
			reimbReqs = (List<ReimbRequest>) doEmployeeReimbRequest(request, response);
		case "/employee/approvedReq":
			System.out.println("Request from /employee/approvedReq");
			return reimbReqs;
		case "/employee/pendingReq":
			System.out.println("Request from /employee/pendingReq");
			return reimbReqs;
		case "/employee/deniedReq":
			System.out.println("Request from /employee/deniedReq");
			return reimbReqs;
		case "/employee/ReimbRequestDetail":
			System.out.println("Request from /employee/ReimbRequestDetail");
			reimbReq = (ReimbRequest)doReimbRequestDetail(request, response);
	    	response.sendRedirect("/ProjectPipeline/webpages/employee/RequestDetail.html");
		case "/employee/ReimbRequestDetailShow":
			return reimbReq;
		case "/employee/profile":
			System.out.println("Request from /employee/profile");
			employee = (Employee)doProfile(request, response);
	    	response.sendRedirect("/ProjectPipeline/webpages/employee/profile.html");				
		case "/employee/profileShow":
			System.out.println("In profile show :"+employee);
			return employee;
		//The followings are about manager 	
		case "/manager/home":
			response.setStatus(200);
			return doManagerHome(request, response);
		case "/manager/employeeInfo":
			employee = (Employee)doManagerEmplInfo(request, response);
		case "/manager/employeeInfoShow":
			return employee;
		case "/manager/ReimbRequestDetail":
			reimbReq = (ReimbRequest)doReimbRequestDetail(request, response);
	    	response.sendRedirect("/ProjectPipeline/webpages/manager/RequestDetail.html");
		case "/manager/ReimbRequestDetailShow":
			return reimbReq;
		case "/manager/reimbRequest":
			reimbReqs = (List<ReimbRequest>) doManagerReimbRequest(request, response);
		case "/manager/approvedReq":
			return reimbReqs;
		case "/manager/pendingReq":
			return reimbReqs;
		case "/manager/deniedReq":
			return reimbReqs;
		case "/manager/profile":
			employee = (Employee)doProfile(request, response);
	    	response.sendRedirect("/ProjectPipeline/webpages/manager/profile.html");				
		case "/manager/profileShow":
			return employee;
		case "/manager/employeeManagement":
			emplMgrList = doEmployeeManagement(request, response);
	    	response.sendRedirect("/ProjectPipeline/webpages/manager/employeeManagement.html");			
		case "/manager/employeeManagementShow":
			return emplMgrList;
		//The following is to logout 
		case "/logout":
			//doEmail(request,response);
			System.out.println("logout from anywhere.");			
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("/ProjectPipeline/index.html");
			return "Your session has been invalidated.";
		default:
			response.setStatus(404);
			return "Sorry. The resource you have requested does not exist.";
		}

	}

	private static Object doEmployeeReimbRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
		    int id = 0;	    
		    int optionid = 1;
		    try {
		    	id = Integer.parseInt(request.getParameter("id"));
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	id = 0;
		    }
		    
		    try {
		    	optionid = 	Integer.parseInt(request.getParameter("optionid"));
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	optionid = 0;
		    }

			int emplId = 0;
			try {
				//emplId = Integer.parseInt((String) request.getSession().getAttribute("employeeId"));
				emplId = (int)request.getSession(false).getAttribute("employeeId");				
				
			}catch(NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
			
	    	System.out.println("employee ReimbRequest: optionid = "+optionid+" , "+" id = "+id);
	    	
			//List<ReimbRequest> reimbReqs = null;
		    try {
				response.setStatus(200);
			    switch(optionid) {
			    case 1://all
					//reimbReqs = new ReimbRequestService().findReimbRequestsByEmployeeId(emplId);			               
			    	response.sendRedirect("/ProjectPipeline/webpages/employee/home.html");
			    	break;
			    case 2://by pending
					reimbReqs = new ReimbRequestService().findReimbRequestsByEmplStatusId(emplId, 1);		               
			    	response.sendRedirect("/ProjectPipeline/webpages/employee/pendingReq.html");
			    	break;
			    case 3://by approved
			    	//response.sendRedirect("/ProjectPipeline/webpages/employee/home.html");
		            //RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectPipeline/webpages/employee/home.html");
					//dispatcher.forward(request, response);	
					reimbReqs = new ReimbRequestService().findReimbRequestsByEmplStatusId(emplId, 2);
//					for(ReimbRequest req:reimbReqs)
//						System.out.println("Approved Request: "+req);
			    	response.sendRedirect("/ProjectPipeline/webpages/employee/approvedReq.html");
			    	break;
			    case 4://by denied
					reimbReqs = new ReimbRequestService().findReimbRequestsByEmplStatusId(emplId, 3);		               
			    	response.sendRedirect("/ProjectPipeline/webpages/employee/deniedReq.html");
			    	break;
			    default:
			    	break;
			    }	    	

			    //set image field to null to fast data transferring
				for(ReimbRequest req:reimbReqs) {
					req.setReceipt(null);
				}
				return reimbReqs;
		    }catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
		}else {
			return null;
		}	
	}

	private static Object doEmployeeHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
			int emplId = 0;
			try {
				emplId = (int)request.getSession(false).getAttribute("employeeId");				
				response.setStatus(200);
				return 	new ReimbRequestService().findReimbRequestsByEmployeeId(emplId);			               				
			}catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
		}else {
			return null;
		}	
	}

	private static Object doReimbRequestDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			int reimbReqId = 0;
			try {
				reimbReqId = Integer.parseInt(request.getParameter("reimbReqId"));				
				reimbReq =	new ReimbRequestService().findReimbRequestByRequestId(reimbReqId);
				System.out.println("In request Detail: "+reimbReq);
				//String url = "/ProjectPipeline/webpages/"+base+"/RequestDetail.html";
		    	//response.sendRedirect("/ProjectPipeline/webpages/employee/RequestDetail.html");
			}catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
			return reimbReq;
	}

	
	private static Object doProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
			int emplId = 0;
			try {
				emplId = (int)request.getSession(false).getAttribute("employeeId");				
				//response.setStatus(200);
				employee= new EmployeeService().findEmployeeById(emplId);			               				
			}catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
			return employee;
		}else {
			return null;
		}	
	}
	
	private static Object doManagerHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
			int managerId = 0;
			try {
				managerId = (int)request.getSession(false).getAttribute("employeeId");				
				response.setStatus(200);
				return 	new ReimbRequestService().findReimbRequestsByManagerId(managerId);			               				
			}catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
		}else {
			return null;
		}	
	}
	private static Object doManagerEmplInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
			int emplId = 0;
			try {				 
				emplId = Integer.parseInt(request.getParameter("employeeId"));
				employee= new EmployeeService().findEmployeeById(emplId);			               				
		    	response.sendRedirect("/ProjectPipeline/webpages/manager/employeeInfo.html");				
			}catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
			return employee;
		}else {
			return null;
		}	
	}
	
	private static Object doManagerReimbRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
		    int optionid = 1;
		    try {
		    	optionid = 	Integer.parseInt(request.getParameter("optionid"));
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	optionid = 0;
		    }

			int managerId = 0;
			try {
				managerId = (int)request.getSession(false).getAttribute("employeeId");			
			}catch(NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
			
		    try {
				response.setStatus(200);
			    switch(optionid) {
			    case 1://all
					//reimbReqs = new ReimbRequestService().findReimbRequestsByManagerId(managerId);			               
			    	response.sendRedirect("/ProjectPipeline/webpages/manager/home.html");
			    	break;
			    case 2://by pending
					reimbReqs = new ReimbRequestService().findReimbRequestsByManagerAndStatusId(managerId,1);              
			    	response.sendRedirect("/ProjectPipeline/webpages/manager/pendingReq.html");
			    	break;
			    case 3://by approved
					reimbReqs = new ReimbRequestService().findReimbRequestsByManagerAndStatusId(managerId, 2);
			    	response.sendRedirect("/ProjectPipeline/webpages/manager/approvedReq.html");
			    	break;
			    case 4://by denied
					reimbReqs = new ReimbRequestService().findReimbRequestsByManagerAndStatusId(managerId, 3);		               
			    	response.sendRedirect("/ProjectPipeline/webpages/manager/deniedReq.html");
			    	break;
			    default:
			    	break;
			    }	    	
			    //set image field to null to fast data transferring
				for(ReimbRequest req:reimbReqs) {
					req.setReceipt(null);
				}
				return reimbReqs;
		    }catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
		}else {
			return null;
		}	
	}

	private static List doEmployeeManagement(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {
			List emplMgrs = null;
			EmployeeService emplServ = new EmployeeService();
		    int optionid = 1;
		    try {
		    	optionid = 	Integer.parseInt(request.getParameter("optionid"));
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	optionid = 1;
		    }
		    int emplId = 0;
		    try {
		    	emplId = Integer.parseInt(request.getParameter("employeeId"));
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	emplId = 0;
		    }
		    
		    try {
				response.setStatus(200);
			    switch(optionid) {
			    case 1://all
			    	emplMgrs = emplServ.findAllEmployeeWithMgrId();
			    	break;
			    case 2://by employee
			    	emplMgrs = emplServ.findEmployeesWithMgrByEmplRoleId(emplId, 1);
			    	break;
			    case 3://by manager
			    	emplMgrs = emplServ.findEmployeesWithMgrByEmplRoleId(emplId, 2);
			    	break;
			    default:
			    	break;
			    }	    	
				return emplMgrs;
		    }catch(Exception e) {
		    	e.printStackTrace();
				return null;
		    }
		}else {
			return null;
		}	
	}
	
	//The followings are for METHOD POST
	public static void processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/ProjectPipeline/app", "");
		System.out.println("resource in post :"+RESOURCE);
		switch (RESOURCE) {
		case "/login":
			doLogin(request,response);
			break;
		case "/employee/newRequest":
			System.out.println("Request from /employee/newRequest");
			//doNewRequest(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/ReimbRequestServlet");
			dispatcher.forward(request, response);		            
			break;
		case "/employee/profileUpdate":
			System.out.println("In profileUpdate");
			doProfileUpdate(request, response, 1);//employee
	    	response.sendRedirect("/ProjectPipeline/webpages/employee/profile.html");			
			break;
			//manager/register
		case "/manager/register":
			doRegister(request, response);
			break;
		case "/manager/optionOnRequest":
			doOptionOnRequest(request, response); 
			break;
		case "/manager/profileUpdate":
			doProfileUpdate(request, response,2);//manager
	    	response.sendRedirect("/ProjectPipeline/webpages/manager/profile.html");							    	
			break;
		default:
			response.setStatus(404);
		}
	}
		
	public static void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
	
	    EmployeeService emplServ = new EmployeeService();
	    Employee empl = emplServ.login(username, password);
	    	
	    if(empl != null) {
	    	int roleId = empl.getRoleId();
	    	switch(roleId) {
	    	case 1: //Employee	 	
	            request.getSession().setAttribute("employee", username);
	            request.getSession().setAttribute("employeeId", empl.getEmployeeId());		            
	            //RequestDispatcher dispatcher = request.getRequestDispatcher("app/employee/home");
				//dispatcher.forward(request, response);		            
				response.sendRedirect("/ProjectPipeline/webpages/employee/home.html");
	            break;
	    	case 2: //manager
	            request.getSession().setAttribute("manager", username);
	            request.getSession().setAttribute("employeeId", empl.getEmployeeId());		            
	            request.getSession().setAttribute("email", empl.getEmail());
				response.sendRedirect("/ProjectPipeline/webpages/manager/home.html");
	    		break;
			default:
				response.setStatus(404);
				break;
	    	}		    	
	    }else {
	    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	    	
			response.sendRedirect("/ProjectPipeline/index.html");
	    }			
	    				
	}
	
	public static void doNewRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			ReimbRequest reimbReq = new ReimbRequest();
			int employeeId = (int)request.getSession(false).getAttribute("employeeId");
			int statusId = 1; //pending for new req
			System.out.println("In doNewRequest cots: " +request.getParameter("cost"));
			double cost = Double.parseDouble(request.getParameter("cost"));
			reimbReq.setEmployeeId(employeeId);
			reimbReq.setLocation(request.getParameter("location"));
			
			reimbReq.setRequestDate(Date.valueOf(LocalDate.now()));
			reimbReq.setCost(cost);
			reimbReq.setDescription(request.getParameter("description"));		
			reimbReq.setStatusId(statusId);
			
			// Uploading a file requires the data to be sent in "parts", because
			// one HTTP packet might not be big
			// enough anymore for all of the data. Here we get the part that has
			// the file data
			Part receipt = request.getPart("receipt");

			InputStream is = null;
			ByteArrayOutputStream os = null;

			try {
				is = receipt.getInputStream();
				os = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];

				while (is.read(buffer) != -1) {
					os.write(buffer);
				}
				
				reimbReq.setReceipt(os.toByteArray());

			} catch (IOException e) {
				System.out.println("Could not upload file!");
				e.printStackTrace();
			} finally {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			}

			int reqId = new ReimbRequestService().addReimbRequest(reimbReq);
			
			if(reqId>0){
				response.sendRedirect("/ProjectPipeline/webpages/employee/home.html");
			}
	}
	
	private static void doProfileUpdate(HttpServletRequest request, HttpServletResponse response,int roleId)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {			
			try {
				Employee empl = new Employee();
				int employeeId = (int)request.getSession(false).getAttribute("employeeId");
				empl.setEmployeeId(employeeId);
				empl.setUsername(request.getParameter("username"));
				empl.setPassword(request.getParameter("password"));
				empl.setFirstname(request.getParameter("firstname"));
				empl.setLastname(request.getParameter("lastname"));
				empl.setEmail(request.getParameter("email"));
				//int roleId = 1;//employee
				empl.setRoleId(roleId);
				int workyears =0;
				workyears = Integer.parseInt(request.getParameter("workyears"));
				empl.setWorkyears(workyears);
			
				boolean flag = new EmployeeService().updateEmployee(empl);
				if(flag) {
					employee = empl;
				}
			}catch(Exception e) {
		    	e.printStackTrace();
		    }
		}	
	}

	private static void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {	
			try {
				int mgrId = (int)request.getSession().getAttribute("employeeId");
				Employee empl = new Employee();
				empl.setUsername(request.getParameter("username"));
				empl.setPassword(request.getParameter("password"));
				empl.setFirstname(request.getParameter("firstname"));
				empl.setLastname(request.getParameter("lastname"));
				empl.setEmail(request.getParameter("email"));
				int roleId = 1;//employee
				empl.setRoleId(roleId);
				int workyears =0;
				workyears = Integer.parseInt(request.getParameter("workyears"));
				empl.setWorkyears(workyears);
			
				//System.out.println("In do Register: "+request.getParameter("username")+" password:"+request.getParameter("password"));
				int emplId = new EmployeeService().addEmployee(empl);
				if(emplId>0) {
					if(new EmployeeMgrService().addEmployeeMgr(mgrId, emplId)) {
				    	//response.sendRedirect("/ProjectPipeline/webpages/manager/register.html");	
						String url = "/ProjectPipeline/app/manager/employeeInfo?employeeId="+emplId;
						System.out.println(url);
						//request.getRequestDispatcher(url).forward(request, response);
						response.sendRedirect(url);
						doEmail(request, response);
					}
				}else {
			    	response.sendRedirect("/ProjectPipeline/webpages/manager/register.html");						
				}
			}catch(Exception e) {
		    	e.printStackTrace();
		    }
		}	
		
	}

	private static void doEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			String username = System.getenv("GMAILUSER");
			String password = System.getenv("GMAILPWD");
			//System.out.println(username);
			//System.out.println(password);
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(username, password));
			email.setSSLOnConnect(true);
			email.setFrom(username);
			email.setSubject("Welcome to team");
			String msg = "This is a welcome letter from our management.\n";
			msg += "Your email : "+ "username@globalmail.com\n";
			msg += "Your temporal password: 12345_6_7_8\n";
			msg += "This is a test mail ... :-)";
			email.setMsg(msg);
			String toEmail = request.getParameter("email");
			email.addTo(toEmail);
			email.send();
		}catch(EmailException e) {
			e.printStackTrace();
		}
	}
	
	private static void doOptionOnRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(request.getSession().getAttribute("employeeId") != null) {			
			try {
				//System.out.println("In option On Request:"+ request.getParameter("reqid")+" , optionId="+request.getParameter("optionid"));
				int mgrId = (int)request.getSession().getAttribute("employeeId");
				int reimbReqId= Integer.parseInt(request.getParameter("reqid"));
				int option = Integer.parseInt(request.getParameter("optionid"));
				
				//System.out.println("In option On Request:"+ "reqId="+reimbReqId+" , optionId="+option);
				
				ReimbRequestService reqServ = new ReimbRequestService();
				ReimbRequest req = reqServ.findReimbRequestByRequestId(reimbReqId);
				
				if(mgrId == reimbReq.getEmployeeId()) {
					//managers can't do option on themselves.
					reimbReq = null;
					PrintWriter out = response.getWriter();
					out.println("<html><body>");
					out.println("<h3>managers can't do option on themselves</h3>");
					out.println("</body></html>");
					//response.sendRedirect("/ProjectPipeline/webpages/manager/RequestDetail.html");							    												
				}else{
					if(option == 2) {
						req.setStatusId(2);//approval
						req.setApprovalDate(Date.valueOf(LocalDate.now()));
					}else {
						req.setStatusId(3);//denied					
						req.setApprovalDate(Date.valueOf(LocalDate.now()));
					}
					
					if(reqServ.updateReimbRequest(req)) {
						reimbReq = req;
				    	response.sendRedirect("/ProjectPipeline/webpages/manager/RequestDetail.html");							    												
					}				
				}				
			}catch(Exception e) {
		    	e.printStackTrace();
				reimbReq = null;
				//response.setStatus(404);
		    	response.sendRedirect("/ProjectPipeline/webpages/manager/home.html");							    												
		    }
		}	
	}
	
}
