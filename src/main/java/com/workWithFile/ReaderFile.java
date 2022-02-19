package com.workWithFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderFile {
    private final String path;
    private String fileContent;
    private String[] temp;

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
                    userInfo.addAll(Arrays.asList(temp).subList(1, temp.length));
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
                    addressInfo.addAll(Arrays.asList(temp).subList(1, temp.length));
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

    public List<String> getSampleInfo() {
        BufferedReader reader = null;
        List<String> sampleInfo = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            while ((fileContent = reader.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].equals("ADDSAMPLE")) {
                    sampleInfo.addAll(Arrays.asList(temp).subList(1, temp.length));
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
        return sampleInfo;
    }

    public List<String> getPaymentInfo() {
        BufferedReader reader = null;
        List<String> paymentInfo = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            while((fileContent = reader.readLine()) != null) {
                temp = fileContent.split(",");
                if (temp[0].equals("PAY")) {
                    paymentInfo.addAll(Arrays.asList(temp).subList(1, temp.length));
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
        return paymentInfo;
    }
}
