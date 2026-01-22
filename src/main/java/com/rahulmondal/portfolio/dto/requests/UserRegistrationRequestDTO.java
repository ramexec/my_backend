package com.rahulmondal.portfolio.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDTO {

    private String firstName;
    private String secondName;
    private String username;
    private String password;
    private String email;
    
}
