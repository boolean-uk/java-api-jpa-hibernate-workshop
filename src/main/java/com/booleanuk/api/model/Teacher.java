package com.booleanuk.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="teachers")
public class Teacher {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="location")
    private String location;
    @Column(name="subject")
    private String subject;
    @Column(name="email")
    private String email;

    public Teacher() {}

    public Teacher(int id, String name, String location, String subject, String email) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.subject = subject;
        this.email = email;
    }

    public void update(String name, String location, String subject, String email) {
        this.name = name;
        this.location = location;
        this.subject = subject;
        this.email = email;
    }

    public void update(Teacher teacher) {
        teacher.update(name, location, subject, email);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher:" +
                "\n\tid: " + id +
                "\n\tname: " + name +
                "\n\tlocation: " + location +
                "\n\tsubject: " + subject +
                "\n\temail: " + email;
    }
}
