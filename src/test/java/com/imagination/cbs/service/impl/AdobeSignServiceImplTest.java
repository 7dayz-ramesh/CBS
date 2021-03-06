package com.imagination.cbs.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.imagination.cbs.domain.Booking;
import com.imagination.cbs.domain.BookingRevision;
import com.imagination.cbs.domain.Config;
import com.imagination.cbs.domain.Contractor;
import com.imagination.cbs.dto.AdobeOAuthDto;
import com.imagination.cbs.exception.CBSApplicationException;
import com.imagination.cbs.exception.ResourceNotFoundException;
import com.imagination.cbs.repository.ConfigRepository;
import com.imagination.cbs.util.AdobeTokenUtility;

@RunWith(MockitoJUnitRunner.class)
public class AdobeSignServiceImplTest {
	
	@InjectMocks
	private AdobeSignServiceImpl adobeSignServiceImpl;
	
	@Mock
	private ConfigRepository configRepository;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private Environment environment;
	@Mock
	private AdobeTokenUtility adobeUtility;
	@Mock
	private ResponseEntity<JsonNode> res;
	
	@Before
	public void init() throws Exception {
		
		when(adobeUtility.getOauthAccessToken()).thenReturn("AcessToken");
		when(adobeUtility.getBaseURIForRestAPI("AcessToken")).thenReturn("baseurl");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnDownloadedAgreementAsInputStream_DownloadAgreement(){

		ResponseEntity<byte[]> mockedResult = Mockito.mock(ResponseEntity.class);
		byte b[] = {20,10,30,5};
		
		when(restTemplate.exchange(eq("baseurl/agreements/A-101/combinedDocument"), eq(HttpMethod.GET), Mockito.any(), eq(byte[].class))).thenReturn(mockedResult);
		when(mockedResult.getBody()).thenReturn(b);
		
		InputStream actualInputStream = adobeSignServiceImpl.downloadAgreement("A-101");
		
		verify(restTemplate).exchange(eq("baseurl/agreements/A-101/combinedDocument"), eq(HttpMethod.GET), Mockito.any(), eq(byte[].class));
		
		assertNotNull(actualInputStream);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void shouldThrowResourceNotFoundException_DownloadAgreement() throws Exception {
		
		when(adobeUtility.getOauthAccessToken()).thenThrow(RuntimeException.class);
		
		adobeSignServiceImpl.downloadAgreement("A-101");
	}
	
	@Test
	public void shouldUpdateExistingAdobeKey_SaveOrUpdateAdobeKeys() throws Exception {
		
		AdobeOAuthDto adobeOAuth = createAdobeOAuthDto();
		beforeSaveOrUpdateAdobeKeysTestCases(false);

		adobeSignServiceImpl.saveOrUpdateAdobeKeys(adobeOAuth);
	}
	
	@Test
	public void shouldSaveNewAdobeKeyDetailsWhenAdobeKeyIsNotPresentInDB_SaveOrUpdateAdobeKeys() throws Exception {
		
		AdobeOAuthDto adobeOAuth = createAdobeOAuthDto();
		beforeSaveOrUpdateAdobeKeysTestCases(true);

		adobeSignServiceImpl.saveOrUpdateAdobeKeys(adobeOAuth);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void shouldThrowResourceNotFoundWhenKeyNameIsNotPresentInDB_SaveOrUpdateAdobeKeys() throws Exception {
		
		AdobeOAuthDto adobeOAuth = createAdobeOAuthDto();
		
		when(configRepository.findByKeyName("ADOBE_ACCESS_TOKEN")).thenThrow(RuntimeException.class);
		
		adobeSignServiceImpl.saveOrUpdateAdobeKeys(adobeOAuth);
		
		verify(configRepository).findByKeyName("ADOBE_ACCESS_TOKEN");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnTransientDocumentId_UploadAndCreateAgreement(){
		
		String testStr = "Content to upload an Agreement";
		InputStream inputStream = new ByteArrayInputStream(testStr.getBytes());
		ResponseEntity<JsonNode> mockedResult = Mockito.mock(ResponseEntity.class);
		JsonNode mockedJsonNode = Mockito.mock(JsonNode.class);
		
		when(restTemplate.exchange(eq("baseurl/transientDocuments"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class))).thenReturn(mockedResult);
		when(mockedResult.getBody()).thenReturn(mockedJsonNode);
		when(mockedJsonNode.path("transientDocumentId")).thenReturn(mockedJsonNode);
		when(mockedJsonNode.asText()).thenReturn("TransientId:111222");
		
		String transientId = adobeSignServiceImpl.uploadAndCreateAgreement(inputStream, "Test.pdf");
		
		verify(restTemplate).exchange(eq("baseurl/transientDocuments"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class));
		
		assertEquals("TransientId:111222", transientId);
	}
	
	@Test
	public void shouldReturnNullWhenExceptionOccuredDuringUploadingOrCreatingAnAgreement_UploadAndCreateAgreement() {
		String testStr = "Content to upload an Agreement";
		InputStream inputStream = new ByteArrayInputStream( testStr.getBytes() );
		
		when(adobeUtility.getOauthAccessToken()).thenThrow(RuntimeException.class);
		
		String transientId = adobeSignServiceImpl.uploadAndCreateAgreement(inputStream, "Test.pdf");
	
		assertNull(transientId);
	}
		
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnAgreementId_SendAgreement() throws Exception {
		String transientDocId = "TransientDocId";
		BookingRevision bookingRevision = getBookingRevision();
		ResponseEntity<JsonNode> mockedResult = Mockito.mock(ResponseEntity.class);
		JsonNode mockedJsonNode = Mockito.mock(JsonNode.class);
		String envArr[] = {"dev", "local", "qual"};

		when(environment.getActiveProfiles()).thenReturn(envArr);
		when(restTemplate.exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class))).thenReturn(mockedResult);
		when(mockedResult.getBody()).thenReturn(mockedJsonNode);
		when(mockedJsonNode.path("id")).thenReturn(mockedJsonNode);
		when(mockedJsonNode.asText()).thenReturn("Id:12345");
		
		String id = adobeSignServiceImpl.sendAgreement(transientDocId, bookingRevision);

		verify(environment).getActiveProfiles();
		verify(restTemplate).exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class));

		assertEquals("Id:12345", id);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnAgreementIdForLocalEnv_SendAgreement() throws Exception {
		String transientDocId = "TransientDocId";
		BookingRevision bookingRevision = getBookingRevision();
		ResponseEntity<JsonNode> mockedResult = Mockito.mock(ResponseEntity.class);
		JsonNode mockedJsonNode = Mockito.mock(JsonNode.class);
		String envArr[] = {"local"};

		when(environment.getActiveProfiles()).thenReturn(envArr);
		when(restTemplate.exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class))).thenReturn(mockedResult);
		when(mockedResult.getBody()).thenReturn(mockedJsonNode);
		when(mockedJsonNode.path("id")).thenReturn(mockedJsonNode);
		when(mockedJsonNode.asText()).thenReturn("Id:12345");
		
		String id = adobeSignServiceImpl.sendAgreement(transientDocId, bookingRevision);

		verify(environment).getActiveProfiles();
		verify(restTemplate).exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class));

		assertEquals("Id:12345", id);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnAgreementIdForQualEnv_SendAgreement() throws Exception {
		String transientDocId = "TransientDocId";
		BookingRevision bookingRevision = getBookingRevision();
		ResponseEntity<JsonNode> mockedResult = Mockito.mock(ResponseEntity.class);
		JsonNode mockedJsonNode = Mockito.mock(JsonNode.class);
		String envArr[] = {"qual"};

		when(environment.getActiveProfiles()).thenReturn(envArr);
		when(restTemplate.exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class))).thenReturn(mockedResult);
		when(mockedResult.getBody()).thenReturn(mockedJsonNode);
		when(mockedJsonNode.path("id")).thenReturn(mockedJsonNode);
		when(mockedJsonNode.asText()).thenReturn("Id:12345");
		
		String id = adobeSignServiceImpl.sendAgreement(transientDocId, bookingRevision);

		verify(environment).getActiveProfiles();
		verify(restTemplate).exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class));

		assertEquals("Id:12345", id);
	}

	
	@SuppressWarnings("unchecked")
	@Test(expected = CBSApplicationException.class)
	public void shouldThrowCBSApplicationExceptionWhenExceptionOccuredDutingSendingAnAgreement_SendAgreement() throws Exception {
		
		String transientDocId = "TransientDocId";
		BookingRevision bookingRevision = getBookingRevision();
		ResponseEntity<JsonNode> mockedResult = Mockito.mock(ResponseEntity.class);
		String envArr[] = {"test"};

		when(environment.getActiveProfiles()).thenReturn(envArr);
		when(restTemplate.exchange(eq("baseurl/agreements"), eq(HttpMethod.POST), Mockito.any(), eq(JsonNode.class))).thenReturn(mockedResult);
		when(mockedResult.getBody()).thenThrow(RuntimeException.class);
		
		adobeSignServiceImpl.sendAgreement(transientDocId, bookingRevision);
	}
	
	
	@Test
	public void shouldSaveNewAuthCodeAndApiAccessPointWhenKeyNameNotPresentInDB_SaveOrUpdateAuthCode() {

		Config config = new Config();

		Optional<Config> configOptional = Optional.empty();
		
		when(configRepository.findByKeyName("ADOBE_AUTH_CODE")).thenReturn(configOptional);
		when(configRepository.findByKeyName("ADOBE_API_BASE_URI")).thenReturn(configOptional);
		when(configRepository.save(Mockito.any())).thenReturn(config);
		
		adobeSignServiceImpl.saveOrUpdateAuthCode("AuthCode","apiAccessPoint","webAccessPoint");
	
		verify(configRepository).findByKeyName("ADOBE_AUTH_CODE");
		verify(configRepository).findByKeyName("ADOBE_API_BASE_URI");
		verify(configRepository, times(3)).save(Mockito.any());
	}

	@Test
	public void shouldUpdateAuthCodeAndApiAccessPointWhenKeyNameIsPresentInDB_SaveOrUpdateAuthCode() {

		Config config = new Config();
		Optional<Config> configOptional = Optional.of(config);
		
		when(configRepository.findByKeyName("ADOBE_AUTH_CODE")).thenReturn(configOptional);
		when(configRepository.findByKeyName("ADOBE_API_BASE_URI")).thenReturn(configOptional);
		when(configRepository.save(Mockito.any())).thenReturn(config);
		
		adobeSignServiceImpl.saveOrUpdateAuthCode("AuthCode","apiAccessPoint","webAccessPoint");
		
		verify(configRepository).findByKeyName("ADOBE_AUTH_CODE");
		verify(configRepository).findByKeyName("ADOBE_API_BASE_URI");
		verify(configRepository, times(3)).save(Mockito.any());
	}

	private void beforeSaveOrUpdateAdobeKeysTestCases(boolean isEmptyOptional) throws Exception {
		Config config = new Config();
		config.setKeyName("TestKey");
		config.setKeyValue("TestValue");
		config.setKeyDescription("TestKeyDescription");

		Optional<Config> configOptional = null;
		if(isEmptyOptional) {
			configOptional = Optional.empty();
		}else {
			configOptional = Optional.of(config);
		}
		when(configRepository.findByKeyName("ADOBE_ACCESS_TOKEN")).thenReturn(configOptional);
		when(configRepository.findByKeyName("ADOBE_REFRESH_TOKEN")).thenReturn(configOptional);
		when(configRepository.findByKeyName("ADOBE_TOKEN_TYPE")).thenReturn(configOptional);
		when(configRepository.findByKeyName("ADOBE_ACCESS_TOKEN_EXP_TIME")).thenReturn(configOptional);
		when(configRepository.save(Mockito.any())).thenReturn(config);

	}
	
	private BookingRevision getBookingRevision() {
		
		BookingRevision bookingRevision = new BookingRevision();
		Booking booking = new Booking();
		booking.setBookingId(5001L);
		bookingRevision.setBooking(booking);
		bookingRevision.setJobNumber("2010");
		bookingRevision.setJobname("TestJob");
		Contractor contractor = new Contractor();
		contractor.setEmail("abc@test.com");
		bookingRevision.setContractor(contractor);

		return bookingRevision;
	}

	private AdobeOAuthDto createAdobeOAuthDto() {
		
		AdobeOAuthDto adobeOAuth = new AdobeOAuthDto();
		adobeOAuth.setAccessToken("AccessToken-1234");
		adobeOAuth.setRefreshToken("RefreshToken-1234");
		adobeOAuth.setTokenType("Access");
		adobeOAuth.setExpiresIn(Integer.MAX_VALUE);

		return adobeOAuth;
	}
} 