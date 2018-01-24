package ProgPack.Button;

import ProgPack.Editor;
import ProgPack.TextEditor;
import ProgPack.Timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setVisible(false);
        timer.begin();
        TextEditor.Editor(timer,args);
    }
}
