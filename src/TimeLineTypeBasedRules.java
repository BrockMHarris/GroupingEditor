import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 */
public class TimeLineTypeBasedRules implements UndoRule
{

    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        //boolean newLine = edits.get(editPointer).getLetter().contains("\n");
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();
        boolean timeDiff = (edits.get(edits.size()-1).getTimecreated() - edits.get(edits.size()-2).getTimecreated()) > 900;
        boolean isDeletion = edits.get(edits.size()-1).getEditType().equals("deletion") && !edits.get(edits.size()-2).getEditType().equals("deletion");
        boolean isAddition = edits.get(edits.size()-1).getEditType().equals("addition") && !edits.get(edits.size()-2).getEditType().equals("addition");

        return !(timeDiff || lineChange || isDeletion || isAddition);
    }
}
