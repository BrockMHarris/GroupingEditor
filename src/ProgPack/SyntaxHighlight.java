package ProgPack;

import javax.swing.*;
import javax.swing.text.Highlighter;

public class SyntaxHighlight {

    private JTextPane pane;
    private String text;
    static String[] RESERVEDWORDS = {"and", "assert", "break", "class", "continue", "def", "del",
            "elif",	"else",	"except", "exec", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda",
            "not", "or", "pass", "print", "raise", "return", "try", "while"};

    public SyntaxHighlight(JTextPane textArea){
        this.pane = textArea;
        text = pane.getText();
    }

    public void changeColor(){
        for(int i = 0; i < text.length(); i++){

        }
    }
}
