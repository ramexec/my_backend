package com.rahulmondal.portfolio.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatesResponseDTO {

    private LocalDateTime time;
    private String subject;
    private String text;
     
}
