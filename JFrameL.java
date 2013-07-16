
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JFrameL extends JFrame
{
    /** Creates a new instance of JFrameL */
    public JFrameL(String title) {
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
    }
   private class FrameListener extends WindowAdapter
   {

    public void windowClosing(WindowEvent e) {
            //This will only be seen on standard output.
       int ans = JOptionPane.showConfirmDialog(null, "Would you like to save your account?");
       if(ans == JOptionPane.YES_OPTION)
    	   main.writeFile();
       System.exit(0);
    }
   }   
}
