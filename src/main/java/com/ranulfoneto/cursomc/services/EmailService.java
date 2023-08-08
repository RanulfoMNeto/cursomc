package com.ranulfoneto.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.ranulfoneto.cursomc.domain.Pedido;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}
