import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyLogger {

    private static final String FILENAME = "~\\Desktop\\Test.txt";
    private static BufferedWriter bw = null;
    private static FileWriter fw = null;

    public static void setup() {



        try {
            fw = new FileWriter(FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);



    }
    public static void write(String Message){

        try {
            bw.write(Message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void close(){
        try {
            bw.close();
            fw.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

}