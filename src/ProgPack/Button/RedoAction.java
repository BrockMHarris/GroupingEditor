package ProgPack.Button;

import ProgPack.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * listener for the Redo button in the window
 */
public class RedoAction implements ActionListener {
    Editor app;
    JButton redo;

    /**
     * Constructor
     * @param Editor The JTextPane that needs to be edited
     * @param redo The button that is being listened to.
     */
    public RedoAction(Editor Editor, JButton redo){
        app = Editor;
        this.redo = redo;
    }

    /**
     * Calls redo when action is preformed
     * @param e action event on the JButton
     */
    public void actionPerformed(ActionEvent e){
        app.redo();
        //redo.setEnabled(app.canRedo());
    }
}