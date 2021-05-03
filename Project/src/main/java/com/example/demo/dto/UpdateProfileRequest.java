package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest implements Serializable {

    private static final long serialVersionUID = 222222L;

    private String email;
    private String fullName;

}
