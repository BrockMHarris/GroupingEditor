import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 */
public class TimeBasedRules implements UndoRule
{
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        boolean timeDiff = (edits.get(edits.size()-1).getTimecreated() - edits.get(edits.size()-2).getTimecreated()) > 900;

        return !(timeDiff);
    }
}
