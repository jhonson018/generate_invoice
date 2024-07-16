package com.practicas.opensource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private String name;
    private String lastName;
    private String identification;
}

