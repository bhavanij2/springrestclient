package com.example;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Greeting {

    private long id;
    private String content;
}
