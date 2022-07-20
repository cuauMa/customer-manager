/*
 * Copyright (c) 2022. This source code is owned to Cuauhtemoc Magana.
 *
 * The code is under the Apache Open Source without limits or restrictions to use.
 */

package mendoza.code.test.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mendoza.code.test.models.Customer;
import mendoza.code.test.repositories.CustomerRepository;
import mendoza.code.test.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mendoza.code.test.constants.ApplicationConstants.ADMIN_ROLE;
import static mendoza.code.test.constants.ApplicationConstants.RESPONSE_SUCCESS;

@RestController
@RequestMapping("/api")
@Api(value="customers", description="CRUD for Customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@ApiOperation(value = "Get a list of Customers",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	//@RequestMapping(value = "/customers", method= RequestMethod.GET, produces = { "application/json", "application/xml", "text/xml" })
	@GetMapping(value = "/customers", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customerList = new ArrayList<>();
		customerService.getCustomers().forEach(customerList::add);
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}

	@ApiOperation(value = "Get a Customer filtered by Id",response = Customer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@GetMapping(value = "/customers/{id}", produces = {"application/json", "application/xml"})
	public ResponseEntity<Customer> getAllCustomers(@PathVariable String id){
		Customer customer = customerService.getCustomer(Long.parseLong(id));
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@ApiOperation(value = "Add new Customers",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@PostMapping(value = "/customers/add", produces = {"application/json", "application/xml"})
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer){
		customerService.createCustomer(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@ApiOperation(value = "Update a Customers",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@PutMapping(value = "/customers/{id}", produces = {"application/json", "application/xml"})
	public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer){
		customerService.updateCustomer(Long.parseLong(id), customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a Customer",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@DeleteMapping(value = "/customers/{id}/{role}", produces = {"application/json", "application/xml"})
	public ResponseEntity<String> updateCustomer(@PathVariable String id, @PathVariable String role){
		if (role.equals(ADMIN_ROLE)) {
			customerService.deleteCustomer(Long.parseLong(id));
			return new ResponseEntity<>(RESPONSE_SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(id, HttpStatus.UNAUTHORIZED);
		}

	}
}
