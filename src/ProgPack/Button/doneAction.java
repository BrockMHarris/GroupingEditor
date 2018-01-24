package ProgPack.Button;

import ProgPack.Timer;
import ProgPack.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        timer.end();
        app.setEnabled(false);
    }
}