/**
 * 
 */
package com.revature.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Jinwei Xiong
 *
 */
@Entity(name="ReimbReqDetail")
@Table(name="reimb_req_detail")
public class ReimbReqDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8400158492442272444L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer reimbId;
	@Column(name="location")
	private String location;
	@Temporal(TemporalType.DATE)
	@Column(name="requestDate")
	private LocalDate requestDate;
	@Temporal(TemporalType.DATE)
	@Column(name="approvalDate")
	private LocalDate approvalDate;
	@Column(name="cost")
	private Double cost;
	@Column(name="description")
	private String description;
	@Lob
	@Column(name="receipt", columnDefinition="bytea")
	private byte[] receipt;
	
	public ReimbReqDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbReqDetail(String location, LocalDate requestDate, LocalDate approvalDate, Double cost,
			String description, byte[] receipt) {
		super();
		this.location = location;
		this.requestDate = requestDate;
		this.approvalDate = approvalDate;
		this.cost = cost;
		this.description = description;
		this.receipt = receipt;
	}

	public Integer getReimbId() {
		return reimbId;
	}

	public void setReimbId(Integer reimbId) {
		this.reimbId = reimbId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + ((reimbId == null) ? 0 : reimbId.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
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
		ReimbReqDetail other = (ReimbReqDetail) obj;
		if (approvalDate == null) {
			if (other.approvalDate != null)
				return false;
		} else if (!approvalDate.equals(other.approvalDate))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (reimbId == null) {
			if (other.reimbId != null)
				return false;
		} else if (!reimbId.equals(other.reimbId))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbReqDetail [reimbId=" + reimbId + ", location=" + location + ", requestDate=" + requestDate
				+ ", approvalDate=" + approvalDate + ", cost=" + cost + ", description=" + description + ", receipt="
				+ Arrays.toString(receipt) + "]";
	}
	

}
