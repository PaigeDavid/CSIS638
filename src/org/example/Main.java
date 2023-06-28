package org.example;

import static org.example.DatabaseGenerator.generateDatabase;

public class Main {
    public static void main(String[] args) {
        //Generate the database
        generateDatabase();

        //Generate the Alloy model
        String alloyModel = AlloyModelGenerator.generateAlloyModel();

        //Write the Alloy model to a file
        AlloyModelGenerator.writeAlloyModelToFile(alloyModel, "database_model.als");
    }
}
