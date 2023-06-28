package org.example.test;

import org.example.Student;
import org.example.Classes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryTests {

    //Data for classes and students
    private static List<Classes> classesData = new ArrayList<>();
    private static List<Student> studentsData = new ArrayList<>();

    static {
        //Initialize classes data
        classesData.add(new Classes("C001", "Math", "Math 101"));
        classesData.add(new Classes("C002", "Science", "Science 101"));

        //Initialize students data
        studentsData.add(new Student("S001", "John Doe", "doej@example.com"));
        studentsData.add(new Student("S002", "Jane Smith", "smithj@example.com"));
    }

    @Test
    @DisplayName("Get students in class")
    public void getStudentsInClassTest() {
        //Specify the class ID
        String classId = "C001";

        //Get the class by ID
        Classes classes = getClassesById(classId);

        //Check if the class exists
        if (classes != null) {
            //Get the students in the class
            List<Student> students = getStudentsInClass(classes);
            System.out.println(students);
        } else {
            System.out.println("Class not found");
        }
    }

    @Test
    @DisplayName("Enroll student in class")
    public void enrollStudentInClassTest() {
        //Specify the student ID and class ID
        String studentId = "S001";
        String classId = "C001";

        //Get the student and class by their IDs
        Student student = getStudentById(studentId);
        Classes classes = getClassesById(classId);

        //Check if the student and class exist
        if (student != null && classes != null) {
            //Enroll the student in the class
            classes.getStudents().add(student);
            student.getClasses().add(classes);
            System.out.println("Student enrolled in class");
        } else {
            System.out.println("Student or class not found");
        }
    }

    //Get a class by its ID
    private Classes getClassesById(String classId) {
        for (Classes classes : classesData) {
            if (classes.getId().equals(classId)) {
                return classes;
            }
        }
        return null;
    }

    //Get a student by their ID
    private Student getStudentById(String studentId) {
        for (Student student : studentsData) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    //Get the students associated with a class
    private List<Student> getStudentsInClass(Classes classes) {
        List<Student> studentsInClass = new ArrayList<>();
        for (Student student : studentsData) {
            if (student.getClasses().contains(classes)) {
                studentsInClass.add(student);
            }
        }
        return studentsInClass;
    }
}
