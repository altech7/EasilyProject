package fr.esimed.easilyproject.BS;

import fr.esimed.easilyproject.DAO.IDAO;
import fr.esimed.easilyproject.entity.Client;
import fr.esimed.easilyproject.entity.DTO.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
@Scope("singleton")
@Transactional
public class MailBS implements IBusinessService {

    private static String HOST = "smtp.esimed.fr";
    private static String USER = "aaltmayer@esimed.fr";
    private static String PASSWORD = "ywOQ6Nf8";

    public void send(MimeMessage message) {
        try {
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public List<Messages> findMessages() {
        final List<Messages> messages = new ArrayList<Messages>();
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);

        try {
            Store store = session.getStore("pop3");
            store.connect(HOST, USER, PASSWORD);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);

            // get the list of inbox messages
            Message[] messagesInbox = inbox.getMessages();

            if (messagesInbox.length == 0) System.out.println("No messages found.");
            for (int i = 0; i < messagesInbox.length; i++) {
                // stop after listing ten messages
                if (i > 10) {
                    System.exit(0);
                    inbox.close(true);
                    store.close();
                }

                System.out.println("Message " + (i + 1));
                System.out.println("From : " + messagesInbox[i].getFrom()[0]);
                System.out.println("Subject : " + messagesInbox[i].getFrom()[0]);
                System.out.println("Sent Date : " + messagesInbox[i].getSentDate());
                System.out.println();

                messages.add(new Messages(messagesInbox[i].getFrom()[0].toString()));
            }

            inbox.close(true);
            store.close();

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

        return messages;
    }
}
