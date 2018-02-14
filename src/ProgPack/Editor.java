package ProgPack;

import ProgPack.Button.keyBindings;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


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
        //SyntaxHighlight pyColor = new SyntaxHighlight(this);
        setTabs(4);
        setText("#When you are done press the done button on the right of the window\n" +
                "#If you have any questions press the questions button, your session will be paused until the button is released\n" +
                "#Please make sure your program works as intended by pressing F5\n\n" +
                "def contains_duplicate(list_of_nums):\n" +
                "\t\"\"\"\n\tTakes a list of integers as an input at returns true if that list contains 1 or more duplicated\n" +
                "\tThe list cannot be changed by this function\n\t\"\"\"\n\n\n\n\n\t" +
                "\nlist_of_nums = [1,3,2,4,3,6]\n" +
                "print(contains_duplicate(list_of_nums))\n\n" );
    }

    private void setTabs(int charactersPerTab){
        Document doc = this.getDocument();
        if (doc instanceof PlainDocument) {
            doc.putProperty(PlainDocument.tabSizeAttribute, 4);
        }
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
