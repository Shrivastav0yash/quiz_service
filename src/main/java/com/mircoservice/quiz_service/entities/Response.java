package com.mircoservice.quiz_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    private Integer id;
    private String response;
}
