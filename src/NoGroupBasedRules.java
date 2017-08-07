import java.util.ArrayList;

/**
 * Created by harrisb on 7/27/17.
 */
public class NoGroupBasedRules implements UndoRule
{

    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        return false;
    }
}
