package com.workWithFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {
    private String path;
    private String fileContent;
    private String[] temp;

    public ReaderFile(){
    }

    public ReaderFile(String path) {
        this.path = path;
    }

    public List<String> getUsersInfo() {
        BufferedReader reader = null;
        List<String> userInfo = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            while ((fileContent = reader.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].equals("REGISTRATION")) {
                    for(int i = 1; i < temp.length; i++) {
                        userInfo.add(temp[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return userInfo;
    }

    public List<String> getAddressInfo() {
        BufferedReader reader = null;
        List<String> addressInfo = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            while ((fileContent = reader.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].equals("ADDADDRESS")) {
                    for(int i = 1; i < temp.length; i++) {
                        addressInfo.add(temp[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return addressInfo;
    }

    public ArrayList<String> getSampleInfo() {
        BufferedReader reader = null;
        ArrayList<String> addSample = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            while ((fileContent = reader.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].equals("ADDSAMPLE")) {
                    for(int i = 1; i < temp.length; i++) {
                        addSample.add(temp[i]);
                    }
                    System.out.println(addSample);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return addSample;
    }

}
