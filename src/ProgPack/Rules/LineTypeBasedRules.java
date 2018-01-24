package ProgPack.Rules;

import java.util.ArrayList;

/**
 * Created by harrisb on 7/27/17.
 * This is a way of grouping the edits so that edits that were made on the same line and are the same type are grouped together.
 * If the edit is a \n or the line numbers of the edits are different or the type of edit is different from the previous edit
 *
 * Type:
 * Addition - The edit was added to the document
 * Deletion - The edit was removed from the document
 *
 */
public class LineTypeBasedRules implements UndoRule
{
    /**
     * @param edits the sequential list of all the edits made in the document
     * @return true if the last edit should be in a new group. False otherwise
     */
    @Override
    public boolean withPrevious(ArrayList<ProgPack.TimeStampEdits> edits)
    {
        boolean lineChange = edits.get(edits.size()-1).getLineNumber() != edits.get(edits.size()-2).getLineNumber();
        boolean isDeletion = edits.get(edits.size()-1).getEditType().equals("deletion") && !edits.get(edits.size()-2).getEditType().equals("deletion");
        boolean isAddition = edits.get(edits.size()-1).getEditType().equals("addition") && !edits.get(edits.size()-2).getEditType().equals("addition");

        return !(lineChange || isDeletion || isAddition);
    }
}
