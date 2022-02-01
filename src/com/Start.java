package com;

import com.adding.RegistrationUser;
import com.entities.User;
import com.work_with_file.ReaderFile;

import java.io.FileNotFoundException;

public class Start {
    public static void main(String[] args) {
        ReaderFile readerFile = new ReaderFile();
        readerFile.readingFile();
    }
}
