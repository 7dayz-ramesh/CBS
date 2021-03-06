package com.imagination.cbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.imagination.cbs.domain.OfficeDm;
import com.imagination.cbs.domain.Region;
import com.imagination.cbs.dto.OfficeDto;
import com.imagination.cbs.dto.RegionDto;
import com.imagination.cbs.mapper.OfficeMapper;
import com.imagination.cbs.mapper.RegionMapper;
import com.imagination.cbs.repository.RegionRepository;
import com.imagination.cbs.repository.OfficeRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegionOfficeServiceImplTest {

	@InjectMocks
	private RegionOfficeServiceImpl regionOfficeServiceImpl;

	@Mock
	private RegionRepository regionRepository;

	@Mock
	private RegionMapper regionMapper;

	@Mock
	private OfficeRepository officeRepository;

	@Mock
	private OfficeMapper officeMapper;

	@Test
	public void shouldReturnListOfCountries() {

		List<Region> listOfRegionDms = createRegionDmList();

		when(regionRepository.findAll()).thenReturn(listOfRegionDms);
		when(regionMapper.toListOfRegionDTO(listOfRegionDms)).thenReturn(createRegionDtoList());

		List<RegionDto> actualListOfRegionDto = regionOfficeServiceImpl.getAllRegions();

		assertEquals(7000, actualListOfRegionDto.get(0).getRegionId());
		assertEquals("Australia", actualListOfRegionDto.get(0).getRegionName());
		assertEquals("Australia", actualListOfRegionDto.get(0).getRegionDescription());

	}

	@Test
	public void shouldReturnListOfOfficesFromRegionId() {

		List<OfficeDm> listOfOffices = createOfficeDmList();

		when(officeRepository.findAll()).thenReturn(listOfOffices);
		when(officeMapper.toListOfficeDTO(listOfOffices)).thenReturn(createOfficeDtoList());

		List<OfficeDto> actualListOfOfficeDto = regionOfficeServiceImpl.getAllOfficesInRegion(7000L);

		assertEquals(8000l, actualListOfOfficeDto.get(0).getOfficeId());
		assertEquals("Melbourne", actualListOfOfficeDto.get(0).getOfficeName());
		assertEquals(8001l, actualListOfOfficeDto.get(1).getOfficeId());
		assertEquals("Sydney", actualListOfOfficeDto.get(1).getOfficeName());
		assertEquals(2, actualListOfOfficeDto.size());
	}

	private List<RegionDto> createRegionDtoList() {
		List<RegionDto> listOfRegionDtos = new ArrayList<>();

		RegionDto regionDto = new RegionDto();
		regionDto.setRegionId(7000l);
		regionDto.setRegionName("Australia");
		regionDto.setRegionDescription("Australia");

		listOfRegionDtos.add(regionDto);
		return listOfRegionDtos;
	}

	private List<Region> createRegionDmList() {
		List<Region> listOfRegion = new ArrayList<Region>();

		Region RegionDm = new Region();
		RegionDm.setRegionId(7000l);
		RegionDm.setRegionName("Australia");
		RegionDm.setRegionDescription("Australia");

		listOfRegion.add(RegionDm);
		return listOfRegion;
	}

	private List<OfficeDm> createOfficeDmList() {
		List<OfficeDm> listOfOfficeDm = new ArrayList<>();

		OfficeDm officeDm1 = new OfficeDm();
		officeDm1.setOfficeId(8000l);
		officeDm1.setOfficeName("Melbourne");
		officeDm1.setOfficeDescription("Melbourne");

		OfficeDm officeDm2 = new OfficeDm();
		officeDm2.setOfficeId(8001l);
		officeDm2.setOfficeName("Sydney");
		officeDm2.setOfficeDescription("Sydney");

		listOfOfficeDm.add(officeDm1);
		listOfOfficeDm.add(officeDm2);
		return listOfOfficeDm;
	}

	private List<OfficeDto> createOfficeDtoList() {
		List<OfficeDto> listOfOfficeDto = new ArrayList<>();

		OfficeDto officeDto1 = new OfficeDto();
		officeDto1.setOfficeId(8000l);
		officeDto1.setOfficeName("Melbourne");
		officeDto1.setOfficeDescription("Melbourne");

		OfficeDto officeDto2 = new OfficeDto();
		officeDto2.setOfficeId(8001l);
		officeDto2.setOfficeName("Sydney");
		officeDto2.setOfficeDescription("Sydney");

		listOfOfficeDto.add(officeDto1);
		listOfOfficeDto.add(officeDto2);
		return listOfOfficeDto;
	}

}
