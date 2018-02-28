package ProgPack;

import org.fife.ui.rsyntaxtextarea.AbstractJFlexCTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMaker;

public class MyDocument extends RSyntaxDocument
{
    private AbstractJFlexCTokenMaker tokenMaker;

    public MyDocument(String syntaxStyle)
    {
        super(syntaxStyle);
    }

    @Override
    public boolean getShouldIndentNextLine(int line) {
        Token t = getTokenListForLine(line);
        t = t.getLastNonCommentNonWhitespaceToken();
        //TokenMaker colon = getClosestStandardTokenTypeForInternalType();
        return tokenMaker.getShouldIndentNextLineAfter(t);
    }
}
