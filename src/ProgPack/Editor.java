package ProgPack;

import ProgPack.Button.keyBindings;

import javax.swing.*;


/**
 * Created by harrisb on 7/10/17.
 * This class allows edits to be made to the window. It sets up keybindings and the style of the document. Allowing for
 * for auto-indentation. It also is the only class with access to the class that creates the undo crouping and edits.
 *
 * The iditor type used is JTextPane because it allows for different colored text within one document.
 */
public class Editor extends JTextPane
{
    private UndoOrganizer undoOrganizer;

    /**
     * This is the constructor that creates the JTextPAne object. It also creates the undoOrginizer which keeps track of
     * all the undo groupings, and creates the keybindings that are available through the keybindings class
     */
    Editor(Timer timer)
    {
        super();
        undoOrganizer = new UndoOrganizer(this, timer);
        getDocument().addUndoableEditListener(undoOrganizer);
        getDocument().addDocumentListener(undoOrganizer);
        keyBindings keyBindings = new keyBindings(this);
    }

    /**
     * This methods allows for outside classes such as buttons in the window to undo a group of edits
     */
    public void undo()
    {
        undoOrganizer.undo();
    }

    /**
     * This methods allows for outside classes such as buttons in the window to redo a group of edits
     */
    public void redo()
    {
        undoOrganizer.redo();
    }

    /**
     * @return Returns true if there is a group of edits that is available to be undone. false otherwise
     */
    public boolean canUndo()
    {
        return undoOrganizer.canUndo();
    }

    /**
     * @return Returns true if there is a group of edits that is available to be undone. false otherwise
     */
    public boolean canRedo()
    {
        return undoOrganizer.canRedo();
    }

    /**
     * Changes the desired rule for grouping of edits. which can be seen with the getRule() method
     * @param rule the desired rule
     */
    public void setRule(String rule){
        undoOrganizer.setRule(rule);
    }

    /**
     * @return The list of rules for grouping of edits
     */
    public String getRule(){
        return undoOrganizer.getRule();
    }
//    Vector<Editor.MyCompoundEdit> getGroups(){
//        return undoOrganizer.getGroups();
//    }
}
