package com.rahulmondal.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserLoginResponseDTO {

    private String jwt;
    private long id;
    private String firstName;
    private String secondName;
}
