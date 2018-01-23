package Editor;
import Editor.Button.*;

import Editor.Button.*;
import Editor.LineNumber.LineNumberListener;
import Editor.LineNumber.LineNumberingTextArea;
import Editor.LineNumber.NewLineFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

/**
 * Created by harrisb on 7/10/17.
 * This program creates a simple text editor that is able to keep track of more information about the edits that are
 * happening in the editor.
 *
 * its main purpose is to change the fucntionality of the undo button as the user pleases. It allows the user to change
 * how much is getting undone and redone, based on specific rules such as time between edits. You cna create a new rule
 * by creating an object that implements Editor.Rules.UndoRule and adding it to the list of available rules in the undo organizer class
 */
public class TextEditor
{
    public static void main(String[] args)
    {
        MyLogger.setup(args[0]);
        //Editor.MyLogger.write("Rule: " + args[0]);
        JFrame frame = new JFrame("Editor.UndoOrganizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.gray);
        frame.getContentPane().add(sidePanel, BorderLayout.LINE_END);



        final Editor app = new Editor();
        app.setRule(args[1]);
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
        //      JComboBox<Editor.MyCompoundEdit> undoList = new JComboBox<Editor.MyCompoundEdit>(app.getGroups());
        //      JComboBox ruleSelection = new JComboBox(app.getRule());
        //      ruleSelection.addActionListener(new Editor.Button.RulesActionListener(app, ruleSelection));
        //      ruleSelection.setSelectedIndex(1);

        //      Editor.Button.UndoListActionListener actionListener = new Editor.Button.UndoListActionListener(app, undoList);
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
        JButton btnSave = new JButton("Save");
        JButton btnQuestion = new JButton("Questions");
        JButton btnDone = new JButton("Done");

        btnUndo.addActionListener(new UndoAction(app, btnUndo));
        btnRedo.addActionListener(new RedoAction(app, btnRedo));
        btnSave.addActionListener(new saveBtnAction(app, btnSave,args[0]));
        btnQuestion.addActionListener(new questionAction(app, btnQuestion));
        btnDone.addActionListener(new doneAction(app, btnDone));
        tb.add(btnUndo);
        tb.add(btnRedo);
        tb.add(btnSave);
        sidePanel.add(btnQuestion, BorderLayout.NORTH);
        sidePanel.add(btnDone, BorderLayout.SOUTH);
        //      tb.add(ruleSelection);
        //      tb.add(undoList);

        btnUndo.setFocusable(false);
        btnRedo.setFocusable(false);
        //      ruleSelection.setFocusable(false);
        //      undoList.setFocusable(false);

        MyBackroundMethod thread = new MyBackroundMethod(btnUndo, btnRedo, app);
        new Thread(thread).start();

        frame.getContentPane().add(tb, BorderLayout.NORTH);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
