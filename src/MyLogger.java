import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
  
public class MyLogger
{
    private ArrayList<Long> undoTimes;
    private ArrayList<Long> redoTimes;
    private Logger logger;
    void MyLogger()
    {
        logger = Logger.getLogger("MyLog");
        FileHandler fh;
         
        try {
             
            // This block configure the logger with handler and formatter
            fh = new FileHandler("/home/harrisb/Desktop/MyLog.log");
            logger.addHandler(fh);
            //logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        undoTimes = new ArrayList<Long>();
        redoTimes = new ArrayList<Long>();
    }

    void addUndoCount()
    {
        undoTimes.add(System.currentTimeMillis());
    }
    void addRedoCount()
    {
        redoTimes.add(System.currentTimeMillis());
    }
    void writeLog()
    {
        logger.info("Hi How r u?");

    }
}