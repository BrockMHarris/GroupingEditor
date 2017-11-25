import javax.swing.*;
import javax.swing.undo.CompoundEdit;
import java.awt.event.*;

/**
 * Created by harrisb on 7/21/17.
 * Not currently used
 *
 * Listener for the undo list that displays the edits to the area. Can be used to undo multiple groups at once
 */
class UndoListActionListener implements ActionListener
{
    private JComboBox undoList;
    private Editor app;
    private static int pointer;
    private boolean first;

    /**
     * Constructor
     * @param app The JTextPane that needs to be edited
     * @param undoList The dropdown list that is being listened to.
     */
    UndoListActionListener(Editor app, JComboBox undoList)
    {
        this.app = app;
        this.undoList = undoList;
        pointer = undoList.getItemCount()-1;
        first = true;
    }

    /**
     * When an element in the list is clicked it undoes every group up until the clicked group
     * @param actionEvent undo list event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        //undoList.setSelectedIndex(0);
        //undoList.add(new )
        Object toRemove = undoList.getItemAt(undoList.getItemCount()-1);
        undoList.removeItem(toRemove);
        undoList.addItem(toRemove);

        if(first)
        {
            pointer = undoList.getItemCount();
            first = false;
        }
        int item = undoList.getSelectedIndex();
        for(int i = item; i < pointer; i++)
        {
            app.undo();
        }
        pointer = item;

    }
}
