import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;

/**
 * Created by harrisb on 7/10/17.
 * This program creates a simple text editor that is able to keep track of more information about the edits that are
 * happening in the editor.
 *
 * its main purpose is to change the fucntionality of the undo button as the user pleases. It allows the user to change
 * how much is getting undone and redone, based on specific rules such as time between edits. You cna create a new rule
 * by creating an object that implements UndoRule and adding it to the list of available rules in the undo organizer class
 */
class TextEditor
{
    public static void main(String[] args)
    {
        MyLogger.setup();
        //MyLogger.write("Rule: " + args[0]);
        JFrame frame = new JFrame("UndoOrganizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Editor app = new Editor();
        app.setRule(args[0]);
        //app.setTabSize(4);
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                AbstractDocument doc = (AbstractDocument)app.getDocument();
                doc.setDocumentFilter( new NewLineFilter() );
            }
        });

        //Buttons for history list. Inactive right now
        //      JComboBox<MyCompoundEdit> undoList = new JComboBox<MyCompoundEdit>(app.getGroups());
        //      JComboBox ruleSelection = new JComboBox(app.getRule());
        //      ruleSelection.addActionListener(new RulesActionListener(app, ruleSelection));
        //      ruleSelection.setSelectedIndex(1);

        //      UndoListActionListener actionListener = new UndoListActionListener(app, undoList);
        //      undoList.addActionListener(actionListener);

        JScrollPane scroll = new JScrollPane(app);
        frame.getContentPane().add(scroll);


        //Line numbers for the text
        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(app);
        scroll.setRowHeaderView(lineNumberingTextArea);
        app.getDocument().addDocumentListener(new LineNumberListener(lineNumberingTextArea));

        //Adds the inportant buttons
        JToolBar tb = new JToolBar();
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");

        btnUndo.addActionListener(new UndoAction(app, btnUndo));
        btnRedo.addActionListener(new RedoAction(app, btnRedo));
        tb.add(btnUndo);
        tb.add(btnRedo);
        //      tb.add(ruleSelection);
        //      tb.add(undoList);

        btnUndo.setFocusable(false);
        btnRedo.setFocusable(false);
        //      ruleSelection.setFocusable(false);
        //      undoList.setFocusable(false);

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
