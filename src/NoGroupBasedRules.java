import java.util.ArrayList;

/**
 * Created by harrisb on 7/27/17.
 * This is a way of grouping the edits so that every edit is in its own group. When undone every edit is done individually
 */
class NoGroupBasedRules implements UndoRule
{
    /**
     * @param edits the sequential list of all the edits made in the document
     * @return false
     */
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        return false;
    }
}
