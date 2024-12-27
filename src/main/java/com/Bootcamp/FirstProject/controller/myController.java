package com.Bootcamp.FirstProject.controller;

import com.Bootcamp.FirstProject.service.ScheduleEmailService;
import com.Bootcamp.FirstProject.ScheduledEmail;
import com.Bootcamp.FirstProject.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class myController {

    private final ScheduleEmailService scheduleEmailService;
    private final SendEmailService sendEmailService;

    @Autowired
    public myController(ScheduleEmailService scheduleEmailService, SendEmailService sendEmailService) {
        this.scheduleEmailService = scheduleEmailService;
        this.sendEmailService = sendEmailService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/schedules.html")
    public String getAllSchedules(Model model) {

        List<ScheduledEmail> schedules = scheduleEmailService.getAllScheduledEmails();
        model.addAttribute("schedules", schedules);
        return "schedules";

    }

    @GetMapping("/Schedules/{id}")
    public ScheduledEmail getScheduleById(@PathVariable int id) {
        return scheduleEmailService.getScheduledEmailById(id);
    }


    @PostMapping("/AddEmail")
    public String EmailAdd(@RequestBody ScheduledEmail se)
    {
        String status =  scheduleEmailService.saveScheduledEmail(se);

        return status;
    }

    @GetMapping("/emailForm.html")
    public String showEmailForm() {
        return "emailForm";  // Serves the emailForm.html page
    }

}
