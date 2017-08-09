import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harrisb on 8/8/17.
 */
public class fileReader
{
    private String fulltxt = "";
    private ArrayList<String> info;
    public fileReader()
    {
        info = new ArrayList<String>();
        try {
            Reader reader = new FileReader("/home/harrisb/Desktop/Java_project/test.txt");
            try{
                int data = reader.read();
                while(data != -1){
                    char dataChar = (char) data;
                    data = reader.read();
                    fulltxt += dataChar;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(fulltxt);
        while (m.find()) {
            info.add(m.group(1));
        }


    }

    String getName(){
        return info.get(0);
    }
    String getTrialNumber(){
        return info.get(1);
    }
    String getRules(){
        return info.get(2);
    }
}