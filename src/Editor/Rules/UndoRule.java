package Editor.Rules;


import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 * Interface for all grouping rules
 */
public interface UndoRule
{
    /**
     * determines if the current edit should be placed in the same group as the previous edit or a new one
     * @param edits list of all edits that have happened
     * @return True if it should be grouped with previous edits. False otherwise
     */
    public boolean withPrevious(ArrayList<Editor.TimeStampEdits> edits);
}
