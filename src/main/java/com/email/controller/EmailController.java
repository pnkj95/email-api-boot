/*{
    "to":"pankajpathakk95@gmail.com",
    "subject":"API Creation Test",
    "message":"Hey, I'm just sending this message to test this API"
}*/
package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/welcome")
	public String welcome() {
		
		return "Welcome to email api";
	}
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
			System.out.println(request.toString());
			boolean result =  this.emailService.sendEmail(request.getMessage(), request.getSubject(), request.getTo());
			if(result) {
				return ResponseEntity.ok("Email sent successfully...");
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the email!");
			}
		}

}
