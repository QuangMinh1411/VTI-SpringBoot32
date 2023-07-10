package com.heaven.libraryjwt.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationRequest {
    private String userName;
    private String password;
}
