package com.imagination.cbs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private RoleDm role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contractor_id")
	private Contractor contractor;

	@Column(name = "contractor_signed_date")
	private Timestamp contractorSignedDate;

	@Column(name = "contractor_total_available_days")
	private Long contractorTotalAvailableDays;

	@Column(name = "contractor_total_working_days")
	private Long contractorTotalWorkingDays;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "currency_id")
	private CurrencyDm currency;

	@Column(name = "employer_tax_percent")
	private BigDecimal employerTaxPercent;

	@Column(name = "inside_ir35")
	private String insideIr35;

	@Column(name = "job_number")
	private String jobNumber;

	private BigDecimal rate;

	@Column(name = "revision_number")
	private Long revisionNumber;

	@Column(name = "job_dept_name")
	private String jobDeptName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supplier_type_id")
	private SupplierTypeDm supplierType;

	@Column(name = "appprover_comments")
	private String approverComments;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "commissioning_office")
	private OfficeDm commisioningOffice;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_work_location")
	private OfficeDm contractWorkLocation;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reason_for_recruiting")
	private ReasonsForRecruiting reasonForRecruiting;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_employee_id")
	private ContractorEmployee contractEmployee;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;

	@Column(name = "job_name")
	private String jobname;

	@OneToOne
	@JoinColumn(name = "approval_status_id")
	private ApprovalStatusDm approvalStatus;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@OneToMany(mappedBy = "bookingRevision", cascade = CascadeType.ALL)
	private List<ContractorMonthlyWorkDay> monthlyWorkDays;

	@OneToMany(mappedBy = "bookingRevision", cascade = CascadeType.ALL)
	private List<BookingWorkTask> bookingWorkTasks;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comm_off_region")
	private Region commOffRegion;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contractor_work_region")
	private Region contractorWorkRegion;

	@OneToMany(mappedBy = "bookingRevision", cascade = CascadeType.ALL)
	private List<ContractorWorkSite> contractorWorkSites;

	@Column(name = "completed_agreement_pdf")
	private String completedAgreementPdf;

	/**
	 * 
	 */
	public BookingRevision() {
	}

	/**
	 * clone existing object without id
	 */
	public BookingRevision(BookingRevision revision) {
		this.agreementDocumentId = revision.agreementDocumentId;
		this.agreementId = revision.agreementId;
		this.changedBy = revision.changedBy;
		this.changedDate = revision.changedDate;
		this.contractAmountAftertax = revision.contractAmountAftertax;
		this.contractAmountBeforetax = revision.contractAmountBeforetax;
		this.contractedFromDate = revision.contractedFromDate;
		this.contractedToDate = revision.contractedToDate;
		this.role = revision.role;
		this.contractor = revision.contractor;
		this.contractorSignedDate = revision.contractorSignedDate;
		this.contractorTotalAvailableDays = revision.contractorTotalAvailableDays;
		this.contractorTotalWorkingDays = revision.contractorTotalWorkingDays;
		this.currency = revision.currency;
		this.employerTaxPercent = revision.employerTaxPercent;
		this.insideIr35 = revision.insideIr35;
		this.jobNumber = revision.jobNumber;
		this.rate = revision.rate;
		this.revisionNumber = revision.revisionNumber;
		this.jobDeptName = revision.jobDeptName;
		this.supplierType = revision.supplierType;
		this.approverComments = revision.approverComments;
		this.commisioningOffice = revision.commisioningOffice;
		this.contractWorkLocation = revision.contractWorkLocation;
		this.reasonForRecruiting = revision.reasonForRecruiting;
		this.contractEmployee = revision.contractEmployee;
		this.team = revision.team;
		this.jobname = revision.jobname;
		this.approvalStatus = revision.approvalStatus;
		this.booking = revision.booking;
		this.monthlyWorkDays = revision.monthlyWorkDays;
		this.bookingWorkTasks = revision.bookingWorkTasks;
		this.commOffRegion = revision.commOffRegion;
		this.contractorWorkRegion = revision.contractorWorkRegion;
		this.contractorWorkSites = revision.contractorWorkSites;
	}

	public List<ContractorWorkSite> getContractorWorkSites() {
		return contractorWorkSites;
	}

	public void setContractorWorkSites(List<ContractorWorkSite> contractorWorkSites) {
		this.contractorWorkSites = contractorWorkSites;
	}

	public ApprovalStatusDm getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatusDm approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Region getCommOffRegion() {
		return commOffRegion;
	}

	public void setCommOffRegion(Region commOffRegion) {
		this.commOffRegion = commOffRegion;
	}

	public Region getContractorWorkRegion() {
		return contractorWorkRegion;
	}

	public void setContractorWorkRegion(Region contractorWorkRegion) {
		this.contractorWorkRegion = contractorWorkRegion;
	}

	public List<ContractorMonthlyWorkDay> getMonthlyWorkDays() {
		return monthlyWorkDays;
	}

	public void setMonthlyWorkDays(List<ContractorMonthlyWorkDay> monthlyWorkDays) {
		this.monthlyWorkDays = monthlyWorkDays;
	}

	public List<BookingWorkTask> getBookingWorkTasks() {
		return bookingWorkTasks;
	}

	public void setBookingWorkTasks(List<BookingWorkTask> bookingWorkTasks) {
		this.bookingWorkTasks = bookingWorkTasks;
	}

	public CurrencyDm getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDm currencyDm) {
		this.currency = currencyDm;
	}

	public RoleDm getRole() {
		return role;
	}

	public void setRole(RoleDm role) {
		this.role = role;
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

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
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

	public String getJobDeptName() {
		return jobDeptName;
	}

	public void setJobDeptName(String jobDeptName) {
		this.jobDeptName = jobDeptName;
	}

	public SupplierTypeDm getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(SupplierTypeDm supplierType) {
		this.supplierType = supplierType;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public OfficeDm getCommisioningOffice() {
		return commisioningOffice;
	}

	public void setCommisioningOffice(OfficeDm commisioningOffice) {
		this.commisioningOffice = commisioningOffice;
	}

	public OfficeDm getContractWorkLocation() {
		return contractWorkLocation;
	}

	public void setContractWorkLocation(OfficeDm contractWorkLocation) {
		this.contractWorkLocation = contractWorkLocation;
	}

	public ReasonsForRecruiting getReasonForRecruiting() {
		return reasonForRecruiting;
	}

	public void setReasonForRecruiting(ReasonsForRecruiting reasonForRecruiting) {
		this.reasonForRecruiting = reasonForRecruiting;
	}

	public ContractorEmployee getContractEmployee() {
		return contractEmployee;
	}

	public void setContractEmployee(ContractorEmployee contractEmployee) {
		this.contractEmployee = contractEmployee;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getCompletedAgreementPdf() {
		return completedAgreementPdf;
	}

	public void setCompletedAgreementPdf(String completedAgreementPdf) {
		this.completedAgreementPdf = completedAgreementPdf;
	}

}