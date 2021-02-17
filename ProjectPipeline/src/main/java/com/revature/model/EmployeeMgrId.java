/**
 * 
 */
package com.revature.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Jinwei Xiong
 *
 */
@Embeddable
public class EmployeeMgrId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140069880550268797L;
	/*
	@Column(name="employeeId")
	private Integer managerId;
	@Column(name="employeeId")
	private Integer employeeId;
	*/
	@ManyToOne
	@JoinColumn(name="managerId", nullable=false)
	private Employee manager;
	@ManyToOne
	@JoinColumn(name="employeeId", nullable=false)
	private Employee employee;
	
	public EmployeeMgrId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeMgrId(Employee manager, Employee employee) {
		super();
		this.manager = manager;
		this.employee = employee;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeMgrId other = (EmployeeMgrId) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeMgrId [manager=" + manager + ", employee=" + employee + "]";
	}
	
}
