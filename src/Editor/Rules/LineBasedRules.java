package Editor.Rules;

import Editor.TimeStampEdits;

import java.util.ArrayList;

/**
 * Created by harrisb on 7/19/17.
 * This is a way of grouping the edits so that edits that were made on the same line are grouped together.
 * If the edit is a \n or the line numbers of the edits are different
 */
public class LineBasedRules implements UndoRule
{
    /**
     * @param edits the sequential lsit of all the edits made in the document
     * @return true if the last edit should be in a new group. False otherwise
     */
    @Override
    public boolean withPrevious(ArrayList<TimeStampEdits> edits)
    {
        boolean isNewLine = edits.get(edits.size()-1).getText().contains("\n");
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();

        return (lineChange || isNewLine);
    }
}
