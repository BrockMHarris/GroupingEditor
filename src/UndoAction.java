import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by harrisb on 7/10/17.
 */
class UndoAction implements ActionListener{
    Editor app;
    JButton undo;
    public UndoAction(Editor Editor, JButton undo){
        app = Editor;
        this.undo = undo;
    }
    public void actionPerformed(ActionEvent e){
        app.undo();
        //undo.setEnabled(app.canUndo());
    }
}
