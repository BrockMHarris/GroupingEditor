import javax.swing.*;

/**
 * Created by harrisb on 7/25/17.
 */
class MyBackroundMethod extends Thread
{
    private JButton undo;
    private JButton redo;
    private Editor app;

    MyBackroundMethod(JButton Undo, JButton Redo, Editor app){
        undo = Undo;
        redo = Redo;
        this.app = app;
    }
    public void run()
    {
        while (true)
        {
            //System.out.println("thread");
            undo.setEnabled(app.canUndo());
            redo.setEnabled(app.canRedo());
            //System.out.println(app.canRedo());
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //if(app.getDocument().getLength()>3){
            //    app.setCaretPosition(2);
            //}
        }
    }
}
