package dev.himbra.ecommercebackend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
