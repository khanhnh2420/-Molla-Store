package com.fourTL.service;

import org.springframework.stereotype.Service;

import com.fourTL.entity.MailInfo;

import jakarta.mail.MessagingException;

@Service
public interface MailService {
	void send(MailInfo mail) throws MessagingException;

	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mail);
	void queue(String to, String subject, String body);

}
