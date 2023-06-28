package org.example;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGenerator {
    public static void generateDatabase() {
        //Array containing different database sizes
        int[] databaseSizes = {100, 1000, 10000};

        //Number of queries to execute
        int numQueries = 10;

        // Loop over each database size
        for (int databaseSize : databaseSizes) {
            //Create a list to store student objects
            List<Student> students = new ArrayList<>();

            //Generate student objects with unique IDs, names, and emails
            for (int i = 1; i <= databaseSize; i++) {
                String studentId = "S" + i;
                String name = "Student" + i;
                String email = "student" + i + "@example.com";

                Student student = new Student(studentId, name, email);
                students.add(student);
            }

            //Create a list to store class objects
            List<Classes> classes = new ArrayList<>();

            //Generate class objects with unique IDs, names, and descriptions
            for (int i = 1; i <= databaseSize / 10; i++) {
                String classId = "C" + i;
                String className = "Class" + i;
                String description = "Description" + i;

                Classes classObj = new Classes(classId, className, description);
                classes.add(classObj);
            }

            //Create a list to store teacher objects
            List<Teacher> teachers = new ArrayList<>();

            //Generate teacher objects with unique IDs, names, and emails
            for (int i = 1; i <= databaseSize / 100; i++) {
                String teacherId = "T" + i;
                String name = "Teacher" + i;
                String email = "teacher" + i + "@example.com";

                Teacher teacher = new Teacher(teacherId, name, email);
                teachers.add(teacher);
            }

            //Assign random students to each class
            //Set a random teacher for each class
            for (Classes classObj : classes) {
                for (Student student : students) {
                    if (Math.random() < 0.5) {
                        classObj.addStudent(student);
                    }
                }

                int randomTeacherIndex = (int) (Math.random() * teachers.size());
                Teacher teacher = teachers.get(randomTeacherIndex);
                classObj.setTeacher(teacher);
            }

            //Execute queries and calculate average execution time
            long totalExecutionTime = 0;
            for (int i = 0; i < numQueries; i++) {
                long startTime = System.nanoTime();
                //Simulate query execution
                long endTime = System.nanoTime();

                long executionTime = endTime - startTime;
                totalExecutionTime += executionTime;
            }

            long averageExecutionTime = totalExecutionTime / numQueries;

            //Print database size and average query execution time
            System.out.println("Database size: " + databaseSize);
            System.out.println("Average query execution time: " + averageExecutionTime + " nanoseconds");
            System.out.println();
        }
    }
}
