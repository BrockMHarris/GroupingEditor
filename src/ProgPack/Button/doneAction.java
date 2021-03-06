package ProgPack.Button;

import ProgPack.MyLogger;
import ProgPack.Timer;
import ProgPack.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * listener for the Redo button in the window
 */
public class doneAction implements ActionListener {
    Editor app;
    Timer timer;

    /**
     * Constructor
     * @param Editor The JTextPane that needs to be edited
     */
    public doneAction(Editor Editor, Timer timer){
        app = Editor;
        this.timer = timer;
    }

    /**
     * Calls redo when action is preformed
     * @param e action event on the JButton
     */
    public void actionPerformed(ActionEvent e){
        int dialog = JOptionPane.showConfirmDialog(null, "Are you sure you are done",
                                                    "Done", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(dialog == JOptionPane.YES_OPTION){
            timer.end();
            app.setEnabled(false);
            MyLogger.close();

            Robot rob = null;
            try {
                rob = new Robot();
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
            rob.keyPress(KeyEvent.VK_SHIFT);
            rob.keyPress(KeyEvent.VK_CONTROL);
            rob.keyPress(KeyEvent.VK_F2);

            rob.keyRelease(KeyEvent.VK_SHIFT);
            rob.keyRelease(KeyEvent.VK_CONTROL);
            rob.keyRelease(KeyEvent.VK_F2);
        }
    }
}