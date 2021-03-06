package com.imagination.cbs.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.imagination.cbs.constant.EmailConstants;
import com.imagination.cbs.domain.ApprovalStatusDm;
import com.imagination.cbs.domain.BookingRevision;
import com.imagination.cbs.domain.BookingWorkTask;
import com.imagination.cbs.domain.Contractor;
import com.imagination.cbs.domain.ContractorEmployee;
import com.imagination.cbs.dto.InternalResourceEmailDto;
import com.imagination.cbs.dto.MailRequest;
import com.imagination.cbs.repository.ApprovalStatusDmRepository;
import com.imagination.cbs.security.CBSUser;
import com.imagination.cbs.service.EmailService;
import com.imagination.cbs.service.LoggedInUserService;
import com.imagination.cbs.util.CBSDateUtils;
import com.imagination.cbs.util.EmailUtility;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailUtility emailUtility;

	@Autowired
	private Configuration config;

	@Autowired
	private LoggedInUserService loggedInUserService;

	@Autowired
	private Environment env;

	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	private static final String TD = "</td>";

	@Value("${baseUrl}")
	private String baseUrl;

	@Autowired
	private ApprovalStatusDmRepository approvalStatusDmRepository;

	@Override
	public void sendEmailForBookingApproval(MailRequest request, BookingRevision bookingRevision, String templateName) { 

		logger.info("MailRequest :: {} CURRENT STATUS :: {} BOOKING_ID :: {}", request,
				bookingRevision.getApprovalStatus().getApprovalName(), bookingRevision.getBooking().getBookingId());

		try {

			Template contractNotificationEmailTemplate = config.getTemplate(
					EmailConstants.PREFIX.getConstantString() + templateName + EmailConstants.EXT.getConstantString());

			String body = FreeMarkerTemplateUtils.processTemplateIntoString(contractNotificationEmailTemplate,
					getBookingApprovalDataModel(bookingRevision));

			emailUtility.sendEmail(request, body);

		} catch (Exception e) {
			logger.error("Not able to send Booking approval email", e);
		}
	}

	@Override
	public void sendContractReceipt(BookingRevision revision) {
		try {

			String[] contractReceiptToEmailReceipt = {
					revision.getChangedBy() + EmailConstants.DOMAIN.getConstantString(),
					revision.getContractor().getEmail() };

			String subject = MessageFormat.format(
					(String) EmailConstants.CONTRACT_RCEIPT_SUBJECT_LINE.getConstantString(),
					String.valueOf(revision.getJobNumber()), revision.getJobname(),
					String.valueOf(revision.getBooking().getBookingId()));

			MailRequest emailRequestDetails = new MailRequest();
			emailRequestDetails.setMailFrom(EmailConstants.FROM_EMAIL.getConstantString());
			emailRequestDetails.setMailTo(contractReceiptToEmailReceipt);
			emailRequestDetails.setSubject(subject);

			Template contractNotificationEmailTemplate = config.getTemplate(EmailConstants.PREFIX.getConstantString()
					+ EmailConstants.CONTRACT_RECEIPT_TEMPLATE.getConstantString()
					+ EmailConstants.EXT.getConstantString());

			String body = FreeMarkerTemplateUtils.processTemplateIntoString(contractNotificationEmailTemplate,
					getContractReceiptDataModel(revision));

			emailUtility.sendEmail(emailRequestDetails, body);

		} catch (Exception e) {

			logger.error("Not able to send Contract Receipt email", e);
		}
	}

	@Override
	public void sendInternalResourceEmail(InternalResourceEmailDto internalResourceEmail) {

		try {
			String[] internalResourceToEmailReceipt = env.getProperty(EmailConstants.TO_EMAIL.getConstantString(),
					String[].class);

			MailRequest emailrequestDetails = new MailRequest();
			emailrequestDetails.setMailFrom(EmailConstants.FROM_EMAIL.getConstantString());
			emailrequestDetails.setMailTo(internalResourceToEmailReceipt);
			emailrequestDetails.setSubject(EmailConstants.INTERNAL_NOTIFICATION_SUBJECT_LINE.getConstantString());

			Template pushNotificationEmailTemplate = config.getTemplate(EmailConstants.PREFIX.getConstantString()
					+ EmailConstants.INTERNAL_SOURCE.getConstantString() + EmailConstants.EXT.getConstantString());
			String body = FreeMarkerTemplateUtils.processTemplateIntoString(pushNotificationEmailTemplate,
					getInternalResourceEmailDataModel(internalResourceEmail));
			emailUtility.sendEmail(emailrequestDetails, body);

			logger.info("Email send Successfully :: {}", emailrequestDetails);
		} catch (Exception e) {
			logger.error("Not able to send email", e);

		}

	}

	private Map<String, Object> getBookingApprovalDataModel(BookingRevision bookingRevision) {

		Map<String, Object> mapOfTemplateValues = new HashMap<>();

		BigDecimal contractAmountAftertax = bookingRevision.getContractAmountAftertax();
		ContractorEmployee contractEmployee = bookingRevision.getContractEmployee();
		String contractEmployeeName = contractEmployee != null
				? validateString(contractEmployee.getContractorEmployeeName()) : "";

		Contractor contractor = bookingRevision.getContractor();
		String contractorName = contractor != null ? validateString(contractor.getContractorName()) : "";

		mapOfTemplateValues.put(EmailConstants.DISCIPLINE.getConstantString(),
				bookingRevision.getRole().getDiscipline().getDisciplineName());
		mapOfTemplateValues.put(EmailConstants.ROLE.getConstantString(), bookingRevision.getRole().getRoleName());
		mapOfTemplateValues.put(EmailConstants.CONTRCTOR_EMPLOYEE.getConstantString(), contractEmployeeName);
		mapOfTemplateValues.put(EmailConstants.CONTRCTOR.getConstantString(), contractorName);
		mapOfTemplateValues.put(EmailConstants.SUPPLIER_TYPE.getConstantString(),
				validateString(bookingRevision.getSupplierType().getName()));
		mapOfTemplateValues.put(EmailConstants.START_DATE.getConstantString(),
				CBSDateUtils.convertTimeStampToString(bookingRevision.getContractedFromDate()));
		mapOfTemplateValues.put(EmailConstants.END_DATE.getConstantString(),
				CBSDateUtils.convertTimeStampToString(bookingRevision.getContractedToDate()));
		mapOfTemplateValues.put(EmailConstants.WORK_LOCATIONS.getConstantString(),
				validateString(bookingRevision.getContractWorkLocation().getOfficeName()));
		mapOfTemplateValues.put(EmailConstants.REASON_FOR_RECRUITING.getConstantString(),
				validateString(bookingRevision.getReasonForRecruiting().getReasonName()));
		mapOfTemplateValues.put(EmailConstants.TOTAL_COST.getConstantString(),
				contractAmountAftertax == null ? "" : contractAmountAftertax);

		List<BookingWorkTask> bookingWorkTasks = bookingRevision.getBookingWorkTasks();
		if (CollectionUtils.isEmpty(bookingWorkTasks)) {
			mapOfTemplateValues.put(EmailConstants.WORK_TASKS.getConstantString(), "");
		} else {
			StringBuilder row = new StringBuilder();
			row.append("<table style=\"border: 1px solid black;width: 75%;  margin-left: 16%; font-size: 10px;\">");
			row.append("<tr style=\"border: 1px solid black;text-align: left;padding: 8px;\">");
			row.append("<th bgcolor=\"#A9A9A9\">#</th>");
			row.append("<th bgcolor=\"#A9A9A9\">Task</th>");
			row.append("<th bgcolor=\"#A9A9A9\">Delivery date</th>");
			row.append("<th bgcolor=\"#A9A9A9\">Day rate</th>");
			row.append("<th bgcolor=\"#A9A9A9\">Total days</th>");
			row.append("<th bgcolor=\"#A9A9A9\">Total(£)</tr>");
			int i = 0;
			for (BookingWorkTask task : bookingWorkTasks) {
				row.append("<tr style=\"border: 1px solid black;\"background-color: #dddddd;\">");
				row.append("<td>" + ++i + TD);
				row.append("<td>" + task.getTaskName() + TD);
				row.append("<td>" + CBSDateUtils.convertDateToString(task.getTaskDeliveryDate()) + TD);
				row.append("<td>" + task.getTaskDateRate() + TD);
				row.append("<td>" + task.getTaskTotalDays() + TD);
				row.append("<td>" + task.getTaskTotalAmount() + TD);
				row.append("</tr>");
			}
			row.append("</table>");
			mapOfTemplateValues.put(EmailConstants.WORK_TASKS.getConstantString(), row.toString());
		}

		String creator = bookingRevision.getBooking().getChangedBy();
		mapOfTemplateValues.put(EmailConstants.REQUESTED_BY.getConstantString(), creator);
		mapOfTemplateValues.put(EmailConstants.EMAIL_ADDRESS.getConstantString(),
				creator + EmailConstants.DOMAIN.getConstantString());
		baseUrl = baseUrl.replace("{bookingId}", String.valueOf(bookingRevision.getBooking().getBookingId()));
		Optional<ApprovalStatusDm> status = approvalStatusDmRepository
				.findById(bookingRevision.getApprovalStatus().getApprovalStatusId());
		baseUrl = baseUrl.replace("{status}", status.isPresent() ? status.get().getApprovalName() : "");
		mapOfTemplateValues.put(EmailConstants.REDIRECT_URL.getConstantString(), baseUrl);
		return mapOfTemplateValues;
	}

	private Map<String, Object> getInternalResourceEmailDataModel(InternalResourceEmailDto internalResourceEmail) {

		Map<String, Object> mapOfTemplateValues = new HashMap<>();

		mapOfTemplateValues.put(EmailConstants.DISCIPLINE.getConstantString(), internalResourceEmail.getDiscipline());
		mapOfTemplateValues.put(EmailConstants.ROLE.getConstantString(), internalResourceEmail.getRole());
		mapOfTemplateValues.put(EmailConstants.START_DATE.getConstantString(),
				internalResourceEmail.getContractedFromDate());
		mapOfTemplateValues.put(EmailConstants.END_DATE.getConstantString(),
				internalResourceEmail.getContractedToDate());
		mapOfTemplateValues.put(EmailConstants.JOB_NUMBER.getConstantString(), internalResourceEmail.getJobNumber());
		mapOfTemplateValues.put(EmailConstants.JOB_NAME.getConstantString(), internalResourceEmail.getJobName());

		return mapOfTemplateValues;
	}

	private Map<String, Object> getContractReceiptDataModel(BookingRevision revision) {

		Map<String, Object> mapOfTemplateValues = new HashMap<>();

		CBSUser user = loggedInUserService.getLoggedInUserDetails();

		mapOfTemplateValues.put(EmailConstants.REQUESTED_BY.getConstantString(), user.getDisplayName());
		mapOfTemplateValues.put(EmailConstants.EMAIL_ADDRESS.getConstantString(),
				user.getEmail() + EmailConstants.DOMAIN.getConstantString());
		mapOfTemplateValues.put(EmailConstants.CONTRACTOR_PDF_LINK.getConstantString(),
				validateString(revision.getCompletedAgreementPdf()));

		return mapOfTemplateValues;
	}

	private String validateString(String str) {
		return StringUtils.isEmpty(str) ? "" : str;
	}

}
