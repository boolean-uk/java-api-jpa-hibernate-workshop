package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "subject")
    private String subject;

    @Column(name = "email")
    private String email;

    public Teacher(String name, String location, String subject, String email) {
        this.name = name;
        this.location = location;
        this.subject = subject;
        this.email = email;
    }
}
