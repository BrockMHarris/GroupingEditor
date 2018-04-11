package ProgPack.Button;

import ProgPack.Editor;
import ProgPack.MyLogger;
import ProgPack.TextEditor;
import ProgPack.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class beginAction implements ActionListener {
    JFrame frame;
    Timer timer;
    String[] args;

    public beginAction(JFrame frame, Timer timer, String[] args){
        this.frame = frame;
        this.timer = timer;
        this.args = args;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //frame.setVisible(false);
        timer.begin();
        try {
            Robot rob = new Robot();
            rob.keyPress(KeyEvent.VK_SHIFT);
            rob.keyPress(KeyEvent.VK_CONTROL);
            rob.keyPress(KeyEvent.VK_F2);

            rob.keyRelease(KeyEvent.VK_SHIFT);
            rob.keyRelease(KeyEvent.VK_CONTROL);
            rob.keyRelease(KeyEvent.VK_F2);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        MyLogger.setup();
        frame.remove(frame.getContentPane().getComponent(0));
        TextEditor.Editor(timer, frame);
    }
}
