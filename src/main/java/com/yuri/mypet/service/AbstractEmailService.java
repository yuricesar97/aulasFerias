package com.yuri.mypet.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.yuri.mypet.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
    
	
	@Value("${default.sender}")
	private String sender;
	
	@Override //indica que estou sobrescrevendo o metado
    public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	
	//pode ser acessado por sub classes
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(obj.getCliente().getEmail());
	sm.setFrom(sender);
	sm.setSubject("Pedido comfirmado: " + obj.getId());
	sm.setSentDate(new Date(System.currentTimeMillis())); // horario do servidor
	sm.setText(obj.toString());
	
	return sm;
	}
		
}
