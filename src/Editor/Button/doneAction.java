package Editor.Button;

import Editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * listener for the Redo button in the window
 */
public class doneAction implements ActionListener {
    Editor app;
    JButton redo;

    /**
     * Constructor
     * @param Editor The JTextPane that needs to be edited
     * @param done The button that is being listened to.
     */
    public doneAction(Editor Editor, JButton done){
        app = Editor;
        this.redo = redo;
    }

    /**
     * Calls redo when action is preformed
     * @param e action event on the JButton
     */
    public void actionPerformed(ActionEvent e){
        //redo.setEnabled(app.canRedo());
    }
}