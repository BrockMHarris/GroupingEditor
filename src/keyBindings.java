import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by harrisb on 9/23/17.
 */
public class keyBindings
{
    Editor pane;
    public keyBindings(Editor pane){
        this.pane = pane;
        undoKeyBindings();
        redoKeyBindings();
        displayRules();
    }

    void undoKeyBindings(){
        //this.insertComponent(new JLabel("Text"));
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pane.canUndo()){
                    pane.undo();
                }
            }};
        String keyStrokeAndKey = "control Z";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        pane.getInputMap().put(keyStroke, keyStrokeAndKey);
        pane.getActionMap().put(keyStrokeAndKey, action);
    }

    void redoKeyBindings(){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pane.canRedo()){
                    pane.redo();
                }
            }};
        String keyStrokeAndKey = "control shift Z";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        pane.getInputMap().put(keyStroke, keyStrokeAndKey);
        pane.getActionMap().put(keyStrokeAndKey, action);
    }
    void displayRules(){
        Action action = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, pane.getRule(),"Rule", JOptionPane.INFORMATION_MESSAGE);
            }};
        String keyStrokeAndKey = "control SPACE";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        pane.getInputMap().put(keyStroke, keyStrokeAndKey);
        pane.getActionMap().put(keyStrokeAndKey, action);
    }
}
