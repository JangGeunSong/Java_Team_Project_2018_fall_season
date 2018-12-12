import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserDefinedHistoryFrame extends JFrame{

    private UserDefinedHistoryPanel primary;
    private EntryPanel Tree[];
    private JScrollPane p;
    public UserDefinedHistoryFrame(int entryNum, EntryComponent[] parameter) {
        // TODO Auto-generated constructor stub
        Tree = new EntryPanel[entryNum + 1];

        this.setPreferredSize(new Dimension(144*10, 90*10));

        for(int i = 1 ; i < entryNum; i++){
            Tree[i] = new EntryPanel(0,0,parameter[i]);
        }

        primary = new UserDefinedHistoryPanel(entryNum, Tree);
        add(primary);

        p = new JScrollPane(primary);
        add(p);

        pack();
        setVisible(true);
    }

    public void reNewTree(EntryComponent[] parameter){
        int k = parameter.length;
        for(int i = 0; i < k; i++){
            Tree[i].setEntryComponent(parameter[i]);
        }
        primary.setEntryTree(Tree);
    }
    
    public void sendInformation(int index, EntryComponent E) {
    	this.primary.applyChange(index, E);
    	this.primary.repaint();
    }
}
