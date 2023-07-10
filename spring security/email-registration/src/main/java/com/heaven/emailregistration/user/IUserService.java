package com.heaven.emailregistration.user;

import com.heaven.emailregistration.registration.RegistrationRequest;
import com.heaven.emailregistration.registration.token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    public void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);
}
