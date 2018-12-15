import javax.swing.*;               //필요한 외부 클래스들 import   
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class UserDefinedInstalizingWorldCupPanel extends JPanel {   //JPanel을 상속받아 Ending패널 제작

    private JPanel sexRadioPanel,scaleRadioPanel;         //라디오버튼 올릴 패널

    private ButtonGroup sexLadioButtonGroup;            //라디오버튼 그룹(성별)
    private ButtonGroup scaleLadioButtonGroup;            //          (규모)

    private JRadioButton [] sexRadioButton;               //라디오버튼 배열(성별)
    private JRadioButton [] scaleRadioButton;            //           (규모)

    private String [] sexText = {"남자", "여자"};            //라디오버튼 이름에 넣을 텍스트 배열
    private String [] scaleText = {"8강", "16강", "32강"};

    
    private JLabel titleLabel;            //제목라벨
    private JButton startButton;         //게임스타트버튼
    private PrimaryPanel parentPanel;      //생성자 파라미터로 받을 PrimaryPanel객체
    private ImageIcon backGround;         //배경이미지 받는 ImageIcon
    private JLabel BGImage;				//배경이미지를 화면에 출력하기 위한 JLabel
   
    private BtnListener btnL;            //버튼리스너

    public UserDefinedInstalizingWorldCupPanel(){ //기본생성자

    } //UserDefinedInstalizingWorldCupPanel

    public UserDefinedInstalizingWorldCupPanel(PrimaryPanel parent) { //파라미터 생성자
        parentPanel = parent;                         //파라미터로 받은 PrimaryPanel 객체
        setBackground(Color.white);                     //배경색
        setPreferredSize(new Dimension(1440, 900));         //Panel 기본크기 설정   
        setLayout(null);                           //레이아웃null설정
        setBounds(0,0,1440,900);                     //크기 설정
        
        backGround = new ImageIcon("Images/BackGround/BackGround.jpg");    //배경이미지 생성

       
        titleLabel = new JLabel("이상형월드컵");                  //제목라벨 생성
        titleLabel.setPreferredSize(new Dimension(500,50));         //기본 크기 설정
        titleLabel.setFont(new Font("", Font.BOLD, 55));         //폰트설정
        titleLabel.setBounds(550,100,500,50);                  //크기설정
        this.add(titleLabel);                              //패널에 제목라벨 add

        startButton = new JButton("Start");                     //시작버튼생성
        startButton.setPreferredSize(new Dimension(300,50));      //기본 크기설정
        startButton.setBounds(580,700,300,50);                  //크기설정
        startButton.setFont(new Font("Verdana",Font.ITALIC,30));   //폰트설정

        startButton.setBackground(Color.CYAN);                  //버튼배경색설정
        btnL = new BtnListener();                           //버튼리스너생성
        startButton.addActionListener(btnL);                  //스타트버튼에 리스너 add
        this.add(startButton);                              //패널에 add

        sexRadioPanel = new JPanel();                        //성별 라디오버튼 올릴 패널 생성
        sexRadioPanel.setBackground(Color.white);               //배경색 설정
        sexRadioPanel.setBounds(200, 300, 130, 250);            //크기설정
        add(sexRadioPanel);                                 //패널에 add

        scaleRadioPanel = new JPanel();                        //규모 라디오버튼 올릴 패널 생성
        scaleRadioPanel.setBackground(Color.white);               //배경색설정
        scaleRadioPanel.setBounds(1040, 300, 130, 300);            //크기설정
        add(scaleRadioPanel);                              //패널에 add

        sexLadioButtonGroup = new ButtonGroup();               //성별라디오버튼 그룹생성
        scaleLadioButtonGroup = new ButtonGroup();               //규모라디오버튼 그룹생성

        sexRadioButton = new JRadioButton [2];                  //성별라디오버튼 배열선언
        scaleRadioButton = new JRadioButton [3];               //규모라디오버튼 배열선언


        sexRadioPanel.setLayout(new GridLayout(2, 1));            //성별라디오버튼 레이아웃설정
        scaleRadioPanel.setLayout(new GridLayout(3, 1));         //규모라디오버튼 레이아웃설정

        for(int i=0;i<sexRadioButton.length;i++)   //성별
        {
            sexRadioButton[i] = new JRadioButton(sexText[i]);            //라디오버튼 생성
            sexLadioButtonGroup.add(sexRadioButton[i]);                  //그룹에 추가
            sexRadioPanel.add(sexRadioButton[i],BorderLayout.CENTER);      //패널에 추가
            sexRadioButton[i].addItemListener(btnL);                  //리스너 추가
            sexRadioButton[i].setFont(new Font("",Font.BOLD, 40));         //폰트설정
            sexRadioButton[i].setBackground(Color.white);               //배경설정
        } //for 

        for(int i=0;i<scaleRadioButton.length;i++)   //규모
        {
            scaleRadioButton[i] = new JRadioButton(scaleText[i]);         //라디오버튼 생성 
            scaleLadioButtonGroup.add(scaleRadioButton[i]);               //그룹에 추가
            scaleRadioPanel.add(scaleRadioButton[i],BorderLayout.CENTER);   //패널에 추가
            scaleRadioButton[i].addItemListener(btnL);                  //리스너 추가
            scaleRadioButton[i].setFont(new Font("",Font.BOLD, 40));      //폰트설정
            scaleRadioButton[i].setBackground(Color.white);               //배경설정
        } //for

        sexRadioButton[0].setSelected(true);            //라디오버튼 기본 선택값설정
        scaleRadioButton[0].setSelected(true);            //라디오버튼 기본 선택값설정
        
        BGImage = new JLabel(backGround);				//이미지 라벨을 생성 사용할 이미지는 처음에 얻어온 backGround
        BGImage.setBounds(0,0,1440,900);				//이미지 라벨은 배경화면이므로 패널의 사이즈와 똑같이 setBounds함
        this.add(BGImage);								//이미지 라벨을 이 패널에 애드함
     
        setVisible(true);                           //생성시 보이도록 설정

    } //UserDefinedInstalizingWorldCupPanel()


   

    
    public void paintComponenet(Graphics g) {   
       super.paintComponents(g);
       g.drawImage(backGround.getImage(), 0, 0, null);   //배경 그리기
    }

    private class BtnListener implements ActionListener, ItemListener{

        public void actionPerformed(ActionEvent event) {
            Object obj = event.getSource();
            if(obj == startButton) {                  //시작버튼 누를시
                parentPanel.disableUDIpanel();            //PrimaryPanel의 함수 실행하여 setVisible(false)로
                try {
               parentPanel.addMGP();               //MainGame을 생성해준다
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            }
        } //actionPerformed()

        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub

            if(e.getStateChange()==ItemEvent.DESELECTED)
                return;
            if(sexRadioButton[0].isSelected()) //남자선택
            {
                parentPanel.setnSex("남자");   //PrimaryPanel의 성별성정을 남자로 변경
            }
            else if(sexRadioButton[1].isSelected()) //여자선택
            {
                parentPanel.setnSex("여자");   //PrimaryPanel의 성별성정을 여자로 변경
            }
            if(scaleRadioButton[0].isSelected())//8강선택
            {
                parentPanel.setnRound(8);   //PrimaryPanel의 라운드설정을 8강으로 변경
            }
            else if(scaleRadioButton[1].isSelected())//16강선택
            {
                parentPanel.setnRound(16);   //PrimaryPanel의 라운드설정을 16강으로 변경
            }
            else if(scaleRadioButton[2].isSelected())//32강선택
            {
                parentPanel.setnRound(32);   //PrimaryPanel의 라운드설정을 32강으로 변경
            }

        } //itemStateChanged()
    } //BtnListener class

} //UserDefinedInstalizingWorldCupPanel class
//change MS949