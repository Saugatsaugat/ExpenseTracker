package util;

import java.io.File;

public class Configuration {
    public Configuration() {
        File f = new File("//home//saugat//Desktop//JAVA//ExpenseTracker//expensetracker.csv");
        try {
            if (f.createNewFile()) {
                System.out.println("File Created.");
            } else {
                System.out.println("File already Exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
