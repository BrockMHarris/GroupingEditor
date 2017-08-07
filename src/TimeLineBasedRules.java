import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 */
public class TimeLineBasedRules implements UndoRule
{
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();
        //boolean lineChange = edits.get(edits.size()-1).getLetter().contains("\n");
        boolean timeDiff = (edits.get(edits.size()-1).getTimecreated() - edits.get(edits.size()-2).getTimecreated()) > 900;
        //if(edits.get(edits.size()-1).getLetter().equals("\n")){}

        return !(timeDiff || lineChange);
    }
}
