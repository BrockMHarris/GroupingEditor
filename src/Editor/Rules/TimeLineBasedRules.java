package Editor.Rules;

import Editor.*;



/**
 * Created by harrisb on 7/19/17.
 * This is a way of grouping the edits so that edits that were made within a given time of each other are grouped together
 * if they are on different lines they are automatically in separate groups
 */
public class TimeLineBasedRules implements Editor.UndoRule
{
    private static final int threshold = 900;

    /**
     * @param edits the sequential list of all the edits made in the document
     * @return true if the last edit should be in a new group. False otherwise
     */
    @Override
    public boolean withPrevious(ArrayList<Editor.TimeStampEdits> edits)
    {
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();
        //boolean lineChange = edits.get(edits.size()-1).getLetter().contains("\n");
        boolean timeDiff = (edits.get(edits.size()-1).getTimecreated() - edits.get(edits.size()-2).getTimecreated()) > threshold;
        //if(edits.get(edits.size()-1).getLetter().equals("\n")){}

        return !(timeDiff || lineChange);
    }
}
