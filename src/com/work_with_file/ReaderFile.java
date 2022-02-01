package com.work_with_file;

import java.io.*;

public class ReaderFile {
    private String separator = File.separator;
    private String path = "C:"
            + separator + "Users"
            + separator + "Евгений"
            + separator + "Desktop"
            + separator + "newUser.txt";

    String fileContent;
    String[] registerUser;
    String[] temp;
    BufferedReader br;

    public String readingFile() {
        try {
            br = new BufferedReader(new FileReader(path));
            while((fileContent = br.readLine()) != null) {
                temp = fileContent.split(",");
                if(temp[0].toString().equals("REGISTRATION")) {
                    registerUser = temp;
                }
                System.out.println(fileContent);
            }
            for(String cont : registerUser) {
                System.out.println(cont);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
