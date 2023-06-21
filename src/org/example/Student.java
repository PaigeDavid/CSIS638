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

    @ManyToMany
    @JoinTable(name = "registrations",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<Classes> classes;

    public Student() {
        this.classes = new ArrayList<>();
    }

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.classes = new ArrayList<>();
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }
}
