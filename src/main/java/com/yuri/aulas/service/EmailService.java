package com.yuri.aulas.service;

import org.springframework.mail.SimpleMailMessage;

import com.yuri.aulas.domain.Pedido;

public interface EmailService {

		void sendOrderConfirmationEmail(Pedido obj);
		
		void sendEmail(SimpleMailMessage msg);
}
