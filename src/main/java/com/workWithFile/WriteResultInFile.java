package com.workWithFile;

import java.io.*;

public class WriteResultInFile {

    public void writeResultInFile(String s) {
        File file = new File("C:\\Users\\Евгений\\IdeaProjects\\CourseProject\\src\\main\\resources\\result.txt");
        try (Writer writer = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
