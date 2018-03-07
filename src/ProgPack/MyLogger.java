package ProgPack;

import com.opencsv.CSVWriter;

import java.io.*;

/**
 * Logger to log all the important information from the edits that were made
 */
public class MyLogger {

    //private String filename = "~\\Test.txt";
    private static OutputStreamWriter osw;
    private static FileOutputStream fos;
    private static CSVWriter writer;

    /**
     * Creates a new to hold all the log files
     */
    public static void setup() {
        try {
            fos = new FileOutputStream("Log.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            osw = new OutputStreamWriter(fos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer = new CSVWriter(osw);
        writer.writeNext(new String[]{"timecreated", "editType", "letter", "isHighlighted", "mark",
                "dot", "start", "length","lineNumStartAtOne"});
    }

    /**
     * Writes a message to the log file
     * @param Message message to be added to the file
     */
    public static void write(String[] Message){
            writer.writeNext(Message);
    }


    /**
     * Clears the log file
     */
    public static void clear()
    {
        MyLogger.close();
        MyLogger.setup();

    }
    /**
     * Closes the log file
     */
    public static void close()
    {
        try {
            writer.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}