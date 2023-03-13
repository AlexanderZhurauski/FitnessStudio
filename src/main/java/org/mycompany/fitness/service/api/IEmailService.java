package org.mycompany.fitness.service.api;

public interface IEmailService {

    void sendConfirmationEmail(String address);

    boolean verifyEmail(String mail, String code);
}
