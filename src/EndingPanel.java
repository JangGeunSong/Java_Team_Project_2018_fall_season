import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class EndingPanel extends JPanel{

    private JPanel lPanel, rPanel;
    private JLabel ImageLabel;
    private PrimaryPanel parent;
    private JButton backToMain;
    private EndingButtonListener btnE;

    private JLabel rankTitle;

    private JLabel [] rank;

    private ImageIcon winnerIcon;
    private JLabel winnerLabel;


    public EndingPanel() {


    }

    public EndingPanel(EntryComponent E, PrimaryPanel parent) {

        this.setPreferredSize(new Dimension(1440,900));
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBounds(0,0,1440,900);
        this.parent = parent;

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

        rankTitle = new JLabel("ÃÖÁ¾°á°ú");
        rankTitle.setBounds(0,0,475,100);
        rankTitle.setForeground(Color.WHITE);
        rankTitle.setFont(new Font("±Ã¼­Ã¼", Font.BOLD,60));
        rankTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rPanel.add(rankTitle);

        rank = new JLabel [5];

        for(int i =0 ; i<5 ; i++) {
            rank[i] = new JLabel((i+1)+". ");
            rank[i].setBounds(0,(i+1)*100,475,100);
            rank[i].setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 40));
            rank[i].setForeground(Color.WHITE);
            rPanel.add(rank[i]);
        }

        winnerIcon = E.image;

        winnerLabel = new JLabel(winnerIcon);
        winnerLabel.setBounds(0,0,475,700);
        //winnerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lPanel.add(winnerLabel);

        this.backToMain = new JButton("Back To Main");
        this.backToMain.setPreferredSize(new Dimension(400,75));
        this.backToMain.setFont(new Font("Consolas", Font.BOLD, 30));
        this.backToMain.setBackground(Color.WHITE);
        this.backToMain.setForeground(Color.ORANGE);
        this.backToMain.setBounds(1000,800,400,75);
        this.backToMain.setVisible(true);
        this.btnE = new EndingButtonListener();
        this.backToMain.addActionListener(btnE);
        this.add(backToMain);


    }

    private class EndingButtonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e){
          Object obj = e.getSource();
          if(obj == backToMain){
             parent.disableEndingPanel();
             parent.enableUDIpanel();
          }
      }
    }

}
