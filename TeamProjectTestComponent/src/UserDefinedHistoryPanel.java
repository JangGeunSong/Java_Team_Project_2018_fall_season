import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserDefinedHistoryPanel extends JPanel{

    private EntryPanel[] entryTree;
    //private JScrollPane scroll;

    final int compoWidth=100;
    final int compoHeight=100;
    final int gapOfCompoY=70;
    final int gapOfCompoX=20;
    final int centerX;
    int level;
    //0
    //1
    //2 3
    //4 5 6 7
    //8 9 10 11 12 13 14 15

    private int entryNum;
    private int heightBound, widthBound;

    void drawEntry() {

        int level=0;

        int tmpEntryNum=entryNum;
        while(tmpEntryNum!=2) {
            tmpEntryNum/=2;
            level++;
        }

        for(int i=entryNum/2;i<entryNum;i++) {
            entryTree[i].setLocation((gapOfCompoX+compoWidth)*(i-entryNum/2), level*(compoHeight+gapOfCompoY));
            //System.out.println(i+" "+(gapOfCompoX+compoWidth)*(i-entryNum/2)+" "+ level*(compoHeight+gapOfCompoY));
        }
        for(int i=entryNum/2-1;i>0;i--) {
            entryTree[i].setLocation((int) (entryTree[i * 2].getLocation().getX() + entryTree[i * 2 + 1].getLocation().getX()) / 2, (int) (entryTree[i * 2].getLocation().getY() - (compoHeight + gapOfCompoY)));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1,x2,y1,y2;
        for(int i=entryNum-1;i>1;i--) {
            x1=entryTree[i].getLocation().x+compoWidth/2;
            y1=entryTree[i].getLocation().y;
            x2=entryTree[i/2].getLocation().x+compoWidth/2;
            y2=entryTree[i/2].getLocation().y+compoHeight;
            g.drawLine(x1, y1, x2, y2);

        }
    }


    public UserDefinedHistoryPanel(int entryNum, EntryPanel root[]) {

        widthBound = 120 * ((entryNum+1)/2);
        heightBound = (int)(90 * (Math.sqrt((double)entryNum) + 3));

        //pane default set
        setLayout(null);
        setPreferredSize(new Dimension(widthBound,heightBound));
        setBackground(Color.white);

        //default location
        this.entryNum=entryNum;
        centerX=(int)getPreferredSize().getWidth()/2-compoWidth/2;

        //0
        //1
        //2 3
        //4 5 6 7
        //8 9 10 11 12 13 14 15

        //
        //scroll = new JScrollPane(this);
        entryTree = new EntryPanel[entryNum+1];

        //1: 0
        //2: -1 1
        //4: -2 -1 1 2
        //8 -3 -2 -1 1 2 3



        for(int i=1;i<entryNum;i++) {
            entryTree[i]=root[i];
            entryTree[i].setSize(new Dimension(compoWidth,compoHeight));
            entryTree[i].setBackground(Color.green);
            entryTree[i].add(new JLabel(Integer.toString(i)));
            add(entryTree[i]);
        }
        drawEntry();


    }

    public void setEntryTree(EntryPanel[] entryTree) {
        this.entryTree = entryTree;
    }
    
    public void applyChange(int index, EntryComponent E) {
    	this.entryTree[index].setEntryComponent(E);
    	this.repaint();
    }
    
}
