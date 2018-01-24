package ProgPack.Button;

import ProgPack.Editor;
import ProgPack.Timer;



import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class questionAction implements ItemListener{

    Editor app;
    JButton redo;
    Timer timer;


    /**
     * Constructor
     *
     * @param Editor The JTextPane that needs to be edited
     * @param  question   The button that is being listened to.
     */
    public questionAction(Editor Editor, JToggleButton question, Timer timer) {
        app = Editor;
        this.redo = redo;
        this.timer = timer;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            app.setEditable(false);
            timer.pause();


        } else if(e.getStateChange()==ItemEvent.DESELECTED){
            app.setEditable(true);
            timer.unPause();
        }
    }
}
