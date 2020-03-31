package com.imagination.cbs.mapper;

import java.sql.Timestamp;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.imagination.cbs.domain.Booking;
import com.imagination.cbs.domain.BookingRevision;
import com.imagination.cbs.dto.BookingDto;
import com.imagination.cbs.dto.BookingRequest;
import com.imagination.cbs.util.DateUtils;

@Mapper(componentModel = "spring")
public interface BookingMapper {

	public Booking toBookingDomainFromBookingDto(BookingRequest bookingDto);

	public BookingDto toBookingDtoFromBooking(Booking booking);

	@Named("stringToTimeStamp")
	public static Timestamp stringToTimeStampConverter(String date) {
		return DateUtils.convertDateToTimeStamp(date);
	}

	@Named("timeStampToString")
	public static String timeStampToStringConverter(Timestamp timeStamp) {
		return timeStamp.toString();
	}

	@Named("longToString")
	public static String longToStringConverter(String domainValue) {
		return domainValue.toString();
	}

	@Mapping(source = "contractedFromDate", target = "contractedFromDate", qualifiedByName = "timeStampToString")
	@Mapping(source = "contractedToDate", target = "contractedToDate", qualifiedByName = "timeStampToString")
	public BookingDto toBookingDtoFromBookingRevision(BookingRevision bookingRevisionDto);

	@Mapping(source = "contractedFromDate", target = "contractedFromDate", qualifiedByName = "stringToTimeStamp")
	@Mapping(source = "contractedToDate", target = "contractedToDate", qualifiedByName = "stringToTimeStamp")
	@Mapping(source = "contractorSignedDate", target = "contractorSignedDate", qualifiedByName = "stringToTimeStamp")
	public BookingRevision toBookingRevisionFromBookingDto(BookingRequest bookingDto);

}