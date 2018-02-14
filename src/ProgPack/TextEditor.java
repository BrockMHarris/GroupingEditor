package ProgPack;
import ProgPack.Button.*;

import ProgPack.LineNumber.LineNumberListener;
import ProgPack.LineNumber.LineNumberingTextArea;
import ProgPack.LineNumber.NewLineFilter;

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
        Timer timer = new Timer();
        //Editor.MyLogger.write("Rule: " + args[0]);
        JFrame frame = new JFrame("Before you start");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel BeginScreen = new JPanel(new GridLayout(1,1,0,0));
        frame.getContentPane().add(BeginScreen, BorderLayout.CENTER);
        JButton btnBegin = new JButton("Press to Begin");
        btnBegin.addActionListener(new beginAction(frame, timer, args));

        btnBegin.setLayout(new BorderLayout());

        //JLabel importantInfo = new JLabel("1) something");
        //JLabel importantInfo2 = new JLabel("2) important");

        //btnBegin.add(importantInfo, BorderLayout.NORTH);
        //btnBegin.add(importantInfo2);
        BeginScreen.add(btnBegin, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

    }

    public static void Editor(Timer timer, String[] args){
        JFrame frame = new JFrame("Editor.UndoOrganizer");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel sidePanel = new JPanel(new GridLayout(2,1,0,0));
        sidePanel.setBackground(Color.gray);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(sidePanel, BorderLayout.EAST);

        JPanel editor = new JPanel();
        editor.setLayout(new BorderLayout());
            Editor app = new Editor(timer);
            editor.add(app, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
            JTextArea output = new JTextArea();
            outputPanel.add(output, BorderLayout.CENTER);

        JSplitPane mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editor,outputPanel);


        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setOneTouchExpandable(true);

        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 50);
        outputPanel.setMinimumSize(minimumSize);
        editor.setMinimumSize(minimumSize);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //editor.setPreferredSize(screenSize);

        //Set the initial location and size of the divider
        mainPanel.setDividerLocation(screenSize.height-150);
        mainPanel.setDividerSize(10);

        //Provide a preferred size for the split pane
        //mainPanel.setPreferredSize(new Dimension(400, 200));
//



        app.setRule(args[1]);
        //app.setTabSize(4);
        AbstractDocument doc = (AbstractDocument)app.getDocument();
        doc.setDocumentFilter( new NewLineFilter() );

        //Buttons for history list. Inactive right now
        //      JComboBox<Editor.MyCompoundEdit> undoList = new JComboBox<Editor.MyCompoundEdit>(app.getGroups());
        //      JComboBox ruleSelection = new JComboBox(app.getRule());
        //      ruleSelection.addActionListener(new Editor.Button.RulesActionListener(app, ruleSelection));
        //      ruleSelection.setSelectedIndex(1);

        //      Editor.Button.UndoListActionListener actionListener = new Editor.Button.UndoListActionListener(app, undoList);
        //      undoList.addActionListener(actionListener);

        JScrollPane scroll = new JScrollPane(app);
        editor.add(scroll);

        JScrollPane scroll2 = new JScrollPane(output);
        outputPanel.add(scroll2);


        //Line numbers for the text
        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(app);
        scroll.setRowHeaderView(lineNumberingTextArea);
        app.getDocument().addDocumentListener(new LineNumberListener(lineNumberingTextArea));

        //Adds the inportant buttons
        JToolBar tb = new JToolBar();
        //JButton btnUndo = new JButton("Undo");
        //JButton btnRedo = new JButton("Redo");
        JButton btnSave = new JButton("Save and Run");
        JToggleButton btnQuestion = new JToggleButton("Questions");
        JButton btnDone = new JButton("Done");

        //btnUndo.addActionListener(new UndoAction(app, btnUndo));
        //btnRedo.addActionListener(new RedoAction(app, btnRedo));
        btnSave.addActionListener(new saveBtnAction(args[0], frame, app, output));
        btnQuestion.addItemListener(new questionAction(app, btnQuestion, timer));
        btnDone.addActionListener(new doneAction(app, timer));

        //tb.add(btnUndo);
        //tb.add(btnRedo);
        tb.add(btnSave);
        sidePanel.add(btnQuestion);
        sidePanel.add(btnDone);
        //      tb.add(ruleSelection);
        //      tb.add(undoList);

        //btnUndo.setFocusable(false);
        //btnRedo.setFocusable(false);
        btnQuestion.setFocusable(false);
        btnDone.setFocusable(false);
        //      ruleSelection.setFocusable(false);
        //      undoList.setFocusable(false);

        //MyBackroundMethod thread = new MyBackroundMethod(btnUndo, btnRedo, app);
        //new Thread(thread).start();

        frame.getContentPane().add(tb, BorderLayout.NORTH);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
