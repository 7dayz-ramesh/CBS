package com.imagination.cbs.service.helper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.imagination.cbs.domain.ApprovalStatusDm;
import com.imagination.cbs.domain.Approver;
import com.imagination.cbs.domain.ApproverOverrides;
import com.imagination.cbs.domain.Booking;
import com.imagination.cbs.domain.BookingRevision;
import com.imagination.cbs.domain.EmployeeMapping;
import com.imagination.cbs.domain.Team;
import com.imagination.cbs.exception.CBSApplicationException;
import com.imagination.cbs.exception.CBSUnAuthorizedException;
import com.imagination.cbs.repository.ApproverOverridesRepository;
import com.imagination.cbs.repository.ApproverRepository;
import com.imagination.cbs.security.CBSUser;

@RunWith(MockitoJUnitRunner.class)
public class BookingApproveHelperTest {

	@InjectMocks
	private BookingApproveHelper bookingApproveHelper;
	
	@Mock
	private ApproverRepository approverRepository;

	@Mock
	private ApproverOverridesRepository approverOverridesRepository;

	@Mock
	private BookingSaveHelper bookingSaveHelper;

	@Mock
	private EmailHelper emailHelper;

	private Team approverTeam;

	private Booking booking;
	private ApprovalStatusDm approvalStatus;
	private CBSUser cbsUser;
	private BookingRevision bookingRevision;
	
	@Test
	public void approve_shouldGetLatestBookingRevision_whenBookingCanBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCanBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(bookingSaveHelper).getLatestRevision(booking);
	}
	
	@Test
	public void approve_shouldGetAllApproversByTeam_whenBookingCanBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCanBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(approverRepository).findAllByTeam(approverTeam);
	}

	@Test
	public void approve_shouldFindByEmployeeIdAndJobNumber_whenBookingCanBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCanBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(approverOverridesRepository).findByEmployeeIdAndJobNumber(1002L, "100204205-02"); 
	}


	@Test
	public void approve_shouldSaveBooking_whenBookingCanBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCanBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(bookingSaveHelper).saveBooking(booking, bookingRevision,1005L, cbsUser);
	}

	@Test
	public void approve_shouldPrepareEmailAndSendTO_HR_whenBookingCanBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCanBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(emailHelper).prepareMailAndSendToHR(bookingRevision);
	}

	
	@Test
	public void approve_shouldGetLatestBookingRevision_whenBookingCannotBeOverride() {
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(bookingSaveHelper).getLatestRevision(booking);
	}

	@Test
	public void approve_shouldGetAllApproversByTeam_whenBookingCannotBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(approverRepository).findAllByTeam(approverTeam);
	}

	@Test
	public void approve_shouldFindByEmployeeIdAndJobNumber_whenBookingCannotBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(approverOverridesRepository).findByEmployeeIdAndJobNumber(1002L, "100204205-02"); 
	}

	@Test
	public void approve_shouldFindByEmployeeIdAndApproverOrder_whenBookingCannotBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(approverRepository).findByTeamAndEmployeeAndApproverOrder
		(any(Team.class),any(EmployeeMapping.class),eq(3L));
	}

	@Test
	public void approve_shouldSaveBooking_whenBookingCannotBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(bookingSaveHelper).saveBooking(booking, bookingRevision,1005L, cbsUser);
	}

	@Test
	public void approve_shouldPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride() {
		
		beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride();
		bookingApproveHelper.approve(booking, cbsUser);
		
		verify(emailHelper).prepareMailAndSendToHR(bookingRevision);
	}
 
	@Test(expected = CBSUnAuthorizedException.class)
	public void shouldThrowCBSUnAuthorizedExceptionWhenUserIsNotAuthorized() {
		
		final Booking booking = createBooking();
		final CBSUser cbsUser = createCBSUser();
		final ApprovalStatusDm approvalStatus = createApprovalStatusDm(1004L);
		final Team approverTeam = createTeam(1003l);
		final Approver approver =createApprover();
		final BookingRevision bookingRevision = createBookingRevision();
		bookingRevision.setApprovalStatus(approvalStatus);
		bookingRevision.setTeam(approverTeam); 
		final List<Approver> approverList = new ArrayList<>();
		approverList.add(approver);
		
		when(bookingSaveHelper.getLatestRevision(booking)).thenReturn(bookingRevision);
		when(approverRepository.findAllByTeam(bookingRevision.getTeam())).thenReturn(approverList); 
		when(approverOverridesRepository.findByEmployeeIdAndJobNumber(1002L,"100204205-02")).thenReturn(null);
		when(approverRepository.findByTeamAndEmployeeAndApproverOrder
				(any(Team.class),any(EmployeeMapping.class),eq(3L))).thenReturn(null); 
		
		bookingApproveHelper.approve(booking, cbsUser);
		
	}
	
	@Test(expected = CBSApplicationException.class)
	public void shouldThrowCBSApplicationExceptionWhenBookingIsNotInApprovalStatus() {
		
		final Booking booking = createBooking();
		final CBSUser cbsUser = createCBSUser();
		final ApprovalStatusDm approvalStatus = createApprovalStatusDm(1001L);
		final BookingRevision bookingRevision = createBookingRevision();
		bookingRevision.setApprovalStatus(approvalStatus);
		
		when(bookingSaveHelper.getLatestRevision(booking)).thenReturn(bookingRevision);
		
		bookingApproveHelper.approve(booking, cbsUser);
		
	}

	@Test
	public void approve_shouldGetLatestRevision_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs1() {
		
		beforeApprove(1002L, 1002L, 1L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(bookingSaveHelper).getLatestRevision(booking);
	}

	@Test
	public void approve_shouldFindAllApproversByTeam_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs1() {

		beforeApprove(1002L, 1002L, 1L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findAllByTeam(approverTeam);
	}

	@Test
	public void approve_shouldFindApproversOverrideByEmployeeIdAndJobNumber_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs1() {

		beforeApprove(1002L, 1002L, 1L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverOverridesRepository).findByEmployeeIdAndJobNumber(1002L,"100204205-02");
	}

	@Test
	public void approve_shouldFindByTeamAndEmployeeAndApproverOrder_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs1() {

		beforeApprove(1002L, 1002L, 1L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findByTeamAndEmployeeAndApproverOrder(any(Team.class),any(EmployeeMapping.class),eq(1L)); 
	}

	@Test
	public void approve_shouldGetLatestRevision_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs2Or3() {
		
		beforeApprove(1002L, 1002L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(bookingSaveHelper).getLatestRevision(booking);
	}

	@Test
	public void approve_shouldFindAllApproversByTeam_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs2Or3() {

		beforeApprove(1002L, 1002L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findAllByTeam(approverTeam);
	}

	@Test
	public void approve_shouldFindApproversOverrideByEmployeeIdAndJobNumber_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs2Or3() {

		beforeApprove(1002L, 1002L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverOverridesRepository).findByEmployeeIdAndJobNumber(1002L,"100204205-02");
	}

	@Test
	public void approve_shouldFindByTeamAndEmployeeAndApproverOrder_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval1AndApproverOrderIs2Or3() {

		beforeApprove(1002L, 1002L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findByTeamAndEmployeeAndApproverOrder(any(Team.class),any(EmployeeMapping.class),eq(1L)); 
	}

	@Test
	public void approve_shouldGetLatestRevision_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs2() {
		
		beforeApprove(1003L, 1003L, 2L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(bookingSaveHelper).getLatestRevision(booking);
	}

	@Test
	public void approve_shouldFindAllApproversByTeam_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs2() {

		beforeApprove(1003L, 1003L, 2L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findAllByTeam(approverTeam);
	}

	@Test
	public void approve_shouldFindApproversOverrideByEmployeeIdAndJobNumber_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs2() {

		beforeApprove(1003L, 1003L, 2L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverOverridesRepository).findByEmployeeIdAndJobNumber(1002L,"100204205-02");
	}

	@Test
	public void approve_shouldFindByTeamAndEmployeeAndApproverOrder_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs2() {

		beforeApprove(1003L, 1003L, 2L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findByTeamAndEmployeeAndApproverOrder(any(Team.class),any(EmployeeMapping.class),eq(2L)); 
	}

	@Test
	public void approve_shouldGetLatestRevision_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs3() {
		
		beforeApprove(1003L, 1003L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(bookingSaveHelper).getLatestRevision(booking);
	}

	@Test
	public void approve_shouldFindAllApproversByTeam_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs3() {

		beforeApprove(1003L, 1003L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findAllByTeam(approverTeam);
	}

	@Test
	public void approve_shouldFindApproversOverrideByEmployeeIdAndJobNumber_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs3() {

		beforeApprove(1003L, 1003L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverOverridesRepository).findByEmployeeIdAndJobNumber(1002L,"100204205-02");
	}

	@Test
	public void approve_shouldFindByTeamAndEmployeeAndApproverOrder_forPrepareEmailAndSentToNextApprover_whenCurrentStatusIsWaitingForApproval2AndApproverOrderIs3() {

		beforeApprove(1003L, 1003L, 3L);
		bookingApproveHelper.approve(booking, cbsUser);

		verify(approverRepository).findByTeamAndEmployeeAndApproverOrder(any(Team.class),any(EmployeeMapping.class),eq(2L)); 
	}

	private void createData() {
		booking = createBooking();
		approvalStatus = createApprovalStatusDm(1004L);
		approverTeam = createTeam(1003l);
		cbsUser = createCBSUser();
		bookingRevision = createBookingRevision();
		bookingRevision.setApprovalStatus(approvalStatus);
		bookingRevision.setTeam(approverTeam);

	}
	
	private void beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCanBeOverride() {
		createData();
		final List<Approver> approverList = new ArrayList<>();
		approverList.add(createApprover());
		
		when(bookingSaveHelper.getLatestRevision(booking)).thenReturn(bookingRevision);
		when(approverRepository.findAllByTeam(bookingRevision.getTeam())).thenReturn(approverList); 
		when(approverOverridesRepository
					.findByEmployeeIdAndJobNumber(1002L, "100204205-02")).thenReturn(createApproverOverrides());
		when(bookingSaveHelper.saveBooking(booking, bookingRevision,
				1005L, cbsUser)).thenReturn(booking);
	}

	private void beforeApproveForPrepareEmailAndSendTO_HR_whenBookingCannotBeOverride() {
		createData();
		final Approver approver =createApprover();
		final List<Approver> approverList = new ArrayList<>();
		approverList.add(approver);
		
		when(bookingSaveHelper.getLatestRevision(booking)).thenReturn(bookingRevision);
		when(approverRepository.findAllByTeam(bookingRevision.getTeam())).thenReturn(approverList); 
		when(approverOverridesRepository.findByEmployeeIdAndJobNumber(1002L,"100204205-02")).thenReturn(null);
		when(approverRepository.findByTeamAndEmployeeAndApproverOrder
				(any(Team.class),any(EmployeeMapping.class),eq(3L))).thenReturn(approver); 
		when(bookingSaveHelper.saveBooking(booking, bookingRevision,1005L, cbsUser)).thenReturn(booking);
	}
	
	private void beforeApprove(Long approvalStatusId, Long teamId, Long approverOrder) {
		booking = createBooking();
		cbsUser = createCBSUser();
		final BookingRevision bookingRevision = createBookingRevisionWithApprovalStatus(approvalStatusId);
		approverTeam = createTeam(teamId);
		bookingRevision.setTeam(approverTeam);
		Long userApproverOrder = 0L;
		if(approvalStatusId == 1002L) {
			userApproverOrder = 1L;
		}else if(approvalStatusId == 1003L) {
			userApproverOrder = 2L;
		}else if(approvalStatusId == 1004L) {
			userApproverOrder = 3L;
		}
		when(bookingSaveHelper.getLatestRevision(booking)).thenReturn(bookingRevision);
		when(approverRepository.findAllByTeam(approverTeam)).thenReturn(createApproverList(approverOrder));
		when(approverOverridesRepository.findByEmployeeIdAndJobNumber(1002L,"100204205-02")).thenReturn(null);
		when(approverRepository.findByTeamAndEmployeeAndApproverOrder(any(Team.class),any(EmployeeMapping.class),eq(userApproverOrder))).thenReturn(new Approver()); 
		
	}
	
	private ApproverOverrides createApproverOverrides() {
		ApproverOverrides approverOverrides = new ApproverOverrides();
		approverOverrides.setEmployeeId(1002L);
		approverOverrides.setJobNumber("100204205-02");
		return approverOverrides;
	}
	private Booking createBooking() {
		Booking booking = new Booking();
		booking.setApprovalStatus(createApprovalStatusDm(1004L));
		booking.setBookingDescription("Test Data");
		booking.setBookingId(1910L);
		booking.setChangedBy("nafisa.ujloomwale");
		booking.setBookingDescription("Test Data");
		return booking;
	}
	private ApprovalStatusDm createApprovalStatusDm(Long approvalStatusId) {
		ApprovalStatusDm approvalStatusDm = new ApprovalStatusDm();
		approvalStatusDm.setApprovalName("Sent To HR");
		approvalStatusDm.setApprovalStatusId(approvalStatusId);
		return approvalStatusDm;
	}
	private BookingRevision createBookingRevision() {
		BookingRevision bookingRevision = new BookingRevision();
		bookingRevision.setBookingRevisionId(4065l);
		bookingRevision.setAgreementId("C-546");
		bookingRevision.setAgreementDocumentId("CBS-111");
		bookingRevision.setChangedBy("nafisa.ujloomwale");
		bookingRevision.setJobname("RRMC Geneva AS 20 Press Conf - Production"); 
		bookingRevision.setJobNumber("100204205-02");
		bookingRevision.setRevisionNumber(4l);
		bookingRevision.setInsideIr35("true");
		return bookingRevision;
	}
	private Team createTeam(Long teamId){
		Team team = new Team();
		team.setChangedBy("david.harman");
		team.setTeamId(teamId);
		team.setTeamName("ADM");
		return team;
	}
	private CBSUser createCBSUser() {
		CBSUser cbsUser = new CBSUser("Pappu");
		cbsUser.setEmpId(1002L);
		return cbsUser;
	}
	private Approver createApprover() {
		Approver approver = new Approver();
		approver.setApproverId(1001L);
		return approver;
	}
	private BookingRevision createBookingRevisionWithApprovalStatus(Long approvalStatusId) {//, Long teamId) {
		BookingRevision bookingRevision = createBookingRevision();
		bookingRevision.setApprovalStatus(createApprovalStatusDm(approvalStatusId));
		
		return bookingRevision;
	}
	private List<Approver> createApproverList(Long approverOrder){
		List<Approver> approvers = new ArrayList<>();
		Approver approver = new Approver();
		approver.setApproverOrder(approverOrder);
		approvers.add(approver);
		return approvers;
	}

	
}
