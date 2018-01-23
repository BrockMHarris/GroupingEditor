package Editor.Button;

import Editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by harrisb on 7/19/17.
 * Currently unused
 *
 * Listener for rule selection button. Allows the ability to switch between rules while editing.
 */
class RulesActionListener implements ActionListener
{
    Editor app;
    JComboBox rules;

    /**
     * Constructor
     * @param app The JTextPane that needs to be edited
     * @param rules The button that is being listened to.
     */
    public RulesActionListener(Editor app, JComboBox rules){
        this.app = app;
        this.rules = rules;
    }

    /**
     * changes the grouping rule
     * @param actionEvent action event on the JButton
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        app.setRule(rules.getSelectedItem().toString());
        //System.out.println(name);
    }
}
