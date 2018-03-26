package ProgPack;

import javax.swing.event.UndoableEditEvent;
import javax.swing.text.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoableEdit;

/**
 * Created by harrisb on 7/10/17.
 * This is a modification of the undoable edit class. It keeps track of when the edit is created so that we can compare them.
 * It also keeps track of where it was inserted, what type of edit it is, whether is was highlighted when the edit occurred
 * and how long it is
 */
public class TimeStampEdits extends CompoundEdit implements UndoableEdit
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

    //private JEditorPane pane;

    private long timecreated;
    private boolean isSignificant;

    /**
     * This creates an edit object with information on where its located when it was created and how it was made
     * this is a subclass of AbstractDocument.DefaultDocumentEvent which is a casting of UndoableEdits
     * @param e this is an undoable edit even, this could be an insertion or deletion of a charactor or a string of text
     * @param name this the human readable for of the edit, it needs to be passed in from a seperate listener because for some reason
     *             you can get the letter from either super class. This listener is a keystroke listner that saves the document and then a
     *             document listener gets the name, these are located in the Editor.UndoOrganizer
     * @param Mark the make is the first point of a highlight. The TimeStampEdit uses this to detemrine if it was created
     *             or deleted while bieng highlighed
     * @param Dot This is the last point of a highlight
     */
    TimeStampEdits(UndoableEditEvent e, String name, int Mark, int Dot, Timer timer)
    {
        docEvents =(AbstractDocument.DefaultDocumentEvent) e.getEdit();
        letter = name;
        start = docEvents.getOffset();
        length = docEvents.getLength();
        timecreated = timer.getCurrentTime();
        editType = docEvents.getPresentationName();
        mark = Mark;
        dot = Dot;
        isHighlighted = mark != dot;
        //this.pane = pane;


        if(editType.equals("addition")){
            try {
                text = docEvents.getDocument().getText(start, length);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
        else {
        }


        int caretPosition = start;//docEvents.getDocument().getLength();
        Element root = docEvents.getDocument().getDefaultRootElement();
        lineNum = root.getElementIndex(caretPosition);
        int lineNumStartAtOne = lineNum+1;


        MyLogger.write(new String[]{String.valueOf(timecreated), editType,letter , String.valueOf(isHighlighted), String.valueOf(mark),
                String.valueOf(dot), String.valueOf(start), String.valueOf(length), String.valueOf(lineNumStartAtOne)});
    }

    /**
     * This allows the user to change the last point of a higlighed group held by this time stamped edit
     * @param dot the index of the last highlight point in the string of the text.
     */
    public void setDot(int dot){
        this.dot = dot;
    }
    /**
     * This allows the user to change the first point of a higlighed group held by this time stamped edit
     * @param mark the index of the first highlight point in the string of the text.
     */
    public void setMark(int mark){
        this.mark = mark;
    }

    /**
     * @return the index of the last point of highlight stored by this edit
     */
    public int getDot(){
        return dot;
    }

    /**
     * @return the index of the first point of highlight stored by this edit
     */
    public int getMark()
    {
        return mark;
    }

    /**
     * @return line number this edit is located on, found by the number of \n before this edit in the text
     */
    public int getLineNumber()
    {
        return lineNum;
    }

    /**
     * @return The edit that is being saved as the timeStampEdit
     */
    public AbstractDocument.DefaultDocumentEvent getDocEvents(){
        return docEvents;
    }

    /**
     * @return Index of the beginning of the edit
     */
    public int getStart()
    {
        return start;
    }

    /**
     * @return String representation of the edit. This is used for storing the letter of deletion edits, this cannot be done the
     * same way as additions
     */
    public String getLetter(){
        return letter;
    }

    /**
     * @return String represention of the edit. Only works for addition events
     */
    public String getText(){
        return text;
    }

    /**
     * @return Length of the edit
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @return true if the edit was created while it was highlighted. false otherwise
     */
    public boolean isHighlighted(){

        return isHighlighted;
    }

    /**
     * @return Time relative to the start of the program that the edit was created
     */
    public long getTimecreated()
    {
        return timecreated;
    }

    /**
     * @return either "addition" or "deletion"
     */
    public String getEditType()
    {
        return editType;
    }

    /**
     * Undo this edit
     * @throws CannotUndoException
     */
    public void undo()throws CannotUndoException
    {
        if (!canUndo()){
            throw new CannotUndoException();
        }
        docEvents.undo();
    }

    /**
     * Redo this edit
     * @throws CannotUndoException
     */
    public void redo() throws CannotUndoException
    {
        if (!canRedo()){
            throw new CannotUndoException();
        }
        docEvents.redo();
    }

    /**
     * @return True if this edit can be undone, false otherwise.
     */
    public boolean canUndo()
    {
        return docEvents.canUndo();
    }

    /**
     * @return True if this edit can be redone, false otherwise.
     */
    public boolean canRedo()
    {
        return docEvents.canRedo();
    }

    /**
     * @return string representation of the edit it in fore a: t or d: t where a is addition and d is deletion, and t is
     * charactor of the edit
     */
    public String toString()
    {
        return editType.charAt(0) + ": " + letter;
    }

    /**
     * Used by undoManager to determine if a new group should be made
     * @return true if is significant, false otherwise
     */
    @Override
    public boolean isSignificant()
    {
        return isSignificant;
    }

    /**
     * Used by undoManager to determine if a new group should be made
     * @param significant Sets the significance of the edit.
     */
    public void setSignificant(boolean significant)
    {
        isSignificant = significant;
    }


    /**
     * required method for undoable edit, but not necesary. Does not do anything
     */
    @Override
    public void die() {

    }

    /**
     * required method for undoable edit, but not necesary. Does not do anything
     */
    @Override
    public boolean addEdit(UndoableEdit undoableEdit)
    {
        return false;
    }

    /**
     * required method for undoable edit, but not necesary. returns false
     */
    @Override
    public boolean replaceEdit(UndoableEdit undoableEdit)
    {
        return false;
    }

    /**
     * required method for undoable edit, but not necesary. returns null
     */
    @Override
    public String getPresentationName()
    {
        return null;
    }

    /**
     * required method for undoable edit, but not necesary. returns null
     */
    @Override
    public String getUndoPresentationName()
    {
        return null;
    }

    /**
     * required method for undoable edit, but not necesary. returns null
     */
    @Override
    public String getRedoPresentationName()
    {
        return null;
    }

    /**
     * Set the charactor this edit holds
     * @param name charactor to be held
     */
    public void setName(String name)
    {
        letter = name;
    }
}
