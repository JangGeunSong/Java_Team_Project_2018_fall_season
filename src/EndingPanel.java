import java.awt.Color;                  //필요한 외부 클래스들 import
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

public class EndingPanel extends JPanel{

    private JPanel lPanel, rPanel;         //왼쪽에는 우승자의 사진을, 오른쪽에는 역대 우승자를 뽑힌 횟수로 정렬해서 출력할 왼쪽, 오른쪽 패널
    private JLabel ImageLabel;            //우승자의 이미지를 담을 JLabel객체
    private PrimaryPanel parent;         //EndingPanel을 add해서 사용할 부모 컴포넌트인 PrimaryPanel 객체 parent
    private JButton backToMain;            //엔딩화면에서 다시 초기화면으로 돌아갈 수 있게 해주는 버튼
    private EndingButtonListener btnE;      //버튼을 눌렀을 때 이벤트를 처리할 객체
    private FILEDB imString;            //데이터베이스와 연결해줄 FILEDB객체
    private JLabel rankTitle;            //오른쪽 위에 "역대 순위"를 출력할 JLabel 객체

    private JLabel [] rank;               //역대 순위를 화면에 보여줄 JLabel 배열

    private ImageIcon winnerIcon;         //왼쪽에 우승자의 사진을 담고있을 ImageIcon 객체
    private JLabel winnerLabel;            //우승자의 이름을 담고있을 JLabel 객체
    
    private EntryComponent E;            //EntryComponent 객체 선언


    public EndingPanel() {               // 기본 생성자


    }

    public EndingPanel(EntryComponent E, FILEDB imString2, PrimaryPanel parent) {      //파라미터 생성자

        this.setPreferredSize(new Dimension(1440,900));      //사이즈 설정   
        this.setLayout(null);                        //레이아웃 설정
        this.setBackground(Color.black);               //배경색 설정
        this.setBounds(0,0,1440,900);                  //크기와 위치 설정
        this.parent = parent;                        //부모 컴포넌트를 연결
        
        E.victoryNum++;                              //해당 우승자의 우승횟수 1회 증가
        this.imString = imString2;                     //데이터베이스와 연결할 FILEDB 객체를 파라미터로 받아서 저장
        //this.imString.sendInformation(E);
        this.imString.sortAllImgList();                  //imString으로 sortAllImgList 함수 호출

        lPanel = new JPanel();                        //왼쪽 패널 생성
        lPanel.setBounds(220,100,475,700);               //왼쪽 패널 크기 및 위치 설정
        lPanel.setBackground(Color.black);               //왼쪽 패널 배경색 설정
        lPanel.setLayout(null);                        //왼쪽 패널 레이아웃 설정

        this.add(lPanel);                           //왼쪽 패널을 이 클래스에 추가

        rPanel = new JPanel();                        //오른쪽 패널 생성
        rPanel.setBounds(745,100,475,700);               //오른쪽 패널 크기 및 위치 설정
        rPanel.setBackground(Color.black);               //오른쪽 패널 배경색 설정
        rPanel.setLayout(null);                        //오른쪽 패널 레이아웃 설정

        this.add(rPanel);                           //오른쪽 패널을 이 클래스에 추가

        rankTitle = new JLabel("최종결과");                     //오른쪽 위에 최종 결과라고 출력할 라벨 생성
        rankTitle.setBounds(0,0,475,100);                     //위치 및 크기 설정
        rankTitle.setForeground(Color.WHITE);                  //전경색 설정
        rankTitle.setFont(new Font("궁서체", Font.BOLD,60));         //폰트 설정
        rankTitle.setHorizontalAlignment(SwingConstants.CENTER);   //위치를 가운데로 설정
        rPanel.add(rankTitle);                              //오른쪽 패널에 추가

        rank = new JLabel [5];                              //역대 순위를 보여줄 객체 생성

        for(int i =0 ; i<5 ; i++) {      //역대 순위를 보여줄 객체 배열을 생성하고 설정하는 반복문
            rank[i] = new JLabel((i+1)+". "+this.imString.getAllImgList()[this.imString.getNumofImg() - i - 1].name+"    "+this.imString.getAllImgList()[this.imString.getNumofImg() - i - 1].victoryNum+"회 우승");         //DB와 연결한 객체를 통해 역대 우승 횟수를 이용해 문자열 생성
            rank[i].setBounds(0,(i+1)*100,475,100);            //위치 및 크기 설정
            rank[i].setFont(new Font("궁서체", Font.BOLD, 40));   //폰트 설정
            rank[i].setForeground(Color.WHITE);               //전경색 설정
            rPanel.add(rank[i]);                        //오른쪽 패널에 추가
        }

        winnerIcon = E.image;                                 //우승자의 이미지를 저장

        winnerLabel = new JLabel(winnerIcon);                     //우승자의 이미지를 이용해 winnerLabel 생성
        winnerLabel.setBounds(0,0,475,700);                        //위치 및 크기 설정
        //winnerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lPanel.add(winnerLabel);                              //우승자의 이미지를 왼쪽 패널에 추가

        this.backToMain = new JButton("Back To Main");               //초기화면으로 돌아갈 JButton 객체 생성
        this.backToMain.setPreferredSize(new Dimension(400,75));      //크기 설정
        this.backToMain.setFont(new Font("Consolas", Font.BOLD, 30));   //폰트 설정
        this.backToMain.setBackground(Color.WHITE);                  //배경색 설정
        this.backToMain.setForeground(Color.ORANGE);               //전경색 설정
        this.backToMain.setBounds(1000,800,400,75);                  //위치 및 크기 설정
        this.backToMain.setVisible(true);                        //버튼 보이게 설정
        this.btnE = new EndingButtonListener();                     //버튼 리스너 역할을 하는 객체 생성
        this.backToMain.addActionListener(btnE);                  //backToMain버튼에 버튼 리스너 추가
        this.add(backToMain);                                 //버튼을 EndingPanel에 추가
        
        this.E=E;                                          //E 저장

    }

    private class EndingButtonListener implements ActionListener{      //버튼 작동하게할 클래스 ActionListener 상속받아 사용
      @Override
      public void actionPerformed(ActionEvent e){
          Object obj = e.getSource();                  //Object 객체에 이벤트 발생한 컴포넌트 저장
          if(obj == backToMain){                     //obj == backToMain 버튼일 때
             parent.disableEndingPanel();               //엔딩패널을 안보이게 설정
             parent.enableUDIpanel();                  //초기화면을 보이게 설정
             try {                                 //예외처리
              imString.writeEditedValues(E);
           } 
             catch (SQLException e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
           }
          }
      }
    }

}//chagne MS949