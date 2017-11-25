import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 * This is a way of grouping the edits so that edits that were made within a given time of each other are grouped together
 * if they are on different lines or they are of a different type they are automatically in separate groups
 *
 *
 * Type:
 * Addition - The edit was added to the document
 * Deletion - The edit was removed from the document
 *
 */
class TimeLineTypeBasedRules implements UndoRule
{

    private static final int threshold = 900;

    /**
     * @param edits the sequential list of all the edits made in the document
     * @return true if the last edit should be in a new group. False otherwise
     */
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        //boolean newLine = edits.get(editPointer).getLetter().contains("\n");
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();
        boolean timeDiff = (edits.get(edits.size()-1).getTimecreated() - edits.get(edits.size()-2).getTimecreated()) > threshold;
        boolean isDeletion = edits.get(edits.size()-1).getEditType().equals("deletion") && !edits.get(edits.size()-2).getEditType().equals("deletion");
        boolean isAddition = edits.get(edits.size()-1).getEditType().equals("addition") && !edits.get(edits.size()-2).getEditType().equals("addition");

        return !(timeDiff || lineChange || isDeletion || isAddition);
    }
}
