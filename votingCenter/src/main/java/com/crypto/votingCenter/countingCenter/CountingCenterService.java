package com.crypto.votingCenter.countingCenter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crypto.votingCenter.https.RSA;
import com.crypto.votingCenter.mail.MailService;
import com.crypto.votingCenter.user.User;
import com.crypto.votingCenter.user.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class CountingCenterService {

	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;

	RSA rsa = new RSA();
	
	
	public void getCertificate(HttpServletRequest request, HttpServletResponse response){
		String privateEncryptionKeyDir ="../electionCommission/src/main/resources/keys/encryption/";
		String privateEncryptionKeyPath = "private.der";
		Path file=Paths.get(privateEncryptionKeyDir, privateEncryptionKeyPath);
		if(Files.exists(file)) {
			response.setContentType("application/x-x509-user-cert");
            response.addHeader("Content-Disposition", "attachment; filename="+privateEncryptionKeyPath);
            try {
            	Files.copy(file, response.getOutputStream());
            	response.getOutputStream().flush();
            }
            catch(IOException ex) {
            	ex.printStackTrace();
            }
		}
		
	}
	
	public String verifyBallot(@RequestBody String request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		String decryptedRequest=rsa.decrypt(request);
		JsonObject requestJson = new Gson().fromJson(decryptedRequest, JsonObject.class);
		String ballotID=requestJson.get("ballotID").getAsString();
		List<User> users = new ArrayList<User>();
		userService.getAllUsers().forEach(i -> {
			if(i.getBallotID().equals(ballotID)) {
				users.add(i);
			}
		});
		if(users.size()==1) {
			User user = users.get(0);
			String sign=rsa.signMessage(ballotID);
			return "{\"Valid\":1,\"ballotID\":\""+ballotID+"\",\"signature\":\""+sign+"\"}";
		}else {
			System.out.println("invalid ballotID");
			return "{\"Valid\":0,\"ballotID\":\""+ballotID+"\"}";
		}
			
	}

}
