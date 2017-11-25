import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 * This is a way of grouping the edits so that edits that were made whithin a givin time of each other are grouped together
 */
class TimeBasedRules implements UndoRule
{
    private static final int threshold = 900;

    /**
     * @param edits the sequential list of all the edits made in the document
     * @return true if the last edit should be in a new group. False otherwise
     */
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        boolean timeDiff = (edits.get(edits.size()-1).getTimecreated() - edits.get(edits.size()-2).getTimecreated()) > threshold;

        return !(timeDiff);
    }
}
