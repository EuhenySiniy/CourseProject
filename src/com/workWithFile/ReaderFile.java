package com.workWithFile;

import java.io.*;
import java.util.ArrayList;

public class ReaderFile {
    private String separator = File.separator;
    private String path;

    String fileContent;
    String[] temp;
    BufferedReader br;

    public ReaderFile(){
    }

    public ReaderFile(String path) {
        this.path = path;
    }

    public ArrayList<String> registrationUser() {
        ArrayList<String> registerUser = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(path));
            while ((fileContent = br.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].toString().equals("REGISTRATION")) {
                    for(int i = 1; i < temp.length; i++) {
                        registerUser.add(temp[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registerUser;
    }

    public ArrayList<String> addingAddress() {
        ArrayList<String> addAdress = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(path));
            while ((fileContent = br.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].toString().equals("ADDADDRESS")) {
                    for(int i = 1; i < temp.length; i++) {
                        addAdress.add(temp[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addAdress;
    }

    public ArrayList<String> addingSample() {
        ArrayList<String> addSample = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(path));
            while ((fileContent = br.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].toString().equals("ADDSAMPLE")) {
                    for(int i = 1; i < temp.length; i++) {
                        addSample.add(temp[i]);
                    }
                    System.out.println(addSample);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addSample;
    }

}
