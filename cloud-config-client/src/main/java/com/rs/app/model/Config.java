package com.rs.app.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@RefreshScope
public class Config implements Serializable {
    private static final long serialVersionUID = 1781599504784401974L;
    @Value("${message: No message available}")
    private String message;
    @Value("${author: No Author}")
    private String author;

    public Config() {
        System.out.println("Config: 0-param constr");
    }

    public Config(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        System.out.println("Config: setMessage");
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        System.out.println("Config: setAuthor");
        this.author = author;
    }

    @Override
    public String toString() {
        return "Config{" +
                "message='" + message + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
