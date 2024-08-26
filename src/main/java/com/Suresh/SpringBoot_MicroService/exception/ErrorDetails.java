package com.Suresh.SpringBoot_MicroService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorCode;

}
