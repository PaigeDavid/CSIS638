package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id;

    private String name;
    private String email;

    //Represents a many-to-many relationship between students and classes
    @ManyToMany
    @JoinTable(name = "registrations",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<Classes> classes;

    //Default constructor
    public Student() {
        this.classes = new ArrayList<>();
    }

    //Constructor with parameters
    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.classes = new ArrayList<>();
    }

    //Getter and setter methods for the classes field
    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    //Provide a string representation of the object
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //Getter method for the student's ID
    public String getId() {
        return id;
    }
}
