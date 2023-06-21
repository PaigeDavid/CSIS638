package org.example;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGenerator {
    public static void generateDatabase() {
        int numStudents = 1000;
        int numClasses = 100;
        int numTeachers = 10;

        List<Student> students = new ArrayList<>();
        List<Classes> classes = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();

        // Generate students
        for (int i = 1; i <= numStudents; i++) {
            String studentId = "S" + i;
            String name = "Student" + i;
            String email = "student" + i + "@example.com";

            Student student = new Student(studentId, name, email);
            students.add(student);
        }

        // Generate classes
        for (int i = 1; i <= numClasses; i++) {
            String classId = "C" + i;
            String className = "Class" + i;
            String description = "Description" + i;

            Classes classObj = new Classes(classId, className, description);
            classes.add(classObj);
        }

        // Generate teachers
        for (int i = 1; i <= numTeachers; i++) {
            String teacherId = "T" + i;
            String name = "Teacher" + i;
            String email = "teacher" + i + "@example.com";

            Teacher teacher = new Teacher(teacherId, name, email);
            teachers.add(teacher);
        }

        // Assign students and teachers to classes
        for (Classes classObj : classes) {
            // Assign students randomly
            for (Student student : students) {
                if (Math.random() < 0.5) {
                    classObj.addStudent(student);
                }
            }

            // Assign a teacher randomly
            int randomTeacherIndex = (int) (Math.random() * teachers.size());
            Teacher teacher = teachers.get(randomTeacherIndex);
            classObj.setTeacher(teacher);
        }

        // Print the generated data
        System.out.println("Students:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\nTeachers:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

        System.out.println("\nClasses:");
        for (Classes classObj : classes) {
            System.out.println(classObj);
        }
    }
}
