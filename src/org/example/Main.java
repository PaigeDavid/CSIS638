package org.example;

import static org.example.DatabaseGenerator.generateDatabase;

public class Main {
    public static void main(String[] args) {
//        Student student1 = new Student("S001", "John Doe", "john@example.com");
//        Student student2 = new Student("S002", "Jane Smith", "jane@example.com");
//
//        Teacher teacher1 = new Teacher("T001", "Mr. Anderson", "anderson@example.com");
//        Teacher teacher2 = new Teacher("T002", "Ms. Johnson", "johnson@example.com");
//
//        Classes class1 = new Classes("C001", "Mathematics", "Introduction to Calculus");
//        Classes class2 = new Classes("C002", "Computer Science", "Object-Oriented Programming");
//
//
//
//        class1.addStudent(student1);
//        class1.addStudent(student2);
//        class2.addStudent(student1);
//
//        class1.setTeacher(teacher1);
//        class2.setTeacher(teacher2);
//
//        System.out.println("Students:");
//        System.out.println(student1);
//        System.out.println(student2);
//
//        System.out.println("\nTeachers:");
//        System.out.println(teacher1);
//        System.out.println(teacher2);
//
//        System.out.println("\nClasses:");
//        System.out.println(class1);
//        System.out.println(class2);

        generateDatabase();

        String alloyModel = AlloyModelGenerator.generateAlloyModel();

        AlloyModelGenerator.writeAlloyModelToFile(alloyModel, "database_model.als");
    }
}
