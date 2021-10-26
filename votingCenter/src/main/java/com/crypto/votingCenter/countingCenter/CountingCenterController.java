package com.crypto.votingCenter.countingCenter;


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
public class CountingCenterController {
	
	@Autowired
	private CountingCenterService countinCenterService;
	
	@RequestMapping(value = "/counting")
	public void getCertificate(HttpServletRequest request, HttpServletResponse response){
		countinCenterService.getCertificate(request, response);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/counting")
	public String verify(@RequestBody String request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return countinCenterService.verifyBallot(request);
	}
}
