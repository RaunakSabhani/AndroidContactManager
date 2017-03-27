package com.example.hci.hciassignment.io;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by root on 25/3/17.
 */

public class FileWriter {

    private String fileName;
    private String filePath;
    private FileOutputStream outputStream;
    private File file;
    private Context mContext;

    public FileWriter(String filePath, String fileName, Context context) {
        this.filePath = filePath;
        this.fileName = fileName;
        mContext = context;
    }

    public boolean openFile() {
        if(outputStream != null) return false;
        file = new File(filePath);
        try {
            outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean writeLine(String line) {
        if(outputStream == null) return false;

        try {
            outputStream.write(line.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean closeFile() {
        if(outputStream == null) return false;
        try {
            outputStream.close();
            outputStream = null;
            file = null;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
