/**
 * 
 */
package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

/**
 * @author Jinwei Xiong
 *
 */
public interface EmployeeDAO {
	public List<Employee> findAllEmployees();
	public List<Employee> findEmployeesByUsername(String username);
	
	public Employee findEmployeeById(int employeeId);
	public Employee findEmployeeByUsername(String username);
	public Employee findEmployeeByEmail(String email);
		
	public int addEmployee(Employee employee);
	public boolean updateEmployee(Employee employee);
	public boolean deleteEmployeeById(int employeeId);
	public boolean deleteEmployeeByUsername(String username);
}
