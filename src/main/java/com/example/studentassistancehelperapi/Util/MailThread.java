package com.example.studentassistancehelperapi.Util;


import com.example.studentassistancehelperapi.Model.Mail;
import com.example.studentassistancehelperapi.Service.MailService;

import java.util.Map;

public class MailThread implements Runnable{

    private MailService mailService;
    private Mail mail;
    private Map<String,Object> model;

    private String to;

    public MailThread(MailService mailService, Mail mail, Map<String, Object> model, String to) {
        this.mailService = mailService;
        this.mail = mail;
        this.model = model;
        this.to = to;
    }

    @Override
    public void run() {
       mailService.sendEmail(mail,model,to);
    }
}
