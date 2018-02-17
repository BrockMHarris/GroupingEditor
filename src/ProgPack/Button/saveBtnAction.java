package ProgPack.Button;

import ProgPack.Editor;
import ProgPack.terminalExecution;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class saveBtnAction implements ActionListener {
    JFrame frame;
    Editor textArea;
    String fileLoc;
    JTextArea terminalOutput;

    public saveBtnAction(JFrame frame, Editor textArea, JTextArea terminalOutput) {
        this.terminalOutput = terminalOutput;
        this.textArea = textArea;
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        File file = null;
        FileWriter out = null;


        try {
            //file = new File(fileLoc + "\\program.py");
            file = new File("program.py");

            out = new FileWriter(file);
            out.write(textArea.getText());
            out.close();
        } catch (FileNotFoundException o) {
            o.printStackTrace();
        } catch (IOException o) {
            o.printStackTrace();
        }

        terminalExecution command = new terminalExecution(terminalOutput);
        try {
            command.execute("python3 program.py");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }
}