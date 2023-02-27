package com.example.studentassistancehelperapi.Controller;
import com.example.studentassistancehelperapi.Model.Mail;
import com.example.studentassistancehelperapi.Service.MailService;
import com.example.studentassistancehelperapi.Util.MailThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {
    @Autowired
   private MailService mailService;

    @PostMapping(value = "/api/mail",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = {"http://localhost:8080","https://studentassist.vercel.app/","https://cn-demo-rvce.vercel.app/"} )
    public void sendEmail(@RequestBody Mail request) {
        Map<String, Object> model = new HashMap<>();
        model.put("ip", request.getIp());
        model.put("version", request.getVersion());
        model.put("url", request.getUrl());
        model.put("domain",request.getDomain());
        model.put("city",request.getCity());
        model.put("region",request.getRegion());
        model.put("org",request.getOrg());
        model.put("country_name",request.getCountry_name());
        for(String to : request.getTo()){
           new Thread(new MailThread(mailService,request,model,to)).start();
        }
    }
}