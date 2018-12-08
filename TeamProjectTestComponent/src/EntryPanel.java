import java.awt.*;
import javax.swing.*;

public class EntryPanel extends JPanel {
    private Point pt;
    private JLabel normal;
    private int velocity;
    private JLabel nameLabel;
    private EntryComponent E;

    public EntryPanel(int x, int y, EntryComponent E){
        pt = new Point();
        pt.x = x;
        pt.y = y;
        this.E = E;
        this.setBounds(pt.x,pt.y,700,800);
        setBackground(Color.BLACK);
        nameLabel = new JLabel(this.E.name);
        normal = new JLabel(this.E.image);
        velocity = 0;
        normal.setBounds(0,0,700,600);
        this.add(normal);

        nameLabel.setFont(new Font("Consolas", Font.BOLD, 40));
        nameLabel.setBounds(350,710,150,40);
        nameLabel.setForeground(Color.ORANGE);
        this.add(nameLabel);
    }

    public EntryPanel(){

    }

    public int getVelocity(){
        return this.velocity;
    }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    public int getPtX(){
        return this.pt.x;
    }

    public void setPtX(int x){
        this.pt.x = x;
    }

    public int getPtY(){
        return this.pt.y;
    }

    public void setPtY(int y){
        this.pt.y = y;
    }

    public Point getPt(){
        return this.pt;
    }
    
    public void setEntryComponent(EntryComponent param){
        this.E = param;
    }
    
    public EntryComponent getEntryComponent() {
    	return this.E;
    }
}
