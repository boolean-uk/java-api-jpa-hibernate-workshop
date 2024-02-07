package com.booleanuk.api.teachers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private String location;

    private String subject;

    private String email;

    public Teacher(String name, String location, String subject, String email) {
        this.name = name;
        this.location = location;
        this.subject = subject;
        this.email = email;
    }
}
