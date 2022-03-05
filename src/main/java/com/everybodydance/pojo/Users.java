package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class Users {
    private int id;
    private String uname;
    private String pwd;
    private String token;
}
