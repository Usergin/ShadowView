package com.shadiz.usergin.shadowview.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OldMan on 31.10.2016.
 */

public class Logger {
    private final static long logFileSize = 5242880;

    /**
     * Write logs in LogCat
     *
     * @param tag
     * @param inLogCat
     */
    public static void doLog(String tag, String inLogCat) {
        Log.d(tag, inLogCat);
    }

    /**
     * Write logs in LogCat and log file
     *
     * @param tag
     * @param inLogCat
     * @param inLogFile
     */
    public static void doLog(String tag, String inLogCat, String inLogFile) {
        Log.d(tag, inLogCat);
        writeLog(tag + " -> " + inLogFile);
    }

    private static void writeLog(String str) {
        File outFile = new File(Environment.getExternalStorageDirectory(), Constants.PATH_TO_LOG_FILE);
        long fileSize = outFile.length();

        if ((fileSize + str.length()) > logFileSize) {
            deleteStr(outFile, str.length());
        }

        try {
            FileWriter wrt = new FileWriter(outFile, true);
            wrt.append(getCurrentTime() + " : " + str + "\n");
            wrt.flush();
            wrt.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the first string if log file size more then need.
     *
     * @param inputFile
     * @param strLength
     */
    private static void deleteStr(File inputFile, int strLength) {
        Log.d("logging", "log file size: " + inputFile.length());
        Log.d("logging", "str.length: " + strLength);

        File tempFile = new File(Environment.getExternalStorageDirectory(), "myTempFile.txt");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
            boolean firstString = true;
            String currentLine;

            while (((currentLine = reader.readLine()) != null)) {
                if (firstString) {
                    firstString = false;
                    continue;
                }
                writer.write(currentLine.trim() + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            firstString = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(inputFile);

        if ((inputFile.length() + strLength) > logFileSize) {
            deleteStr(inputFile, strLength);
        }
    }

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int ms = calendar.get(Calendar.MILLISECOND);
        return String.format("%02d.%02d.%04d %02d:%02d:%02d.%03d", day, month, year, hour, minute, second, ms);
    }

    public static void sendLogFileToServer(Context mContext) {
//        RequestParams params = new RequestParams();
//        String path = Environment.getExternalStorageDirectory() + AppConstants.PATH_TO_LOG_FILE;
//        File logFile = new File(path);
//        if (logFile.exists() && logFile.isFile()) {
//            Map<String, Object> log = new HashMap<String, Object>();
//            Map<String, Object> payloadMap = new HashMap<String, Object>();
//
//            log.put("type", AppConstants.TYPE_LOG_REQUEST);
//            log.put("time", ConvertDate.logTime());
//            log.put("path", path);
//
//            payloadMap.put("file", path);
//            RequestList.sendDataRequest(params, payloadMap, mContext);
//
//   }     }
    }
}
