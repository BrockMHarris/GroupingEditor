import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * Created by harrisb on 7/10/17.
 */
class Editor extends JTextPane
{
    //git commit
    private JComboBox box;
    private UndoOrganizer undoOrganizer;
    private static MyLogger logger;
    Editor(MyLogger logger)
    {
        super();
        this.logger = logger;
        //this.box =undoList;
        undoOrganizer = new UndoOrganizer(this, logger);
        //setEditorKit(new StyledEditorKit());
        getDocument().addUndoableEditListener(undoOrganizer);
        getDocument().addDocumentListener(undoOrganizer);
        keyBindings keyBindings = new keyBindings(this);
    }
    void undo()
    {
        undoOrganizer.undo();
    }
    void redo()
    {
        undoOrganizer.redo();
    }
    boolean canUndo()
    {
        return undoOrganizer.canUndo();
    }
    boolean canRedo()
    {
        return undoOrganizer.canRedo();
    }
    void setRule(String rule){
        undoOrganizer.setRule(rule);
    }
    String getRule(){
        return undoOrganizer.getRule();
    }
    Vector<MyCompoundEdit> getGroups(){
        return undoOrganizer.getGroups();
    }
}
