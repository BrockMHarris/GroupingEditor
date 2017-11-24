/**
 * Created by harrisb on 7/17/17.
 * Group of TimeStampedEdits. This grouping is created based on similarities between the edits. they can be undone
 * all at once.
 */
class MyCompoundEdit extends javax.swing.undo.CompoundEdit
{
    public MyCompoundEdit()
    {
        super();
    }

    /**
     * Checks that all the edits in the group can be undone
     * @return true if they all cna be undone, false otherwise.
     */
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

    /**
     * Checks that all the edits in the group can be redone
     * @return true if they all cna be redone, false otherwise.
     */
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

    /**
     * @return string represent of the group of edits. in the format {a;h}, {a;e}, {a;l}, {a;l}, {a;o} where the first
     * part is the Type of edit  a: addition  d: deletion
     * the second part is the charactor that is being edited.
     */
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
