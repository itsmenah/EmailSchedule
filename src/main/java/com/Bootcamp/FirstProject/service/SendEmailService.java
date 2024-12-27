package com.Bootcamp.FirstProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import com.Bootcamp.FirstProject.ScheduledEmail;

@Service
@Component
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ScheduleEmailService ses;

    @Value("${spring.mail.username}")
    private String sender;

    @Scheduled(fixedRate = 60000)
    public String sendSimpleMail() {

        List<ScheduledEmail> ScheduleList = ses.getAllScheduledEmails();
        //Date Compare
        //

        for (ScheduledEmail s : ScheduleList) {
           System.out.println(s);
            if (s.getTime().toInstant().isBefore(new Date().toInstant())) {
                try {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom(s.getSender());
                    message.setTo(s.getReceiver());
                    message.setSubject(s.getSubject());
                    message.setText(s.getBody());
                    javaMailSender.send(message);
                    System.out.println("Mail sent - "+s.getId()+" time - "+s.getTime());
                    System.out.println("Mail Entry Deleted - " + ses.deleteScheduledEmailById(s.getId()));
                } catch (Exception e) {
                    return "Error occured while trying to send mail";
                }
            }
            else {
                System.out.println("Mail date is in the future "+ s.getTime());
            }
        }
        System.out.println("All Mails Sent");
        return "All Mails Successfully Sent";
    }
}
