package com.cloudservices.mss.users.control.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NewRoleRequest {

    @NotBlank
    private String name;
}
