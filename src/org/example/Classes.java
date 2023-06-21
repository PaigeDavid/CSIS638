package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Classes {
    @Id
    private String id;

    private String title;
    private String description;

    @ManyToMany(mappedBy = "classes")
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    public Classes(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getClasses().add(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
