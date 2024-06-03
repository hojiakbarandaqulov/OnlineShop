package org.example.util;

import org.example.dto.JwtDTO;
import org.example.enums.ProfileRole;
import org.example.exp.AppForbiddenException;

public class SecurityUtil {

    public static JwtDTO getJwtDTO(String token) {
        String jwt = token.substring(7); // Bearer eyJhb
        JwtDTO dto = JwtUtil.decode(jwt);
        return dto;
    }

    public static JwtDTO getJwtDTO(String token, ProfileRole requiredRole) {
        JwtDTO dto = getJwtDTO(token);
        if(!dto.getRole().equals(requiredRole)){
            throw new AppForbiddenException("Method not allowed");
        }
        return dto;
    }
}