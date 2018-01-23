package Editor.LineNumber;

import Editor.Editor;

import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;

/**
 * Creates the line numbers in the window for each new line that is edited
 */
public class LineNumberingTextArea extends JTextArea
{
    private JTextPane textArea;

    /**
     * constructor
     * @param textArea the window that needs to be tracked for line numbers
     */
    public LineNumberingTextArea(JTextPane textArea)
    {
        this.textArea = textArea;
        setBackground(Color.LIGHT_GRAY);
        setEditable(false);
    }

    /**
     * adds the new line number to the window
     */
    public void updateLineNumbers()
    {
        String lineNumbersText = getLineNumbersText();
        setText(lineNumbersText);
    }

    /**
     * @return String with all the line numbers that are currently being used. with a 2 empty spaces after each number
     */
    private String getLineNumbersText()
    {
        int caretPosition = textArea.getDocument().getLength();
        Element root = textArea.getDocument().getDefaultRootElement();
        StringBuilder lineNumbersTextBuilder = new StringBuilder();
        lineNumbersTextBuilder.append("1  ").append(System.lineSeparator());

        for (int elementIndex = 2; elementIndex < root.getElementIndex(caretPosition) + 2; elementIndex++)
        {
            lineNumbersTextBuilder.append(elementIndex).append(System.lineSeparator());
        }

        return lineNumbersTextBuilder.toString();
    }
}
