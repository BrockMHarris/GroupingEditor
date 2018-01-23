package Editor.LineNumber;

import Editor.Editor;
import Editor.LineNumber.LineNumberingTextArea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Listener on the JTextPane that updates the line numbers after every edit
 */
public class LineNumberListener implements DocumentListener {

    LineNumberingTextArea lineNumber;

    /**
     * constructor
     * @param lineNumberingTextArea the object that is charge of updating the line numbers for the JTextPane
     */
    public LineNumberListener(LineNumberingTextArea lineNumberingTextArea){
        lineNumber = lineNumberingTextArea;
    }

    /**
     * listens for insertions in the document and updates the line number
     * @param documentEvent insertion event
     */
    @Override
    public void insertUpdate(DocumentEvent documentEvent)
    {
        lineNumber.updateLineNumbers();
    }

    /**
     * listens for removal in the document and updates the line number
     * @param documentEvent removal event
     */
    @Override
    public void removeUpdate(DocumentEvent documentEvent)
    {
        lineNumber.updateLineNumbers();
    }

    /**
     * listens for a change in the document and updates the line number
     * @param documentEvent change event
     */
    @Override
    public void changedUpdate(DocumentEvent documentEvent)
    {
        lineNumber.updateLineNumbers();
    }
}
