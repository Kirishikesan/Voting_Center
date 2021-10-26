package com.crypto.votingCenter.mobile;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class MobileService {

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
	
	public void setSymmetricKey(@RequestBody String request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		String decryptedRequest=rsa.decrypt(request);
		JsonObject requestJson = new Gson().fromJson(decryptedRequest, JsonObject.class);
	}
	
	public void sendOTP(@RequestBody String request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		String decryptedRequest=rsa.decrypt(request);
		JsonObject requestJson = new Gson().fromJson(decryptedRequest, JsonObject.class);
		String RandomID=requestJson.get("randomID").getAsString();
		String deviceID=requestJson.get("deviceID").getAsString();
		User user=userService.getUser(RandomID);
		if(user.getDeviceID().equals(deviceID)) {
			String OTP=mailService.sendEmail(user.getEmail());
			FileWriter myWriter = new FileWriter("ClientData.txt");
			myWriter.write(RandomID+" "+OTP);
			myWriter.close();
		}else {
			System.out.println("DeviceID not valid");
		}
	}
	
	

}
