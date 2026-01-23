package com.rahulmondal.portfolio.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserUpdatesRequestDTO {
    
    private String subject;
    private String text;
}
