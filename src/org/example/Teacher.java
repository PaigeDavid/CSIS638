package org.example;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    private String id;

    private String name;
    private String email;

    //Constructor with parameters
    public Teacher(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //Provide a string representation of the object
    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
