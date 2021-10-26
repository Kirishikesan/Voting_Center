package com.crypto.votingCenter.mobile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MobileController {
	
	@Autowired
	private MobileService mobileService;
	
	@RequestMapping(value = "/config")
	public void getCertificate(HttpServletRequest request, HttpServletResponse response){
		mobileService.getCertificate(request, response);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/config/key")
	public void setSymmetricKey(@RequestBody String request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		mobileService.setSymmetricKey(request);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/config/getOTP")
	public void sendRandomID(@RequestBody String request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		mobileService.sendOTP(request);
	}
	
}
