package util;

import java.io.File;

import constants.ExpenseTrackerConst;

public class Configuration {
    public Configuration() {

        String fileLocation = ExpenseTrackerConst.FILE_LOCATION;

        File f = new File(fileLocation);
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
