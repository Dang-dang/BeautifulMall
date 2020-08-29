package com.beautiful.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    private Long id;
    private String username;
    private String password;

}
