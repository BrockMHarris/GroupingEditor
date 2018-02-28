package ProgPack;

import org.fife.ui.rsyntaxtextarea.AbstractJFlexCTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMaker;

public class MyDocument extends RSyntaxDocument
{

    public MyDocument(String syntaxStyle)
    {
        super(syntaxStyle);

    }

//    @Override
//    public boolean getShouldIndentNextLine(int line) {
//        Token t = getTokenListForLine(line);
//        t = t.getLastNonCommentNonWhitespaceToken();
//        if (t.toString().equals(":")){
//            return true;
//        }
//        else {
//            //return super.tokenMaker.getShouldIndentNextLineAfter(t);
//
//        }
//        //TokenMaker colon = getClosestStandardTokenTypeForInternalType();
//        //return tokenMaker.getShouldIndentNextLineAfter(t);
//    }
}
