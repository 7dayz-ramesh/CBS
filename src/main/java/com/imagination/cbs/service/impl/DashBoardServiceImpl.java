package com.imagination.cbs.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imagination.cbs.dto.DashBoardBookingDto;
import com.imagination.cbs.repository.BookingRevisionRepository;
import com.imagination.cbs.service.DashBoardService;
import com.imagination.cbs.service.LoggedInUserService;
import com.imagination.cbs.util.CBSDateUtils;

/**
 * @author Pappu Rout
 *
 */


@Service("dashBoardService")
public class DashBoardServiceImpl implements DashBoardService{
	
	@Autowired
	private BookingRevisionRepository bookingRevisionRepository;
	
	@Autowired
	private LoggedInUserService loggedInUserService;
	
	@Override
	public Page<DashBoardBookingDto> getDashBoardBookingsStatusDetails(String status, int pageNo, int pageSize) {

		String loggedInUser = loggedInUserService.getLoggedInUserDetails().getDisplayName();
		Long employeeId = loggedInUserService.getLoggedInUserDetails().getEmpId();
		
		List<Tuple> bookingRevisions = null;

		if (status.equalsIgnoreCase("Submitted")) {
			bookingRevisions = bookingRevisionRepository.retrieveBookingRevisionForSubmitted(loggedInUser, PageRequest.of(pageNo, pageSize));
		} else if(status.equalsIgnoreCase("waiting")){
			return retrieveBookingRevisionForWaitingForApproval(employeeId, PageRequest.of(pageNo, pageSize));
		} else {
			bookingRevisions = bookingRevisionRepository.retrieveBookingRevisionForDraftOrCancelled(loggedInUser, status, PageRequest.of(pageNo, pageSize));
		}
        List<DashBoardBookingDto> bookingDashBoardDtos = toPagedBookingDashBoardDtoFromTuple(bookingRevisions, false);

		return new PageImpl<>(bookingDashBoardDtos, PageRequest.of(pageNo, pageSize), bookingDashBoardDtos.size());
	}
	
	
	private Page<DashBoardBookingDto> retrieveBookingRevisionForWaitingForApproval(Long employeeId,  Pageable pageable){
		
		List<Tuple> retrieveBookingRevisionForWaitingByJobName = bookingRevisionRepository.retrieveBookingRevisionForWaitingForApprovalByJobNumber(employeeId, pageable);
		
        List<DashBoardBookingDto> bookingDashboradDtosList = toPagedBookingDashBoardDtoFromTuple(retrieveBookingRevisionForWaitingByJobName, true);
		
		List<Tuple> retrieveBookingRevisionForWaitingByEmployeeId = bookingRevisionRepository.retrieveBookingRevisionForWaitingForApprovalByEmployeeId(employeeId, pageable);
		
		List<Tuple> retrieveBookingRevisionForWaitingByHR = bookingRevisionRepository.retrieveBookingRevisionForWaitingForHRApproval(employeeId, pageable);
		
		bookingDashboradDtosList.addAll(toPagedBookingDashBoardDtoFromTuple(retrieveBookingRevisionForWaitingByEmployeeId, true));
		bookingDashboradDtosList.addAll(toPagedBookingDashBoardDtoFromTuple(retrieveBookingRevisionForWaitingByHR, true));
		
		return new PageImpl<>(bookingDashboradDtosList.stream().distinct().collect(Collectors.toList()), pageable, bookingDashboradDtosList.size());
		
	}
	
	private List<DashBoardBookingDto> toPagedBookingDashBoardDtoFromTuple(List<Tuple> bookingRevisions, Boolean isWaitingStatus) {
        
        return bookingRevisions.stream().filter(bookingRevision-> Objects.nonNull((bookingRevision.get("bookingId",BigInteger.class))))
            .map(bookingRevision -> {
            
            	DashBoardBookingDto bookingDashBoardDto = new DashBoardBookingDto();

            bookingDashBoardDto.setStatus(bookingRevision.get("status", String.class));
            bookingDashBoardDto.setJobName(bookingRevision.get("jobName", String.class));
            bookingDashBoardDto.setRoleName(bookingRevision.get("roleName", String.class));
            bookingDashBoardDto.setContractorName(bookingRevision.get("contractorName", String.class));
            bookingDashBoardDto.setContractedFromDate(CBSDateUtils.convertTimeStampToString(bookingRevision.get("contractedFromDate", Timestamp.class)));
            bookingDashBoardDto.setContractedToDate(CBSDateUtils.convertTimeStampToString(bookingRevision.get("contractedToDate", Timestamp.class)));
            bookingDashBoardDto.setChangedBy(bookingRevision.get("changedBy", String.class));
            bookingDashBoardDto.setChangedDate(CBSDateUtils.convertTimeStampToString(bookingRevision.get("changedDate", Timestamp.class)));
            bookingDashBoardDto.setBookingId(bookingRevision.get("bookingId", BigInteger.class));
            bookingDashBoardDto.setBookingRevisionId(bookingRevision.get("bookingRevisionId", BigInteger.class));
            bookingDashBoardDto.setBookingCreater(bookingRevision.get("bookingCreater", String.class));
           
            if(isWaitingStatus){
            bookingDashBoardDto.setCompletedAgreementPdf(bookingRevision.get("completedAgreementPdf", String.class));
            }	
            return bookingDashBoardDto;
            
            }).collect(Collectors.toList());
    }
    
    
	
}

