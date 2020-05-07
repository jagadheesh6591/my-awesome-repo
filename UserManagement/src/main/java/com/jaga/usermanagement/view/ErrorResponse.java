package com.jaga.usermanagement.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    String errorCode;
    String errorMessage;
}
