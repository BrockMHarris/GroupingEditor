import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by harrisb on 7/19/17.
 */
class RulesActionListener implements ActionListener
{
    Editor app;
    JComboBox rules;
    public RulesActionListener(Editor app, JComboBox rules){
        this.app = app;
        this.rules = rules;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        app.setRule(rules.getSelectedItem().toString());
        //System.out.println(name);
    }
}
