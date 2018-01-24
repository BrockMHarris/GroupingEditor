package ProgPack;

import javax.swing.*;

/**
 * Created by harrisb on 7/25/17.
 * Thread to watch if the buttons are pressible independent of main proccess, so that the buttons become inactive as soon
 * as they can no longer be used
 */
public class MyBackroundMethod extends Thread
{
    private JButton undo;
    private JButton redo;
    private Editor app;

    /**
     * constructor
     * initializes all the buttons that are being watched
     * @param Undo Undo button
     * @param Redo Redo button
     * @param app JTextPane that is effected
     */
    MyBackroundMethod(JButton Undo, JButton Redo, Editor app){
        undo = Undo;
        redo = Redo;
        this.app = app;
    }

    /**
     * checks if the button can be pressed every 100 milliseconds. if not it disables the button, othwise it enables it.
     */
    public void run()
    {
        while (true)
        {
            undo.setEnabled(app.canUndo());
            redo.setEnabled(app.canRedo());
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
