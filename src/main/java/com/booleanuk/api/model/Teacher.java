package com.booleanuk.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "email_address", nullable = false)
    private String email;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    public Teacher() {

    }
    public Teacher(String name, String location, String subject, String email) {
        super();
        this.name = name;
        this.location = location;
        this.subject = subject;
        this.email = email;
    }
}
