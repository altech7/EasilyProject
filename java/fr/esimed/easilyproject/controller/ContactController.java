package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.ClientBS;
import fr.esimed.easilyproject.BS.MailBS;
import fr.esimed.easilyproject.entity.Client;
import fr.esimed.easilyproject.entity.DTO.Messages;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
public class ContactController {

    private static String HOST = "smtp.esimed.fr";

    @Inject
    MailBS mailBS;

    @RequestMapping({"/contact"})
    public String contactForm(Model model) {

        //model.addAttribute("messages", mailBS.findMessages());

        return Views.CONTACT_FORM.toString();
    }


    @RequestMapping(value = "/contact/send", method = RequestMethod.POST)
    public String send(@Valid @RequestParam("to") String to, @RequestParam("from") String from, @RequestParam("title") String title, @RequestParam("message") String msg) {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", HOST);
        properties.put("mail.smtp.auth", "false");

        try {
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setText(msg);

            mailBS.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Views.CONTACT_FORM.toString();
    }


}