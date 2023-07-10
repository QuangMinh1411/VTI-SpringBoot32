package com.heaven.emailregistration.registration;

import com.heaven.emailregistration.event.RegistrationCompleteEvent;
import com.heaven.emailregistration.registration.token.VerificationToken;
import com.heaven.emailregistration.registration.token.VerificationTokenRepository;
import com.heaven.emailregistration.user.User;
import com.heaven.emailregistration.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public ResponseEntity<?> registration (@RequestBody RegistrationRequest request,
                                           final HttpServletRequest re){
        User user = userService.registerUser(request);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(re)));
        return ResponseEntity.ok("Success registration! Check your email to complete");
    }
    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return ResponseEntity.ok("This account has already been verified, please log in");
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return ResponseEntity.ok("Email verified successfully. Now you can log in to your account");
        }
        return ResponseEntity.ok("Invalid verification token");
    }
    public String applicationUrl(HttpServletRequest re) {
        return "http://"+ re.getServerName()+":"+re.getServerPort()+re.getContextPath();
    }


}
