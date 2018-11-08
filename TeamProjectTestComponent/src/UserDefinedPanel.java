import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserDefinedPanel extends JPanel {
    private JLabel objName, objShowing;
    private ImageIcon objImage1, objImage2;
    private int objVelocity;
    private int xPoint, yPoint;
    private DefinedMouseEvent DME;

    public UserDefinedPanel(String str, ImageIcon icon1, ImageIcon icon2){
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(500,600));

        DME = new DefinedMouseEvent();

        this.objName = new JLabel(str);
        objName.setForeground(Color.ORANGE);

        this.objImage1 = icon1;
        this.objImage2 = icon2;
        this.objShowing = new JLabel(this.objImage1);
        this.objVelocity = 0;

        add(objName);
        add(objShowing);





    }



    public class DefinedMouseEvent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e){

        }

        @Override
        public void mouseEntered(MouseEvent e){
            objShowing.setIcon(objImage2);
        }

        @Override
        public void mouseExited(MouseEvent e){
            objShowing.setIcon((objImage1));
        }

        @Override
        public void mousePressed(MouseEvent e){

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }


}
