package com.example.mongock.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;
    @Field("nome")
    private String nome;
    @Field("login")
    private String login;

    @Field("password")
    public String password;

    public User(String nome, String login) {
        this.nome = nome;
        this.login = login;
    }
}
