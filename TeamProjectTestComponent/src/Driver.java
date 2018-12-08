import java.awt.*;
import javax.swing.*;

public class Driver {
    public static void main(String[] args){
        JFrame f = new JFrame("testFrame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UserDefinedInstalizingWorldCupPanel startPanel = new UserDefinedInstalizingWorldCupPanel();
        //frame.getContentPane().add(startPanel);
        PrimaryPanel primary = new PrimaryPanel();
        f.getContentPane().add(primary);

        //f.getContentPane().add(MGP);

        f.pack();
        f.setVisible(true);

    }
}
