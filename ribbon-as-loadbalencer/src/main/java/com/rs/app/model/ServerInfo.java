package com.rs.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo implements Serializable {
    private static final long serialVersionUID = 6836567166170732595L;
    private String host;
    private int port;
}
