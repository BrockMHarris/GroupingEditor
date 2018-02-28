package ProgPack;

import ProgPack.Button.keyBindings;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.text.*;


/**
 * Created by harrisb on 7/10/17.
 * This class allows edits to be made to the window. It sets up keybindings and the style of the document. Allowing for
 * for auto-indentation. It also is the only class with access to the class that creates the undo crouping and edits.
 *
 * The iditor type used is JTextPane because it allows for different colored text within one document.
 */
public class Editor extends RSyntaxTextArea
{
    private UndoOrganizer undoOrganizer;

    /**
     * This is the constructor that creates the JTextPAne object. It also creates the undoOrginizer which keeps track of
     * all the undo groupings, and creates the keybindings that are available through the keybindings class
     */
    Editor(Timer timer)
    {
        super(20, 60);
        undoOrganizer = new UndoOrganizer(this, timer);
        getDocument().addUndoableEditListener(undoOrganizer);
        getDocument().addDocumentListener(undoOrganizer);
        keyBindings keyBindings = new keyBindings(this);
        setText("#When you are done press the \"Done\" button on the bottom right of the window\n" +
                "#If you have any questions press the \"Questions button\". your session will be paused until the button is released\n" +
                "#Please make sure your program works as intended by pressing  the \"Save and Run\" button\n\n" +
                "def contains_duplicate(list_of_nums):\n" +
                "\t\"\"\"\n\tTakes a list of integers as an input at returns True if that list contains 1 or more duplicated\n" +
                "\treturn False if there is not duplicates in the list\n" +
                "\tThe list cannot be changed by this function\n\t\"\"\"\n\n\n\n\n\t" +
                "\nlist_with_dups = [1,3,2,4,3,6]" +
                "\nlist_no_dups = [4,8,3,10,6,5]\n" +
                "print(contains_duplicate(list_with_dups))  # should return True\n" +
                "print(contains_duplicate(list_no_dups))    # should return False\n" );
        MyLogger.clear();
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
}
