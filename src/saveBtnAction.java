import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class saveBtnAction implements ActionListener {
    Editor textArea;
    JButton redo;
    String fileLoc;

    public saveBtnAction(Editor textArea, JButton redo, String fileLoc) {
        this.textArea = textArea;
        this.redo = redo;
        this.fileLoc = fileLoc;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        File file = null;
        FileWriter out = null;


        try {
            file = new File(fileLoc + "\\program.py");
            out = new FileWriter(file);
            out.write(textArea.getText());
            out.close();
        } catch (FileNotFoundException o) {
            o.printStackTrace();
        } catch (IOException o) {
            o.printStackTrace();
        }
    }
}