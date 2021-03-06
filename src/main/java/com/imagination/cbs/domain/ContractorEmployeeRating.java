package com.imagination.cbs.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the contractor_employee_ratings database table.
 * 
 */
@Entity
@Table(name="contractor_employee_ratings")
@NamedQuery(name="ContractorEmployeeRating.findAll", query="SELECT c FROM ContractorEmployeeRating c")
public class ContractorEmployeeRating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contractor_employee_ratings_id")
	private long contractorEmployeeRatingsId;

	private String description;

	@Column(name="rating_given")
	private BigDecimal ratingGiven;

	@Column(name="rating_given_by")
	private String ratingGivenBy;

	@Column(name="rating_given_date")
	private Timestamp ratingGivenDate;

	private String status;

	@OneToOne
	@JoinColumn(name="contractor_employee_id")
	private ContractorEmployee contractorEmployee;

	@OneToOne
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	
	public ContractorEmployeeRating() {
	}

	public long getContractorEmployeeRatingsId() {
		return this.contractorEmployeeRatingsId;
	}

	public void setContractorEmployeeRatingsId(long contractorEmployeeRatingsId) {
		this.contractorEmployeeRatingsId = contractorEmployeeRatingsId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRatingGiven() {
		return this.ratingGiven;
	}

	public void setRatingGiven(BigDecimal ratingGiven) {
		this.ratingGiven = ratingGiven;
	}

	public String getRatingGivenBy() {
		return this.ratingGivenBy;
	}

	public void setRatingGivenBy(String ratingGivenBy) {
		this.ratingGivenBy = ratingGivenBy;
	}

	public Timestamp getRatingGivenDate() {
		return this.ratingGivenDate;
	}

	public void setRatingGivenDate(Timestamp ratingGivenDate) {
		this.ratingGivenDate = ratingGivenDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ContractorEmployee getContractorEmployee() {
		return this.contractorEmployee;
	}

	public void setContractorEmployee(ContractorEmployee contractorEmployee) {
		this.contractorEmployee = contractorEmployee;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}