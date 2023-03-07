package com.fourTL.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class RandomService {

	public String randomString(int length) {
		final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		SecureRandom  random = new SecureRandom();
		char[]text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(random.nextInt(characters.length()));
		}
		return new String(text);
	}
}
