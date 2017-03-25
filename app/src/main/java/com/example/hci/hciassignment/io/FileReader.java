package com.example.hci.hciassignment.io;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 25/3/17.
 */

public class FileReader {

    private String fileName;
    private String filePath;
    private FileInputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader reader;
    private Context mContext;

    public FileReader(String filePath, String fileName, Context context) {
        this.filePath = filePath;
        this.fileName = fileName;
        mContext = context;
    }

    public boolean openFile() {
        if(inputStream != null) return false;
        try {
            inputStream = mContext.openFileInput(fileName);
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String readLine() {
        if(inputStream == null) return null;

        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean closeFile() {
        if(inputStream == null) return false;
        try {
            reader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            inputStreamReader = null;
            reader = null;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
