import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UserDefinedInstalizingWorldCupPanel extends JPanel {

    private JPanel sexRadioPanel,scaleRadioPanel;

    private ButtonGroup sexLadioButtonGroup;
    private ButtonGroup scaleLadioButtonGroup;

    private JRadioButton [] sexRadioButton;
    private JRadioButton [] scaleRadioButton;

    private String [] sexText = {"남자", "여자"};
    private String [] scaleText = {"8강", "16강", "32강"};

    ///패널구성요소
    private JLabel titleLabel;
    private JButton startButton;
    private int isStartButtonClicked;
    private PrimaryPanel parentPanel;
    private ImageIcon backGround;
    ////




    private BtnListener btnL;

    public UserDefinedInstalizingWorldCupPanel(){

    } //UserDefinedInstalizingWorldCupPanel

    public UserDefinedInstalizingWorldCupPanel(PrimaryPanel parent) {
        parentPanel = parent;//
        setBackground(Color.white);
        setPreferredSize(new Dimension(1440, 900));
        setLayout(null);
        setBounds(0,0,1440,900);
        
        backGround = new ImageIcon("Images/BackGround/BackGround.jpg");

        //
        titleLabel = new JLabel("이상형월드컵");
        titleLabel.setPreferredSize(new Dimension(500,50));
        titleLabel.setFont(new Font("", Font.BOLD, 55));

        titleLabel.setBounds(550,100,500,50);

        this.add(titleLabel);


        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(300,50));
        startButton.setBounds(580,700,300,50);
        startButton.setFont(new Font("Verdana",Font.ITALIC,30));

        startButton.setBackground(Color.CYAN);
        btnL = new BtnListener();
        //btnL = new ButtonListener();
        startButton.addActionListener(btnL);

        this.add(startButton);


        isStartButtonClicked =0;

        //



        sexRadioPanel = new JPanel();
        sexRadioPanel.setBackground(Color.white);
        //sexRadioPanel.setOpaque(false);
        sexRadioPanel.setBounds(200, 300, 130, 250);
        //sexRadioPanel.setLayout(null);
        add(sexRadioPanel);

        scaleRadioPanel = new JPanel();
        scaleRadioPanel.setBackground(Color.white);
        //scaleRadioPanel.setOpaque(false);
        scaleRadioPanel.setBounds(1040, 300, 130, 300);
        //scaleRadioPanel.setLayout(null);
        add(scaleRadioPanel);




        sexLadioButtonGroup = new ButtonGroup();
        scaleLadioButtonGroup = new ButtonGroup();

        sexRadioButton = new JRadioButton [2];
        scaleRadioButton = new JRadioButton [3];


        sexRadioPanel.setLayout(new GridLayout(2, 1));
        scaleRadioPanel.setLayout(new GridLayout(3, 1));

        for(int i=0;i<sexRadioButton.length;i++)
        {
            sexRadioButton[i] = new JRadioButton(sexText[i]);
            sexLadioButtonGroup.add(sexRadioButton[i]);
            sexRadioPanel.add(sexRadioButton[i],BorderLayout.CENTER);
            sexRadioButton[i].addItemListener(btnL);
            sexRadioButton[i].setFont(new Font("",Font.BOLD, 40));
            sexRadioButton[i].setBackground(Color.white);
        }

        for(int i=0;i<scaleRadioButton.length;i++)
        {
            scaleRadioButton[i] = new JRadioButton(scaleText[i]);
            scaleLadioButtonGroup.add(scaleRadioButton[i]);
            scaleRadioPanel.add(scaleRadioButton[i],BorderLayout.CENTER);
            scaleRadioButton[i].addItemListener(btnL);
            scaleRadioButton[i].setFont(new Font("",Font.BOLD, 40));
            scaleRadioButton[i].setBackground(Color.white);
        }

        sexRadioButton[0].setSelected(true);
        scaleRadioButton[0].setSelected(true);

        //this.add(sexRadioPanel,BorderLayout.NORTH);
        //this.add(scaleRadioPanel,BorderLayout.NORTH);

        setVisible(true);

    } //UserDefinedInstalizingWorldCupPanel()


    //占쎌겱域뱀눖占쏙옙
    public int GetIsStartButtonClicked() {
        return isStartButtonClicked;
    }
    //

    
    public void paintComponenet(Graphics g) {
    	super.paintComponents(g);
    	g.drawImage(backGround.getImage(), 0, 0, null);
    }

    private class BtnListener implements ActionListener, ItemListener{

        public void actionPerformed(ActionEvent event) {
            Object obj = event.getSource();
            if(obj == startButton) {
                parentPanel.disableUDIpanel();
                parentPanel.addMGP();
            }



        } //actionPerformed()

        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub

            if(e.getStateChange()==ItemEvent.DESELECTED)
                return;
            if(sexRadioButton[0].isSelected()) //남자를 선택했을때
            {
                parentPanel.setnSex("남자");
            }
            else if(sexRadioButton[1].isSelected()) //여자를 선택했을때
            {
                parentPanel.setnSex("여자");
            }
            if(scaleRadioButton[0].isSelected())//8강을 선택했을때
            {
                parentPanel.setnRound(8);
            }
            else if(scaleRadioButton[1].isSelected())//16강을 선택했을때
            {
                parentPanel.setnRound(16);
            }
            else if(scaleRadioButton[2].isSelected())//32강을 선택했을때
            {
                parentPanel.setnRound(32);
            }

        } //itemStateChanged()
    } //BtnListener class

} //UserDefinedInstalizingWorldCupPanel class
//change MS949
