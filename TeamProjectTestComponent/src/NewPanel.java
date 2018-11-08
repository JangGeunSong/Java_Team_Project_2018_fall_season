import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class NewPanel extends JPanel{
    private JPanel panel1, panel2;
    private JLabel lbl1, lbl2;
    private JLabel lbl3, lbl4;
    private ImageIcon icon1, icon2;
    private ImageIcon icon3, icon4;
    private DefinedMouseEvent DME1, DME2;

    public NewPanel(){
        setLayout(null);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(1024,720));

        panel1 = new JPanel();
        panel1.setBackground(Color.BLACK);
        panel1.setBounds(10,10,500,600);

        panel2 = new JPanel();
        panel2.setBackground(Color.BLACK);
        panel2.setBounds(520,10,500,600);

        icon1 = new ImageIcon("Images/Sample1.jpg");
        icon2 = new ImageIcon("Images/Sample2.jpg");
        icon3 = new ImageIcon("Images/Sample3.jpg");
        icon4 = new ImageIcon("Images/Sample4.jpg");

        lbl1 = new JLabel(icon1);
        panel1.add(lbl1);

        DME1 = new DefinedMouseEvent(lbl1, icon1, icon2);
        panel1.addMouseListener(DME1);

        lbl2 = new JLabel(icon3);
        panel2.add(lbl2);

        DME2 = new DefinedMouseEvent(lbl2, icon3, icon4);
        panel2.addMouseListener(DME2);

        lbl3 = new JLabel("슬기");
        lbl4 = new JLabel("은하");

        lbl3.setFont(new Font("궁서체", Font.BOLD, 20));
        lbl4.setFont(new Font("궁서체", Font.BOLD, 20));

        lbl3.setBounds(245,600,100,100);
        lbl4.setBounds(745,600,100,100);

        lbl3.setForeground(Color.ORANGE);
        lbl4.setForeground(Color.ORANGE);

        this.add(panel2);
        this.add(panel1);
        this.add(lbl3);
        this.add(lbl4);
    }

    private class DefinedMouseEvent implements MouseListener{

        private ImageIcon icon1, icon2;
        private JLabel J;

        DefinedMouseEvent(JLabel J, ImageIcon icon1, ImageIcon icon2){
            this.icon1 = icon1;
            this.icon2 = icon2;
            this.J = J;
        }

        @Override
        public void mouseClicked(MouseEvent e){

        }

        @Override
        public void mouseEntered(MouseEvent e){
            J.setIcon(icon2);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            J.setIcon(icon1);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }

}
