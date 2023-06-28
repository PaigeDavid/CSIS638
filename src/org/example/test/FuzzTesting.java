package org.example.test;

import org.example.Student;
import org.example.Classes;
import org.example.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FuzzTesting {
    public static void main(String[] args) {
        int numStudents = 1000;
        int numClasses = 100;
        int numTeachers = 10;

        List<Student> students = new ArrayList<>();
        List<Classes> classes = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();

        //Generate student objects with fuzzed names and emails
        for (int i = 1; i <= numStudents; i++) {
            String studentId = "S" + i;
            String name = fuzzName("Student" + i);
            String email = fuzzEmail("student" + i + "@example.com");

            Student student = new Student(studentId, name, email);
            students.add(student);
        }

        //Generate class objects with fuzzed names and descriptions
        for (int i = 1; i <= numClasses; i++) {
            String classId = "C" + i;
            String className = fuzzString("Class" + i);
            String description = fuzzString("Description" + i);

            Classes classObj = new Classes(classId, className, description);
            classes.add(classObj);
        }

        //Generate teacher objects with fuzzed names and emails
        for (int i = 1; i <= numTeachers; i++) {
            String teacherId = "T" + i;
            String name = fuzzName("Teacher" + i);
            String email = fuzzEmail("teacher" + i + "@example.com");

            Teacher teacher = new Teacher(teacherId, name, email);
            teachers.add(teacher);
        }

        Random random = new Random();

        //Assign random students to each class
        //Set a random teacher for each class
        for (Classes classObj : classes) {
            for (Student student : students) {
                if (random.nextDouble() < 0.5) {
                    classObj.addStudent(student);
                }
            }

            int randomTeacherIndex = random.nextInt(teachers.size());
            Teacher teacher = teachers.get(randomTeacherIndex);
            classObj.setTeacher(teacher);
        }

        //Print generated objects for debugging purposes
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

        //Run fuzz testing
        runFuzzTesting(students, classes, teachers);
    }

    private static String fuzzName(String originalName) {
        return originalName + " [fuzzed]";
    }

    private static String fuzzEmail(String originalEmail) {
        return originalEmail.replace("@", "_at_");
    }

    private static String fuzzString(String originalString) {
        return originalString + "_fuzzed";
    }

    public static void runFuzzTesting(List<Student> students, List<Classes> classes, List<Teacher> teachers) {
        //Check if the student count matches the expected count
        boolean studentCountPassed = checkStudentCount(students);
        if (!studentCountPassed) {
            System.out.println("Fuzz testing failed: Incorrect student count");
        } else {
            System.out.println("Fuzz testing passed: Correct student count");
        }

        //Check if the class count matches the expected count
        boolean classCountPassed = checkClassCount(classes);
        if (!classCountPassed) {
            System.out.println("Fuzz testing failed: Incorrect class count");
        } else {
            System.out.println("Fuzz testing passed: Correct class count");
        }

        //Check if the teacher count matches the expected count
        boolean teacherCountPassed = checkTeacherCount(teachers);
        if (!teacherCountPassed) {
            System.out.println("Fuzz testing failed: Incorrect teacher count");
        } else {
            System.out.println("Fuzz testing passed: Correct teacher count");
        }
    }

    private static boolean checkStudentCount(List<Student> students) {
        return students.size() == 1000;
    }

    private static boolean checkClassCount(List<Classes> classes) {
        return classes.size() == 100;
    }

    private static boolean checkTeacherCount(List<Teacher> teachers) {
        return teachers.size() == 10;
    }
}
