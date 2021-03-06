package com.imagination.cbs.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.util.CollectionUtils;

/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "booking_description")
	private String bookingDescription;

	@Column(name = "changed_by")
	private String changedBy;

	@CreationTimestamp
	@Column(name = "changed_date")
	private Timestamp changedDate;

	@OneToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToOne
	@JoinColumn(name = "status_id")
	private ApprovalStatusDm approvalStatus;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BookingRevision> bookingRevisions;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public ApprovalStatusDm getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatusDm approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Booking() {
	}

	public Long getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingDescription() {
		return this.bookingDescription;
	}

	public void setBookingDescription(String bookingDescription) {
		this.bookingDescription = bookingDescription;
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

	public List<BookingRevision> getBookingRevisions() {
		if (CollectionUtils.isEmpty(this.bookingRevisions)) {
			return this.bookingRevisions = new ArrayList<BookingRevision>();
		}
		return this.bookingRevisions;
	}

	public void setBookingRevisions(List<BookingRevision> bookingRevisions) {
		this.bookingRevisions = bookingRevisions;
	}

	public void addBookingRevision(BookingRevision bookingRevision) {
		getBookingRevisions().add(bookingRevision);
		bookingRevision.setBooking(this);
	}

	public BookingRevision removeBookingRevision(BookingRevision bookingRevision) {
		getBookingRevisions().remove(bookingRevision);
		return bookingRevision;
	}

}