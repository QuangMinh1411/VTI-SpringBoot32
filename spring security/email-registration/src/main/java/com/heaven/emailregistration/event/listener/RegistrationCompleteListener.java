package com.heaven.emailregistration.event.listener;

import com.heaven.emailregistration.event.RegistrationCompleteEvent;
import com.heaven.emailregistration.user.User;
import com.heaven.emailregistration.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteListener implements
        ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;
    private final JavaMailSender  mailSender;
    private User theUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        theUser = event.getUser();
        String verificationToken = UUID.randomUUID().toString();

        userService.saveUserVerificationToken(theUser,verificationToken);

        String url = event.getApplicationUrl()
                +"/api/v1/register/verifyEmail?token="+verificationToken;
        try {
            sendVerificationEmail(url);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration :"+url);

    }

    public void sendVerificationEmail(String url) throws MessagingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p>Hi, " + theUser.getFirstName()+", </p>"
                + "<p>Thank you for registration with us"+
                "Please , follow the link below to complete your registration.</p>"
                + "<a href=\""+url+"\">Verify your email to activate your account</a>";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("quangbookstore1@gmail.com");
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent,true);
        mailSender.send(message);
    }
}
