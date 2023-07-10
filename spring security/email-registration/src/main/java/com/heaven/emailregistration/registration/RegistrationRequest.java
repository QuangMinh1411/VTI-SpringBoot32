package com.heaven.emailregistration.registration;

public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String role) {
}
