package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest implements Serializable {

    private static final long serialVersionUID = 444444L;

    private String id;
    private String title;
    private String bg_color;
    private String bg_image;

}
