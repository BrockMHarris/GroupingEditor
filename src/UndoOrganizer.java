import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by harrisb on 7/10/17.
 */
class UndoOrganizer extends UndoManager implements UndoableEditListener, KeyListener, CaretListener,  DocumentListener
{

    private ArrayList<TimeStampEdits> edits = new ArrayList<TimeStampEdits>();
    private Vector<MyCompoundEdit> groups = new Vector<MyCompoundEdit>();
    private int groupPointer;
    private JTextPane pane;
    private JComboBox undoList;
    private String previousText;
    private TimeStampEdits timeEdit;
    private String name;

    static final String[] keywords = {"for", "int", "float", "while", "if", "char", "long", "new", "public", "static", "void", "final"};

    private boolean lineChange;
    private boolean timeDiff;
    private boolean isHighliting;

    private int dotLineNum;
    private int markLineNum;

    static String[] Rules = {"Time and Line and Type","Time and Line","Line and Type","Time","Line","No Rules"};
    private UndoRule rule;
    private String currentRule;

    private int dot;
    private int mark;

    static final int NEWLINEKEYCODE = 10;
    static final int TABKEYCODE = 9;

    private MyCompoundEdit currentGroup;
    private UndoManager undoManager;
    //static MyLogger logger;

    /**
     * This creates a listener class that makes all the decisions. This is the main class. it listens for
     * string edits to the document, changes to the document in general, keystrokes, and movement of the caret
     *
     * @param Pane this is the JEditorPane that houses the text, I need this so i can get the actual readable characters
     *            and set then to the current TimeStampEdit in the case of a deletion
     */
    UndoOrganizer(JTextPane Pane)
    {
        pane = Pane;
        //this.undoList = new JComboBox<MyCompoundEdit>(groups);
        pane.addKeyListener(this);
        groupPointer = -1;
        rule = new TimeLineBasedRules();
        pane.addCaretListener(this);
        undoManager = new UndoManager();
        undoManager.setLimit(-1);
        currentGroup = new MyCompoundEdit();
//        this.logger = logger;

    }


    /**
     * the listens for changes in the text. it creates one of the rules objects and then asks it whether it should be
     * with the previous group or on its own. edits are put int CompoundEdits these CompoundEdits have to be ended
     * before they can be undon, and there is no way to access the individual edits once they are put in there, so there is a separate
     * list
     * @param e and individual undoable edit which is then turned into a CompoundEdit
     */
    @Override
    public void undoableEditHappened(UndoableEditEvent e)
    {
        timeEdit = new TimeStampEdits(e, name, pane, dot, mark);

        //for(int i = 0; i < groups.size(); i++){
        //    if(!groups.get(i).canUndo()){
        //        groups.get(i).die();
        //    }
        //}
        edits.add(timeEdit);
        isHighliting = false;
        if (edits.size() <= 1)
        {
            timeEdit.setSignificant(true);
            undoManager.addEdit(timeEdit);
            //groups.add(new MyCompoundEdit());
            //groupPointer = groups.size() - 1;
            //groups.get(groups.size()-1).addEdit(timeEdit);
        }
        else
        {
            if (rule.withPrevious(edits))
            {
                timeEdit.setSignificant(false);
                undoManager.addEdit(timeEdit);
                //groups.get(groupPointer).addEdit(timeEdit);
            }
            else
            {
                timeEdit.setSignificant(true);
                undoManager.addEdit(timeEdit);

                //groups.get(groupPointer).end();
                //groups.add(new MyCompoundEdit());
                //groupPointer++;
                //groups.get(groupPointer).addEdit(timeEdit);
            }
        }
    }
    public void undo() throws CannotUndoException
    {
        previousText = pane.getText();
        //if(groups.get(groupPointer).isInProgress())
        //{
        //    getGroups().get(groupPointer).end();
        //    //super.addEdit(currentGroup);
        //}
        edits.get(0).setSignificant(true);
        undoManager.undo();
        timeEdit.setSignificant(true);
        //groups.get(groupPointer).undo();
        //groupPointer--;

    }
    public void redo() throws CannotUndoException
    {
        edits.get(0).setSignificant(false);
        undoManager.redo();
        //groupPointer++;
        //groups.get(groupPointer).redo();
    }

    public boolean canUndo(){
        return undoManager.canUndo();
        //return (groupPointer>=0) && groups.get(groupPointer).canUndo();

        //if(groupPointer<0){
        //    return false;
        //}
        //return groups.get(groupPointer).canUndo();
    }
    public boolean canRedo(){
        return undoManager.canRedo();

        //return (groups.size()>=1) &&
        //        (groupPointer < groups.size()-1) &&
        //        (groups.get(groupPointer+1).canRedo());
    }

    String getRule(){
        return this.currentRule;
    }
    String[] getKeywords(){
        return keywords;
    }

    /**
     * You only have a certain number of strings, default is TimeLineBasedRules
     * @param rule "Time and Line and Type","Time and Line","Line and Type","Time","Line","No Rules"
     */
    void setRule(String rule){

        switch (rule){
            case "TimeLineType":
                this.rule = new TimeLineTypeBasedRules();
                currentRule = "TimeLineType";
                break;
            case "TimeLine":
                this.rule = new TimeLineBasedRules();
                currentRule = "TimeLine";

                break;
            case "LineType":
                this.rule = new LineTypeBasedRules();
                currentRule = "LineType";
                break;
            case "Time":
                this.rule = new TimeBasedRules();
                currentRule = "Time";
                break;
            case "Line":
                this.rule = new LineBasedRules();
                currentRule = "Line";
                break;
            case "NoRules":
                this.rule = new NoGroupBasedRules();
                currentRule = "NoRules";
                break;
        }
    }

    Vector<MyCompoundEdit> getGroups(){
        return groups;
    }

    private void inputKeyTyped(java.awt.event.KeyEvent evt) {
        String text = "";
        if (evt.getKeyChar()=='('){
            text = pane.getText();
            text = text + ")";
            pane.setText(text);
            pane.setCaretPosition(pane.getText().length()-1);
        }
    }



    /**
     * This is the document listener i use removeUpdate to see what sections were removed so i can check which letters
     * were there and apply a name to the right timeStamped Object, these listeners are triggered before the undoable edit listener above
     * @param documentEvent and change to the document
     */
    @Override
    public void insertUpdate(DocumentEvent documentEvent)
    {

    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        if(previousText != null) {
            int offset = e.getOffset();
            int length = e.getLength();
            if((offset + length) < previousText.length())
            {
                String removedStr = previousText.substring(e.getOffset(), e.getOffset() + e.getLength());
                String withNewLines = removedStr.replaceAll("\n","\\(\\\\n\\)");
                withNewLines = withNewLines.replaceAll("\t","\\(\\\\t\\)");
                name = withNewLines;
            }
        }
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {

    }


    /**
     * This is a caret listener, i can use this to get what text is being highlighted, i have not implemented this yet
     * @param caretEvent change i the position of the caret
     */
    @Override
    public void caretUpdate(CaretEvent caretEvent) {
        if(caretEvent.getDot() != caretEvent.getMark()){
            isHighliting = true;
            //System.out.println("highlighted");
            //timeEdit.setHighlight(true);

            Element root = timeEdit.getDocEvents().getDocument().getDefaultRootElement();
            dotLineNum = root.getElementIndex(caretEvent.getDot());
            markLineNum = root.getElementIndex(caretEvent.getMark());
            mark = caretEvent.getMark();
            dot = caretEvent.getDot();
        }
    }

    /**
     * These are the keystroke listeners, these are the first to be updated, key typed only looks for printable characters
     * such as number and letters, i am storing the previous string for each key even so that i can know what was deleted
     *
     * key pressed and key released track all keys
     * @param keyEvent The key that is pressed it set as the letter in the undoable event
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        name = Character.toString(keyEvent.getKeyChar());
        inputKeyTyped(keyEvent);

    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        //System.out.print(keyEvent.getExtendedKeyCode()+ ": ");
        if(keyEvent.getExtendedKeyCode() == NEWLINEKEYCODE){
            name = "(\\n)";
        }
        if(keyEvent.getExtendedKeyCode() == TABKEYCODE){
            name = "(\\t)";
        }
        previousText = pane.getText();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
