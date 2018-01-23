package Editor.Button;

import Editor.Editor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class questionAction implements ActionListener{

    Editor app;
    JButton redo;
    boolean CurrentEditState;

    /**
     * Constructor
     *
     * @param Editor The JTextPane that needs to be edited
     * @param  question   The button that is being listened to.
     */
    public questionAction(Editor Editor, JToggleButton question) {
        app = Editor;
        this.redo = redo;
        CurrentEditState = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.setEditable(!CurrentEditState);
        System.out.println("hello");
    }
}
