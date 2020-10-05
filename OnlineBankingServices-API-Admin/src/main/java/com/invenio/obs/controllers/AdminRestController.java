package com.invenio.obs.controllers;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invenio.obs.beans.Customer;
import com.invenio.obs.interfaces.AdminDaoInterface;
import com.invenio.obs.payload.UploadFileResponse;
import com.invenio.obs.services.FileStorageService;


@RestController
public class AdminRestController {


	@Autowired
	AdminDaoInterface admindao;
	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri,
				file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello OnlineBankingServices-API-Admin";
	}

	@PostMapping("/customer/addAccount")
	public String addCustomer(@RequestBody Customer customer) {

		return admindao.createCustomerAccount(customer);
	}


	@DeleteMapping("/customer/deleteAccount/{accountNumber}")
	public String deleteCustomerAccount(@PathVariable("accountNumber")int accountNumber) {

		return admindao.deleteCustomerAccount(accountNumber);
	}

	@GetMapping("/customerAllDetails/{accountNumber}")
	public Customer customerDetails(@PathVariable("accountNumber")int accountNumber){
		return admindao.viewCustomerDetails(accountNumber);
	}

	@PutMapping("/customer/updateCustomerEmailAccount/{accountNumber}/{customerEmailId}")
	public String updateCustomerEmailAccount(@PathVariable("accountNumber") int accountNumber,@PathVariable("customerEmailId") String customerEmailId ) {

		return admindao.updateCustomerEmailAccount(accountNumber,customerEmailId);
	}

	@PutMapping("/customer/updateCustomerPhoneAccount/{accountNumber}/{customerPhoneNumber}")
	public String updateCustomerPhoneAccount(@PathVariable("accountNumber") int accountNumber,@PathVariable("customerPhoneNumber") String customerPhoneNumber ) {

		return admindao.updateCustomerPhoneAccount(accountNumber,customerPhoneNumber);
	}

	@PutMapping("/customer/updateCustomerAddressAccount/{accountNumber}/{customerAddress}")
	public String updateCustomerAddressAccount(@PathVariable("accountNumber") int accountNumber,@PathVariable("customerAddress") String customerAddress ) {

		return admindao.updateCustomerAddressAccount(accountNumber,customerAddress);
	}
}
