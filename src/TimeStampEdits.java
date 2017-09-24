import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.text.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoableEdit;
import java.awt.*;

/**
 * Created by harrisb on 7/10/17.
 */
class TimeStampEdits extends CompoundEdit implements UndoableEdit
{

    private final AbstractDocument.DefaultDocumentEvent docEvents;
    private final int start;
    private final int length;
    private String letter;
    private final String editType;
    private final int lineNum;
    private String text;
    private boolean isHighlighted;
    private int dot;
    private int mark;

    private JEditorPane pane;

    private long timecreated;
    private boolean isSignificant;
    MyLogger logger;

    /**
     * This creates an edit object with information on where its located when it was created and how it was made
     * this is a subclass of AbstractDocument.DefaultDocumentEvent which is a casting of UndoableEdits
     * @param e this is an undoable edit even, this could be an insertion or deletion of a charactor or a string of text
     * @param name this the human readable for of the edit, it needs to be passed in from a seperate listener because for some reason
     *             you can get the letter from either super class. This listener is a keystroke listner that saves the document and then a
     *             document listener gets the name, these are located in the UndoOrganizer
     */
    TimeStampEdits(UndoableEditEvent e, String name, JEditorPane pane, int Mark, int Dot, MyLogger logger)
    {
        docEvents =(AbstractDocument.DefaultDocumentEvent) e.getEdit();
        letter = name;
        start = docEvents.getOffset();
        length = docEvents.getLength();
        timecreated = System.currentTimeMillis();
        editType = docEvents.getPresentationName();
        mark = Mark;
        dot = Dot;
        isHighlighted = mark != dot;
        this.pane = pane;
        this.logger = logger;

        if(editType.equals("addition")){
            try {
                text = docEvents.getDocument().getText(start, length);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
        // TODO fix line numberson newline so that newlines are counted as previous lines

        int caretPosition = docEvents.getDocument().getLength();
        Element root = docEvents.getDocument().getDefaultRootElement();
        lineNum = root.getElementIndex(caretPosition);
    }

    void setDot(int dot){
        this.dot = dot;
    }
    void setMark(int mark){
        this.mark = mark;
    }
    int getDot(){
        return dot;
    }

    int getMark()
    {
        return mark;
    }

    int getLineNumber()
    {
        return lineNum;
    }

    AbstractDocument.DefaultDocumentEvent getDocEvents(){
        return docEvents;
    }

    int getStart()
    {
        return start;
    }

    String getLetter(){
        return letter;
    }

    String getText(){
        return text;
    }

    int getLength()
    {
        return length;
    }
    void setHighlight(boolean isHighlighted){
        this.isHighlighted = isHighlighted;
        //System.out.println("setIT");
    }
    boolean isHighlighted(){

        return isHighlighted;
    }

    long getTimecreated()
    {
        return timecreated;
    }

    /**
     * @return either "addition" or "deletion"
     */
    String getEditType()
    {
        return editType;
    }

    public void undo()throws CannotUndoException
    {
        if (!canUndo()){
            throw new CannotUndoException();
        }
        docEvents.undo();

    }

    public void redo() throws CannotUndoException
    {
        if (!canRedo()){
            throw new CannotUndoException();
        }
        docEvents.redo();
    }

    public boolean canUndo()
    {
        return docEvents.canUndo();
    }
    public boolean canRedo()
    {
        return docEvents.canRedo();
    }

    public String toString()
    {
        return editType.charAt(0) + ": " + letter;
    }

    @Override
    public boolean isSignificant()
    {
        return isSignificant;
    }

    public void setSignificant(boolean significant)
    {
        isSignificant = significant;
    }


    @Override
    public void die() {

    }

    @Override
    public boolean addEdit(UndoableEdit undoableEdit)
    {
        return false;
    }

    @Override
    public boolean replaceEdit(UndoableEdit undoableEdit)
    {
        return false;
    }

    @Override
    public String getPresentationName()
    {
        return null;
    }

    @Override
    public String getUndoPresentationName()
    {
        return null;
    }

    @Override
    public String getRedoPresentationName()
    {
        return null;
    }

    public void setName(String name)
    {
        letter = name;
    }
}
