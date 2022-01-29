package com.adding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class RegistrationUser {
    private String [] regInfo = new String[3];
    private String separator = File.separator;
    private String path = "C:"
            + separator + "Users"
            + separator + "Евгений"
            + separator + "Desktop"
            + separator + "newUser.txt";

    File file = new File(path);
    Scanner scan = new Scanner(file);

    public void checkResult() {
        String line = scan.nextLine();
        String[] firstLine = line.split(",");
        System.out.println(Arrays.toString(firstLine));
    }


    public RegistrationUser() throws FileNotFoundException {
    }
}
