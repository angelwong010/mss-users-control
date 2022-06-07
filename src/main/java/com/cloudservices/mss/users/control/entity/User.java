package com.cloudservices.mss.users.control.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mss_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "role_id")
    private Integer role;

    @Column(name="name")
    private String name;

    @Column(name="first_lastname")
    private String fistLastname;

    @Column(name="second_lastname")
    private String secondLastname;

    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="rfc")
    private String rfc;
}
