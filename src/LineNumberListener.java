import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LineNumberListener implements DocumentListener {

    LineNumberingTextArea lineNumber;

    public LineNumberListener(LineNumberingTextArea lineNumberingTextArea){
        lineNumber = lineNumberingTextArea;
    }
    @Override
    public void insertUpdate(DocumentEvent documentEvent)
    {
        lineNumber.updateLineNumbers();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent)
    {
        lineNumber.updateLineNumbers();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent)
    {
        lineNumber.updateLineNumbers();
    }
}
