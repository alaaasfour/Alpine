package com.alpine.domain.security;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
    private final String authority;
    // Constructor to set the authority
    public Authority(String authority) {
        this.authority = authority;
    }

    // Method to retrieve the authority
    @Override
    public String getAuthority() {
        return authority;
    }
}

