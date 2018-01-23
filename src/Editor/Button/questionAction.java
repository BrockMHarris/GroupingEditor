package Editor.Button;

import Editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class questionAction implements ActionListener{

    Editor app;
    JButton redo;

    /**
     * Constructor
     *
     * @param Editor The JTextPane that needs to be edited
     * @param  question   The button that is being listened to.
     */
    public questionAction(Editor Editor, JButton question) {
        app = Editor;
        this.redo = redo;
    }

    /**
     * Calls redo when action is preformed
     *
     * @param e action event on the JButton
     */
    public void actionPerformed(ActionEvent e) {
        app.setEditable(false);
    }
}
