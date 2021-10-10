package me.plich.empik.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
class Error  {
    private LocalDateTime timestamp;
    private String message;
    private int statusCode;
}
