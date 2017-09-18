import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by harrisb on 7/10/17.
 */
class TextEditor
{
    public static void main(String[] args)
    {
        //This is a test
        MyLogger logger = new MyLogger();
        JFrame frame = new JFrame("UndoOrganizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Editor app = new Editor(logger);

        //JComboBox<MyCompoundEdit> undoList = new JComboBox<MyCompoundEdit>(app.getGroups());
        //JComboBox ruleSelection = new JComboBox(app.getRule());
        //ruleSelection.addActionListener(new RulesActionListener(app, ruleSelection));
        //ruleSelection.setSelectedIndex(1);

        //UndoListActionListener actionListener = new UndoListActionListener(app, undoList);
        //undoList.addActionListener(actionListener);

        JScrollPane scroll = new JScrollPane(app);
        frame.getContentPane().add(scroll);

        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(app);
        scroll.setRowHeaderView(lineNumberingTextArea);

        app.getDocument().addDocumentListener(new LineNumberListener(lineNumberingTextArea));

        JToolBar tb = new JToolBar();
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");

        btnUndo.addActionListener(new UndoAction(app, btnUndo));
        btnRedo.addActionListener(new RedoAction(app, btnRedo));
        tb.add(btnUndo);
        tb.add(btnRedo);
        //tb.add(ruleSelection);
        //tb.add(undoList);

        btnUndo.setFocusable(false);
        btnRedo.setFocusable(false);
        //ruleSelection.setFocusable(false);
        //undoList.setFocusable(false);

        MyBackroundMethod thread = new MyBackroundMethod(btnUndo, btnRedo, app);
        new Thread(thread).start();

        frame.getContentPane().add(tb, BorderLayout.NORTH);
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                frame.setSize(800, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
