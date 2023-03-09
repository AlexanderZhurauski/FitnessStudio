package org.mycompany.fitness.service;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.mycompany.fitness.service.api.IEmailService;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService implements IEmailService {

    private JavaMailSender mailSender;
    private JobScheduler jobScheduler;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendConfirmationEmail(String address) {

    }

    @Job(name = "Confirmation Link", retries = 3)
    public void sendConfirmationLinkAsync(String address, String token) {

    }
}
