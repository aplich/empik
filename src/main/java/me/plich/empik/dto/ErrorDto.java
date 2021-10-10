package me.plich.empik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDto {
    private String field;
    private String value;
    private String message;
}
