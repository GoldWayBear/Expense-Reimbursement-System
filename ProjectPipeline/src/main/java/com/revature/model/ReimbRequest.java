/**
 * 
 */
package com.revature.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Jinwei Xiong
 *
 */
@Entity(name="ReimbRequest")
@Table(name="reimb_request")
public class ReimbRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7873173225410985300L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer requestId;
	@ManyToOne
	@JoinColumn(name="employeeId")
	//private Integer employeeId;
	private Employee employee;
	@ManyToOne
	@JoinColumn(name="reimbId")
	//private Integer reimbId;
	private ReimbReqDetail reimbReqDetail;
	@ManyToOne
	@JoinColumn(name="statusId")
	//private Integer statusId;
	private ReimbReqStatus reimbReqStatus;
	public ReimbRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbRequest(Employee employee, ReimbReqDetail reimbReqDetail, ReimbReqStatus reimbReqStatus) {
		super();
		this.employee = employee;
		this.reimbReqDetail = reimbReqDetail;
		this.reimbReqStatus = reimbReqStatus;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ReimbReqDetail getReimbReqDetail() {
		return reimbReqDetail;
	}
	public void setReimbReqDetail(ReimbReqDetail reimbReqDetail) {
		this.reimbReqDetail = reimbReqDetail;
	}
	public ReimbReqStatus getReimbReqStatus() {
		return reimbReqStatus;
	}
	public void setReimbReqStatus(ReimbReqStatus reimbReqStatus) {
		this.reimbReqStatus = reimbReqStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((reimbReqDetail == null) ? 0 : reimbReqDetail.hashCode());
		result = prime * result + ((reimbReqStatus == null) ? 0 : reimbReqStatus.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
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
		ReimbRequest other = (ReimbRequest) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (reimbReqDetail == null) {
			if (other.reimbReqDetail != null)
				return false;
		} else if (!reimbReqDetail.equals(other.reimbReqDetail))
			return false;
		if (reimbReqStatus == null) {
			if (other.reimbReqStatus != null)
				return false;
		} else if (!reimbReqStatus.equals(other.reimbReqStatus))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReimbRequest [requestId=" + requestId + ", employee=" + employee + ", reimbReqDetail=" + reimbReqDetail
				+ ", reimbReqStatus=" + reimbReqStatus + "]";
	}
}
