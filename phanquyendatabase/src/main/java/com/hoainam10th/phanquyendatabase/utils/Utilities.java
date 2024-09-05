package com.hoainam10th.phanquyendatabase.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Utilities {
    public static String getUsername(Authentication auth){
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    public static List<String> getRoles(Authentication auth){
        Collection<? extends GrantedAuthority> roleAuthorities = auth.getAuthorities();

        return roleAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}
