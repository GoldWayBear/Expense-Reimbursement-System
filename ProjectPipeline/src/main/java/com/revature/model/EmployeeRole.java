/**
 * 
 */
package com.revature.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Jinwei Xiong
 *
 */
@Entity(name="EmployeeRole")
@Table(name="empl_role")
public class EmployeeRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	//@Column(name="roleId")
	private Integer roleId;
	@Column(name="role")
	private String role;
	
	//one to many is not a must here.
	/*
	@OneToMany(mappedBy="role")
	Set<Employee> employees = new HashSet<>();
	*/
	public EmployeeRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeRole(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	/*
	public EmployeeRole(Integer roleId, String role, Set<Employee> employees) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.employees = employees;
	}
	*/
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	/*
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		EmployeeRole other = (EmployeeRole) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeRole [roleId=" + roleId + ", role=" + role + "]";
	}

}
