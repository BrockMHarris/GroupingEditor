import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoableEdit;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harrisb on 7/17/17.
 */
class MyCompoundEdit extends javax.swing.undo.CompoundEdit
{
    public MyCompoundEdit()
    {
        super();
    }

    @Override
    public boolean canUndo(){
        for(int i = 0; i<super.edits.size();i++)
        {
            TimeStampEdits edit = (TimeStampEdits) super.edits.get(i);
            System.out.println(edit.isInProgress());

            if(!edit.canUndo())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canRedo(){
        for(int i = 0; i<super.edits.size();i++)
        {
            TimeStampEdits edit = (TimeStampEdits) super.edits.get(i);
            if(!edit.canRedo())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        String letters = "";
        String type = "";
        for(int i = 0; i<super.edits.size();i++){
            TimeStampEdits edit = (TimeStampEdits) super.edits.get(i);
            letters += edit.getLetter();
            type += edit.getEditType().substring(0,1);
        }

        //{a;h}, {a;e}, {a;l}, {a;l}, {a;o}
        return type + ": " + letters;

    }
}
