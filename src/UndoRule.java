import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 */
interface UndoRule
{
    public boolean withPrevious(ArrayList<TimeStampEdits> edits);
}
