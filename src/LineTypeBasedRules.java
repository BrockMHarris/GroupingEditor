import java.util.ArrayList;

/**
 * Created by harrisb on 7/27/17.
 */
public class LineTypeBasedRules implements UndoRule
{
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();
        boolean isDeletion = edits.get(edits.size()-1).getEditType().equals("deletion") && !edits.get(edits.size()-2).getEditType().equals("deletion");
        boolean isAddition = edits.get(edits.size()-1).getEditType().equals("addition") && !edits.get(edits.size()-2).getEditType().equals("addition");

        return !(lineChange || isDeletion || isAddition);
    }
}
