package com.cloudservices.mss.users.control.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NewUSerRequest {

    @NotNull
    private Integer role;

    @NotBlank
    private String name;

    @NotBlank
    private String fistLastname;

    @NotBlank
    private String secondLastname;

    private String phone;

    private String address;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String rfc;
}
