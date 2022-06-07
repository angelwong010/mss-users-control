package com.cloudservices.mss.users.control.dto;

import lombok.Data;


@Data
public class UserDto {

    private Integer id;
    private Integer role;
    private String name;
    private String fistLastname;
    private String secondLastname;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String rfc;
}
