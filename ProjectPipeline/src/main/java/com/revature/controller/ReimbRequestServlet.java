package com.revature.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.model.ReimbRequest;
import com.revature.service.ReimbRequestService;

/**
 * Servlet implementation class ReimbRequestServlet
 */
@MultipartConfig
public class ReimbRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReimbRequest reimbReq = new ReimbRequest();
		int employeeId = (int)request.getSession(false).getAttribute("employeeId");
		int statusId = 1; //pending for new req
		double cost = 0;
		int roleId = 1;
		System.out.println("cost :" +request.getParameter("cost"));
		try {
			cost = Double.parseDouble(request.getParameter("cost"));	
			roleId = Integer.parseInt(request.getParameter("roleid"));
		}catch(Exception e) {
			cost = 0;
		}
		reimbReq.setEmployeeId(employeeId);
		
		System.out.println("location :" +request.getParameter("location"));
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
			if(roleId == 1) {
				response.sendRedirect("/ProjectPipeline/webpages/employee/home.html");				
			}else {
				response.sendRedirect("/ProjectPipeline/webpages/manager/home.html");				
			}
		}
	}
}
