package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest implements Serializable {

    private static final long serialVersionUID = 3333333L;

    private String password;
    private String newPassword;

}
