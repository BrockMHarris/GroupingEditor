import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RedoAction implements ActionListener {
    Editor app;
    JButton redo;
    public RedoAction(Editor Editor, JButton redo){
        app = Editor;
        this.redo = redo;
    }
    public void actionPerformed(ActionEvent e){
        app.redo();
        //redo.setEnabled(app.canRedo());
    }
}