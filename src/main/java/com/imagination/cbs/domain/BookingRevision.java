package com.imagination.cbs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * The persistent class for the booking_revision database table.
 * 
 */
@Entity
@Table(name = "booking_revision")
public class BookingRevision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_revision_id")
	private Long bookingRevisionId;

	@Column(name = "agreement_document_id")
	private String agreementDocumentId;

	@Column(name = "agreement_id")
	private String agreementId;

	@Column(name = "approval_status_id")
	private Long approvalStatusId;

	@Column(name = "changed_by")
	private String changedBy;

	@CreationTimestamp
	@Column(name = "changed_date")
	private Timestamp changedDate;

	@Column(name = "contract_amount_aftertax")
	private BigDecimal contractAmountAftertax;

	@Column(name = "contract_amount_beforetax")
	private BigDecimal contractAmountBeforetax;

	@Column(name = "contracted_from_date")
	private Timestamp contractedFromDate;

	@Column(name = "contracted_to_date")
	private Timestamp contractedToDate;

	@Column(name = "contractor_contact_details")
	private String contractorContactDetails;

	@Column(name = "contractor_employee_name")
	private String contractorEmployeeName;

	@Column(name = "contractor_employee_role_id")
	private Long contractorEmployeeRoleId;

	@Column(name = "contractor_id")
	private Long contractorId;

	@Column(name = "contractor_name")
	private String contractorName;

	@Column(name = "contractor_signed_date")
	private Timestamp contractorSignedDate;

	@Column(name = "contractor_total_available_days")
	private Long contractorTotalAvailableDays;

	@Column(name = "contractor_total_working_days")
	private Long contractorTotalWorkingDays;

	@Column(name = "contractor_type")
	private String contractorType;

	@Column(name = "currency_id")
	private Long currencyId;

	@Column(name = "employee_contact_details")
	private String employeeContactDetails;

	@Column(name = "employer_tax_percent")
	private BigDecimal employerTaxPercent;

	@Column(name = "inside_ir35")
	private String insideIr35;

	@Column(name = "job_number")
	private String jobNumber;

	@Column(name = "known_as")
	private String knownAs;

	private BigDecimal rate;

	@Column(name = "revision_number")
	private Long revisionNumber;

	@Column(name = "job_dept_name")
	private String jobDeptName;

	@Column(name = "supplier_type_id")
	private Long supplierTypeId;

	@Column(name = "appprover_comments")
	private String approverComments;

	@Column(name = "commissioning_office")
	private Long commisioningOffice;

	@Column(name = "contract_work_location")
	private Long contractWorkLocation;

	@Column(name = "reason_for_recruiting")
	private Long reasonForRecruiting;

	@Column(name = "contract_employee_id")
	private Long contractEmployeeId;

	@Column(name = "team_id")
	private Long teamId;

	@Column(name = "job_name")
	private String jobname;

	@Column(name = "supplier_work_location_type")
	private Long supplierWorkLocationType;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public Long getSupplierWorkLocationType() {
		return supplierWorkLocationType;
	}

	public void setSupplierWorkLocationType(Long supplierWorkLocationType) {
		this.supplierWorkLocationType = supplierWorkLocationType;
	}

	public Long getSupplierTypeId() {
		return supplierTypeId;
	}

	public void setSupplierTypeId(Long supplierTypeId) {
		this.supplierTypeId = supplierTypeId;
	}

	public Long getCommisioningOffice() {
		return commisioningOffice;
	}

	public void setCommisioningOffice(Long commisioningOffice) {
		this.commisioningOffice = commisioningOffice;
	}

	public Long getContractWorkLocation() {
		return contractWorkLocation;
	}

	public void setContractWorkLocation(Long contractWorkLocation) {
		this.contractWorkLocation = contractWorkLocation;
	}

	public Long getReasonForRecruiting() {
		return reasonForRecruiting;
	}

	public void setReasonForRecruiting(Long reasonForRecruiting) {
		this.reasonForRecruiting = reasonForRecruiting;
	}

	public Long getContractEmployeeId() {
		return contractEmployeeId;
	}

	public void setContractEmployeeId(Long contractEmployeeId) {
		this.contractEmployeeId = contractEmployeeId;
	}

	public String getJobDeptName() {
		return jobDeptName;
	}

	public void setJobDeptName(String jobDeptName) {
		this.jobDeptName = jobDeptName;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public BookingRevision() {
	}

	public Long getBookingRevisionId() {
		return bookingRevisionId;
	}

	public void setBookingRevisionId(Long bookingRevisionId) {
		this.bookingRevisionId = bookingRevisionId;
	}

	public String getAgreementDocumentId() {
		return agreementDocumentId;
	}

	public void setAgreementDocumentId(String agreementDocumentId) {
		this.agreementDocumentId = agreementDocumentId;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public Long getApprovalStatusId() {
		return approvalStatusId;
	}

	public void setApprovalStatusId(Long approvalStatusId) {
		this.approvalStatusId = approvalStatusId;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Timestamp getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(Timestamp changedDate) {
		this.changedDate = changedDate;
	}

	public BigDecimal getContractAmountAftertax() {
		return contractAmountAftertax;
	}

	public void setContractAmountAftertax(BigDecimal contractAmountAftertax) {
		this.contractAmountAftertax = contractAmountAftertax;
	}

	public BigDecimal getContractAmountBeforetax() {
		return contractAmountBeforetax;
	}

	public void setContractAmountBeforetax(BigDecimal contractAmountBeforetax) {
		this.contractAmountBeforetax = contractAmountBeforetax;
	}

	public Timestamp getContractedFromDate() {
		return contractedFromDate;
	}

	public void setContractedFromDate(Timestamp contractedFromDate) {
		this.contractedFromDate = contractedFromDate;
	}

	public Timestamp getContractedToDate() {
		return contractedToDate;
	}

	public void setContractedToDate(Timestamp contractedToDate) {
		this.contractedToDate = contractedToDate;
	}

	public String getContractorContactDetails() {
		return contractorContactDetails;
	}

	public void setContractorContactDetails(String contractorContactDetails) {
		this.contractorContactDetails = contractorContactDetails;
	}

	public String getContractorEmployeeName() {
		return contractorEmployeeName;
	}

	public void setContractorEmployeeName(String contractorEmployeeName) {
		this.contractorEmployeeName = contractorEmployeeName;
	}

	public Long getContractorEmployeeRoleId() {
		return contractorEmployeeRoleId;
	}

	public void setContractorEmployeeRoleId(Long contractorEmployeeRoleId) {
		this.contractorEmployeeRoleId = contractorEmployeeRoleId;
	}

	public Long getContractorId() {
		return contractorId;
	}

	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public Timestamp getContractorSignedDate() {
		return contractorSignedDate;
	}

	public void setContractorSignedDate(Timestamp contractorSignedDate) {
		this.contractorSignedDate = contractorSignedDate;
	}

	public Long getContractorTotalAvailableDays() {
		return contractorTotalAvailableDays;
	}

	public void setContractorTotalAvailableDays(Long contractorTotalAvailableDays) {
		this.contractorTotalAvailableDays = contractorTotalAvailableDays;
	}

	public Long getContractorTotalWorkingDays() {
		return contractorTotalWorkingDays;
	}

	public void setContractorTotalWorkingDays(Long contractorTotalWorkingDays) {
		this.contractorTotalWorkingDays = contractorTotalWorkingDays;
	}

	public String getContractorType() {
		return contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getEmployeeContactDetails() {
		return employeeContactDetails;
	}

	public void setEmployeeContactDetails(String employeeContactDetails) {
		this.employeeContactDetails = employeeContactDetails;
	}

	public BigDecimal getEmployerTaxPercent() {
		return employerTaxPercent;
	}

	public void setEmployerTaxPercent(BigDecimal employerTaxPercent) {
		this.employerTaxPercent = employerTaxPercent;
	}

	public String getInsideIr35() {
		return insideIr35;
	}

	public void setInsideIr35(String insideIr35) {
		this.insideIr35 = insideIr35;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getKnownAs() {
		return knownAs;
	}

	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(Long revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}