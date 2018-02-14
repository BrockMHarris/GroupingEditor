package ProgPack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Logger to log all the important information from the edits that were made
 */
public class MyLogger {

    //private String filename = "~\\Test.txt";
    private static BufferedWriter bw;
    private static FileWriter fw;

    /**
     * Creates a new to hold all the log files
     */
    public static void setup(String fileLoc) {
        try {
            fw = new FileWriter("Log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);
    }

    /**
     * Writes a message to the log file
     * @param Message message to be added to the file
     */
    public static void write(String Message){

        try {
            bw.write(Message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the log file
     */
    public static void close(){
        try {
            bw.close();
            fw.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

}