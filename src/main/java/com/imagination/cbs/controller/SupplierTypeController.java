package com.imagination.cbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagination.cbs.dto.SupplierTypeDto;
import com.imagination.cbs.service.SupplierTypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/supplier-types")
public class SupplierTypeController {

	@Autowired
	private SupplierTypeService supplierService;

	@GetMapping
	public List<SupplierTypeDto> getAllSupplierTypes() {
		return supplierService.getAllSupplierTypes();
	}

}
