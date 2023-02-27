package com.example.studentassistancehelperapi.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.example.studentassistancehelperapi.Model.Mail;
import com.example.studentassistancehelperapi.Model.Response;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private Configuration config;

    @Override
    public Response sendEmail(Mail request, Map<String, Object> model, String to) {
        Response response = new Response();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            Template t = config.getTemplate("sap-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setTo(to);
            helper.setText(html, true);
            helper.setSubject("DNS Resolver Result");
            helper.setFrom("msandeepcip@gmail.com");
            sender.send(message);
            response.setMessage("mail send to : " + request.getTo());
            response.setStatus(Boolean.TRUE);
        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
