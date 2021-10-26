package com.crypto.votingCenter.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(String id) {	
		return userRepository.findById(id).get();
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(String id, User user) {
		userRepository.save(user);
	}

	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
	
	public void addRandomId(String id, String randomID) {
		User user=userRepository.findById(id).get();
		user.setRandomID(randomID);
		userRepository.save(user);
	}
}
