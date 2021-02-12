package br.com.irdbr.springbootionic.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.irdbr.springbootionic.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
