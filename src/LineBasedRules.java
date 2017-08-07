import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 */
public class LineBasedRules implements UndoRule
{
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();

        return !(lineChange);
    }
}
