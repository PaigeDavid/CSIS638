package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AlloyModelGenerator {

    public static void main(String[] args) {
        //Generate the Alloy model
        String alloyModel = generateAlloyModel();

        //Write the Alloy model to a file
        writeAlloyModelToFile(alloyModel, "database_model.als");
    }

    /**
     * Generates the Alloy model for the database.
     *
     * @return the generated Alloy model as a String
     */
    static String generateAlloyModel() {
        StringBuilder alloyModel = new StringBuilder();

        //Define the Student signature
        alloyModel.append("sig Student {\n");
        alloyModel.append("    id: lone String,\n");
        alloyModel.append("    name: String,\n");
        alloyModel.append("    email: String,\n");
        alloyModel.append("    classes: set Classes\n");
        alloyModel.append("}\n\n");

        //Define the Teacher signature
        alloyModel.append("sig Teacher {\n");
        alloyModel.append("    id: lone String,\n");
        alloyModel.append("    name: String,\n");
        alloyModel.append("    email: String\n");
        alloyModel.append("}\n\n");

        //Define the Classes signature
        alloyModel.append("sig Classes {\n");
        alloyModel.append("    id: lone String,\n");
        alloyModel.append("    title: String,\n");
        alloyModel.append("    description: String,\n");
        alloyModel.append("    students: set Student,\n");
        alloyModel.append("    teacher: lone Teacher\n");
        alloyModel.append("}\n\n");

        //Define the fact block
        alloyModel.append("fact {\n");
        alloyModel.append("    // Constraints\n");
        alloyModel.append("    all s: Student | s.id != none\n");
        alloyModel.append("    all t: Teacher | t.id != none\n");
        alloyModel.append("    all c: Classes | c.id != none && c.title != none && c.description != none\n\n");

        alloyModel.append("    // Relationships\n");
        alloyModel.append("    all s: Student, c: Classes | s in c.students <=> c in s.classes\n");
        alloyModel.append("    all c: Classes | c.teacher in Teacher\n");
        alloyModel.append("    all s1, s2: Student, c: Classes | s1 != s2 && s1 in c.students && s2 in c.students => s1.classes != s2.classes\n");
        alloyModel.append("}\n\n");

        //Define the desiredProperty assertion
        alloyModel.append("assert desiredProperty {\n");
        alloyModel.append("    all c: Classes | #c.students >= 2\n");
        alloyModel.append("}\n\n");

        //Define the eachStudentEnrolled assertion
        alloyModel.append("assert eachStudentEnrolled {\n");
        alloyModel.append("    all s: Student | some c: Classes | s in c.students\n");
        alloyModel.append("}\n\n");

        //Define the uniqueTeacher assertion
        alloyModel.append("assert uniqueTeacher {\n");
        alloyModel.append("    all c: Classes | lone c.teacher\n");
        alloyModel.append("}\n\n");

        //Define the noDuplicateStudents assertion
        alloyModel.append("assert noDuplicateStudents {\n");
        alloyModel.append("    all c: Classes | no disj s1, s2: c.students | s1 = s2\n");
        alloyModel.append("}\n");

        return alloyModel.toString();
    }

    /**
     * Writes the Alloy model to a file.
     *
     * @param alloyModel the Alloy model to write
     * @param filename   the name of the file to write to
     */
    static void writeAlloyModelToFile(String alloyModel, String filename) {
        File file = new File(filename);

        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filename);
                } else {
                    System.out.println("Failed to create file: " + filename);
                    return;
                }
            }

            FileWriter writer = new FileWriter(file);
            writer.write(alloyModel);
            writer.close();

            System.out.println("Alloy model saved to: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the Alloy model to a file.");
            e.printStackTrace();
        }
    }
}
