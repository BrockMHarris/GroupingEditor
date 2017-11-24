import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by harrisb on 9/23/17.
 * Creates the keybindings for the editor
 */
public class keyBindings
{
    Editor pane;

    /**
     * Adds the keybings to the JTextPane
     * @param pane the JTextPane that controls the editor
     */
    public keyBindings(Editor pane){
        this.pane = pane;
        undoKeyBindings();
        redoKeyBindings();
        displayRules();
    }

    /**
     * Sets the undo keybindings the CTL-Z
     */
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

    /**
     * Sets the redo keybindings the CTL SHIFT-Z
     */
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

    /**
     * Displays the rule that is being used to CTL-SPACE
     */
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
