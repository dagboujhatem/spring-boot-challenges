package com.fivepoints.spring.payload.requests;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String firstName;

    // @NotBlank or @NotEmpty annotation must be applied on any String field only.
    @NotBlank
    @Size(min = 3, max = 255)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    // Use annotation @Min to validate long/int type field.
    @Min(18)
    private int age;

    private Set<String> roles;
}
