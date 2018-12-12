import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainGamePanel extends JPanel{
    private EntryPanel leftPanel, rightPanel;
    //
    private JButton btnH;
    //
    private ButtonListener btnL;
    //
    private PanelClickDetect dteP;
    //
    private Timer aniTimer, resetTimer;
    //
    private String Type;
    //
    private int nRound, nElem, nWinner, nNextMatch, rBound, signal;
    //
    private EntryComponent initialComponent;
    private EntryComponent[] eTree;
    private EntryPanel[] Tree;
    //
    private UserDefinedHistoryFrame UDHF;
    private getImgRes imString;
    private PrimaryPanel parentPanel;
    private EndingPanel EDP;


    public MainGamePanel(String Type, int Round, PrimaryPanel parent) {
        int i = 0, j = 0;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(1440,900));
        this.setLayout(null);

        dteP = new PanelClickDetect();

        this.Type = Type;
        this.nRound = Round;
        this.nElem = (this.nRound * 2) - 1;
        this.nNextMatch = nElem;
        this.rBound = 1;
        this.signal = 0;
        this.parentPanel = parent;
        this.initialComponent = new EntryComponent("Images/QuestionMark/a.jpg","Images/QuestionMark/a.jpg","Who?", 0);

        if(Type == "여자") {
        	imString = new getImgRes("src/code.txt", nRound);
        }
        else if(Type == "남자"){
        	imString = new getImgRes("src/code1.txt", nRound);
        }

        eTree = new EntryComponent[nElem + 1];
        Tree = new EntryPanel[nElem + 1];

        for (i = nElem; i > 0; i--){
            if(i > nElem/2) {
                eTree[i] = imString.getRandomImgList()[j];
                j++;
            }
            else{
                eTree[i] = this.initialComponent;
            }
        }

        for(i = nElem; i > 0; i--) {
            Tree[i] = new EntryPanel(10,60,eTree[i]);
        }

        for(i = 1; i < nElem + 1; i++){
            Tree[i].addMouseListener(dteP);
        }

        UDHF = new UserDefinedHistoryFrame(nElem + 1, eTree);
        UDHF.setTitle("현재상황");
        UDHF.setVisible(false);

        System.out.println(this.Type);
        System.out.println(this.nRound);

        leftPanel = Tree[nNextMatch - 1];
        leftPanel.setPtX(10);
        leftPanel.setPtY(60);
        leftPanel.setLocation(leftPanel.getPtX(),leftPanel.getPtY());
        leftPanel.repaint();
        this.add(leftPanel);
        //leftPanel.addMouseListener(dteP);

        rightPanel = Tree[nNextMatch];
        rightPanel.setPtX(720);
        rightPanel.setPtY(60);
        rightPanel.setLocation(rightPanel.getPtX(),rightPanel.getPtY());
        rightPanel.repaint();
        this.add(rightPanel);
        //rightPanel.addMouseListener(dteP);

        btnH = new JButton("HISTORY");
        btnH.setBounds(1200,10,200,40);
        btnH.setFont(new Font("Consolas", Font.BOLD, 30));
        btnH.setForeground(Color.WHITE);
        btnH.setBackground(Color.BLACK);
        this.add(btnH);

        btnL = new ButtonListener();
        btnH.addActionListener(btnL);

    }

    public void reset(int nLeft, int nRight, int signal, EntryComponent E) {
        if(signal == 0) {
            leftPanel.setVisible(false);
            rightPanel.setVisible(false);
            leftPanel = null;
            leftPanel = Tree[nLeft];
            leftPanel.setPtX(10);
            leftPanel.setPtY(60);
            leftPanel.setLocation(leftPanel.getPtX(), leftPanel.getPtY());
            //leftPanel.addMouseListener(dteP);
            this.add(leftPanel);
            leftPanel.setVisible(true);
            rightPanel = null;
            rightPanel = Tree[nRight];
            rightPanel.setPtX(720);
            rightPanel.setPtY(60);
            rightPanel.setLocation(rightPanel.getPtX(), rightPanel.getPtY());
            //rightPanel.addMouseListener(dteP);
            this.add(rightPanel);
            rightPanel.setVisible(true);
            this.repaint();
        }
        else{
        	this.parentPanel.disableMGP();
        	this.parentPanel.addEDP(E, parentPanel);
        }
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object obj = e.getSource();
            if(obj == btnH){
            	new UserDefinedHistoryFrame(nElem + 1, eTree);
            }
        }
    }

    private class PanelClickDetect implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e){
            Object obj = e.getSource();
            if(obj == rightPanel){
                nWinner = nNextMatch;
                Tree[(nNextMatch)/2] = rightPanel;
                eTree[(nNextMatch)/2] = rightPanel.getEntryComponent();
                UDHF.sendInformation(nNextMatch/2, rightPanel.getEntryComponent());
                nNextMatch = nNextMatch - 2;
                rightPanel.setVelocity(8);
                leftPanel.setVisible(false);
                //Tree.get(nNextMatch/2).add(Tree.get(nWinner));
                aniTimer = new Timer(1, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        aniTimer.start();
                        if(rightPanel.getPtX() > 400){
                            rightPanel.setPtX(rightPanel.getPtX() - rightPanel.getVelocity());
                            rightPanel.setLocation(rightPanel.getPtX(), rightPanel.getPtY());
                            rightPanel.repaint();
                            aniTimer.setRepeats(true);
                        }
                        else if(rightPanel.getPtX() <= 400){
                            resetTimer = new Timer(2500, new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    resetTimer.start();
                                    if((nNextMatch)/2 == 0){
                                        System.out.println("Panel Change!");
                                        signal = 1;
                                    }
                                    reset(nNextMatch - 1, nNextMatch, signal, eTree[1]);
                                    resetTimer.stop();
                                }
                            });
                            resetTimer.start();
                            aniTimer.stop();
                        }
                    }
                });
                aniTimer.start();
            }
            else if(obj == leftPanel){
                nWinner = nNextMatch + 1;
                Tree[(nNextMatch)/2] = leftPanel;
                eTree[(nNextMatch)/2] = leftPanel.getEntryComponent();
                UDHF.sendInformation(nNextMatch/2, leftPanel.getEntryComponent());
                nNextMatch = nNextMatch - 2;
                leftPanel.setVelocity(8);
                rightPanel.setVisible(false);
                //Tree.get(nNextMatch/2).add(Tree.get(nWinner));
                aniTimer = new Timer(1, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        aniTimer.start();
                        if(leftPanel.getPtX() < 400){
                            leftPanel.setPtX(leftPanel.getPtX() + leftPanel.getVelocity());
                            leftPanel.setLocation(leftPanel.getPtX(), leftPanel.getPtY());
                            leftPanel.repaint();
                            aniTimer.setRepeats(true);
                        }
                        else if(leftPanel.getPtX() >= 400){
                            resetTimer = new Timer(2500, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent a){
                                    resetTimer.start();
                                    if((nNextMatch)/2 == 0){
                                        System.out.println("Panel Change!");
                                        signal = 1;
                                    }
                                    reset(nNextMatch - 1, nNextMatch, signal, eTree[1]);
                                    resetTimer.stop();
                                }
                            });
                            resetTimer.start();
                            aniTimer.stop();
                        }
                    }
                });
                aniTimer.start();
            }
        }
        @Override
        public void mousePressed(MouseEvent e){

        }
        @Override
        public void mouseReleased(MouseEvent e){

        }
        @Override
        public void mouseEntered(MouseEvent e){

        }
        @Override
        public void mouseExited(MouseEvent e){

        }
    }
}
