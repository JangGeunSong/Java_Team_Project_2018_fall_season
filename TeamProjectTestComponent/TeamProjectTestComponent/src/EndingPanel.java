import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EndingPanel extends JPanel{

    private JPanel lPanel, rPanel;
    private JLabel ImageLabel;

    private JLabel rankTitle;

    private JLabel [] rank;

    private ImageIcon winnerIcon;
    private JLabel winnerLabel;


    public EndingPanel() {


    }

    public EndingPanel(PrimaryPanel parent) {

        this.setPreferredSize(new Dimension(1440,900));
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBounds(0,0,1440,900);

        lPanel = new JPanel();
        lPanel.setBounds(220,100,475,700);
        lPanel.setBackground(Color.black);
        lPanel.setLayout(null);

        this.add(lPanel);

        rPanel = new JPanel();
        rPanel.setBounds(745,100,475,700);
        rPanel.setBackground(Color.black);
        rPanel.setLayout(null);

        this.add(rPanel);

        rankTitle = new JLabel("역대 순위");
        rankTitle.setBounds(0,0,475,100);
        rankTitle.setForeground(Color.WHITE);
        rankTitle.setFont(new Font("궁서체", Font.BOLD,60));
        rankTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rPanel.add(rankTitle);

        rank = new JLabel [5];

        for(int i =0 ; i<5 ; i++) {
            rank[i] = new JLabel((i+1)+". ");
            rank[i].setBounds(0,(i+1)*100,475,100);
            rank[i].setFont(new Font("궁서체", Font.BOLD, 40));
            rank[i].setForeground(Color.WHITE);
            rPanel.add(rank[i]);
        }

        winnerIcon = new ImageIcon("imges/1432175070768.jpg");

        winnerLabel = new JLabel(winnerIcon);
        winnerLabel.setBounds(0,0,475,700);
        //winnerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lPanel.add(winnerLabel);

        parent.add(this);
    }


}
