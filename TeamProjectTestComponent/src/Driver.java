import java.awt.*;
import javax.swing.*;

public class Driver {
    public static void main(String[] args){
        JFrame f = new JFrame("testFrame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NewPanel p = new NewPanel();

        f.getContentPane().add(p);

        f.pack();
        f.setVisible(true);

    }
}
