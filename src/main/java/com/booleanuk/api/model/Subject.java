package com.booleanuk.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "level", nullable = false)
    private String level;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    //endregion

    public Subject() {

    }
    public Subject(String title, String level) {
        super();
        this.title = title;
        this.level = level;
    }
}
