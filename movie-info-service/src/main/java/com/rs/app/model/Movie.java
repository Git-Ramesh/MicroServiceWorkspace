package com.rs.app.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie implements Serializable {
    private long id;
    private String name;
    private String actor;

}
