package com.yuri.mypet.service;

import org.springframework.mail.SimpleMailMessage;

import com.yuri.mypet.domain.Pedido;

public interface EmailService {

		void sendOrderConfirmationEmail(Pedido obj);
		
		void sendEmail(SimpleMailMessage msg);
}
