package com.netflix.netflixapi.jwtutils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestModel {
    private String username; 
    private String password;
}
