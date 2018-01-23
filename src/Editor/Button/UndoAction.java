package Editor.Button;

import Editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by harrisb on 7/10/17.
 * listener for the Undo button in the window
 */
public class UndoAction implements ActionListener{
    Editor app;
    JButton undo;

    /**
     * Constructor
     * @param Editor The JTextPane that needs to be edited
     * @param undo The button that is being listened to.
     */
    public UndoAction(Editor Editor, JButton undo){
        app = Editor;
        this.undo = undo;
    }

    /**
     * Calls undo when action is preformed
     * @param e action event on the JButton
     */
    public void actionPerformed(ActionEvent e){
        app.undo();
        //undo.setEnabled(app.canUndo());
    }
}
