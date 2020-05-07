package com.jaga.usermanagement.view;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginSuccess {
    private String token;
}
