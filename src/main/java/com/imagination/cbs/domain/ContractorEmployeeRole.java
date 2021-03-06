package com.imagination.cbs.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * The persistent class for the contractor_employee_role database table.
 * 
 */
@Entity
@Table(name="contractor_employee_role")
@NamedQuery(name="ContractorEmployeeRole.findAll", query="SELECT c FROM ContractorEmployeeRole c")
public class ContractorEmployeeRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contractor_employee_role_id")
	private long contractorEmployeeRoleId;

	@Column(name="changed_by")
	private String changedBy;

	@CreationTimestamp
	@Column(name="changed_date")
	private Timestamp changedDate;

	private String status;
	
	@Column(name="date_from")
	private Timestamp dateFrom;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="contractor_employee_id")
	private ContractorEmployee contractorEmployee;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private RoleDm roleDm;
	
	public ContractorEmployeeRole() {
	}

	public long getContractorEmployeeRoleId() {
		return this.contractorEmployeeRoleId;
	}

	public void setContractorEmployeeRoleId(long contractorEmployeeRoleId) {
		this.contractorEmployeeRoleId = contractorEmployeeRoleId;
	}

	public String getChangedBy() {
		return this.changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Timestamp getChangedDate() {
		return this.changedDate;
	}

	public void setChangedDate(Timestamp changedDate) {
		this.changedDate = changedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public ContractorEmployee getContractorEmployee() {
		return this.contractorEmployee;
	}

	public void setContractorEmployee(ContractorEmployee contractorEmployee) {
		this.contractorEmployee = contractorEmployee;
	}
	
	public RoleDm getRoleDm() {
		return this.roleDm;
	}
	
	public void setRoleDm(RoleDm roleDm) {
		this.roleDm = roleDm;
	}
	
}