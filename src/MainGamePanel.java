import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
//클래스 구현에 필수적인 라이브러리를 import

public class MainGamePanel extends JPanel{
    private EntryPanel leftPanel, rightPanel;
    //화면에 등장할 2개의 대결 대상을 올리는 패널
    private JButton btnH;
    //History Frame을 띄우기 위한 버튼
    private ButtonListener btnL;
    //btnL을 눌렀을때 이벤트를 핸들링 하기 위한 리스너
    private PanelClickDetect dteP;
    //패널 2개중 하나를 눌렀을때 나타는 반응을 만들기 위해 선언한 이벤트 리스너
    private Timer aniTimerLeft, resetTimerLeft;
    private Timer aniTimerRight, resetTimerRight;
    //왼쪽 패널과 오른쪽 패널중 하나를 선택했을때 작동하게 될 타이머를 패널별로 선언 aniTimer는 패널의 이동을
    //resetTimer는 승자를 띄운 후 다음 라운드로 진행하기 위해 만든 타이머 스레드.
    private String Type;
    //게임의 주제인 남자 여자중 어떤 대상을 선택했는지를 받아서 플래그로 돌리기 위한 String변수
    private int nRound, nElem, nWinner, nNextMatch, signal;
    //몇강을 진행할지 저장하는 nRound, 몇강인지를 확인하여 트리의 사이즈를 어떻게 결정할지 선택하는 nElem,
    //승자 패널의 인덱스를 저장하는 nWinner, 다음 라운드의 트리 인덱스를 저장하는 nNextMatch,
    //토너먼트의 종료를 확인하는 signal 변수
    private EntryComponent initialComponent;
    //승자가 저장되는 트리의 위치에 아직 아무도 배정되지 않았을때, 집어넣을 패널의 속성
    private EntryComponent[] eTree;
    private EntryPanel[] Tree;
    //eTree == 패널의 정보를 트리로 저장, Tree == 패널자체를 트리로 생성하여 저장
    private UserDefinedHistoryFrame UDHF;
    //History Frame을 저장하는 객체
    private FILEDB imString;
    //DB에 접근하여 정보를 받아오는 FILEDB 클래스의 객체 imString
    private PrimaryPanel parentPanel;
    //부모 패널인 primaryPanel을 패러미터 패싱해서 받아오는 parentPanel
    private EndingPanel EDP;
    //엔딩패널을 만드는 EDP 변수

    public MainGamePanel(String Type, int Round, PrimaryPanel parent) throws SQLException {
        //어떤 주제인지 받아오는 Type, 몇강인지 받는 Round, 부모 패널인 parent 를 받아서 생성자를 제작
    	int i = 0, j = 0;//트리의 인덱스를 받아오기 위한 변수, j는 트리 속성의 제작을 위해 만들때 사용되는 인덱스 조정 변수
        this.setBackground(Color.BLACK);//메인 게임 패널의 배경색을 지정하는 변수
        this.setPreferredSize(new Dimension(1440,900));//메인게임 패널의 사이즈를 지정
        this.setLayout(null);//메인 게임패널의 Layout을 null로 지정

        dteP = new PanelClickDetect();//패널 클릭 이벤트 리스너를 생성

        this.Type = Type;//패러미터로 받아온 주제를 저장
        this.nRound = Round;//패러미터로 받아온 n강을 저장
        this.nElem = (this.nRound * 2) - 1;//n강에 의해 만들어야할 트리의 개수를 계산해서 저장
        this.nNextMatch = nElem;//게임의 처음 시작은 트리의 외부노드중 가작 오른쪽 끝에서 부터 시작할 예정이므로 이를 저장
        this.signal = 0;//우선 게임이 끝나지 않았으므로 signal을 0으로 저장
        this.parentPanel = parent;//부모 패널을 지정하여 저장
        this.initialComponent = new EntryComponent("Images/QuestionMark/a.jpg","Images/QuestionMark/a.jpg","", 0);
        //아직 승자가 정해지지 않았을때 패널의 정보를 생성

        if(Type == "여자") {
        	imString = new FILEDB("WOMAN", nRound);
        	//만약 여자로 주제가 정해졌을때 isString에 생성 파라미터를 WOMAN, 그리고 받아온 nRound로 생성
        }
        else if(Type == "남자"){
        	imString = new FILEDB("MAN", nRound);
        	//만약 남자로 주제가 정해졌을때 isString에 생성 파라미터를 MAN, 그리고 받아온 nRound로 생성
        }

        eTree = new EntryComponent[nElem + 1];
        Tree = new EntryPanel[nElem + 1];
        //트리의 속성인 eTree와 트리 패널인 Tree를 생성 이때 트리의 부모 속성을 살리면서 연산하기 위해 
        //일부러 +1개를 더 만들어서 우승자 패널의 번호를 1번으로 들어가도록 조정
        for (i = nElem; i > 0; i--){
        	//모든 트리 구성요소의 생성을 시작
            if(i > nElem/2) {
                eTree[i] = imString.getRandomImgList()[j];
                j++;
                //만약 트리의 초반 구성활동시 외부노드로 간주되는 leaf에는 우선 대전을 붙이기 위해 대상을 불러와야 한다.
                //따라서 이때는 imString 에서 랜덤으로 생성된 배열을 그대로 받아와서 복사해 집어 넣는다.
                //이때 imString.getRandomImgList()은 인덱스를 i로 하면 오류가 생기므로 별도의 변수 j를 사용한다.
            }
            else{
                eTree[i] = this.initialComponent;
                //만약 모든 엔트리들이 다 배정되었을때, 나머지는 승자를 모르므로 initilaComponenet를 지정
            }
        }

        for(i = nElem; i > 0; i--) {
            Tree[i] = new EntryPanel(10,60,eTree[i]);
            //이제 배정된 패널 속성을 이용해서 패널의 트리를 생성
        }

        for(i = 1; i < nElem + 1; i++){
            Tree[i].addMouseListener(dteP);
            //모든 패널 트리에 대해 마우스 클릭 리스너로 제작했던 dteP를 애드
        }

        UDHF = new UserDefinedHistoryFrame(nElem + 1, eTree);
        UDHF.setTitle("현재상황");
        UDHF.setVisible(false);
        //초기 상태의 히스토리 프레임을 생성

        leftPanel = Tree[nNextMatch - 1];
        leftPanel.setPtX(10);
        leftPanel.setPtY(60);
        leftPanel.setLocation(leftPanel.getPtX(),leftPanel.getPtY());
        leftPanel.repaint();
        this.add(leftPanel);
        //왼쪽 패널의 초기 배정 위치의 경우 Point 변수로 패널의 속성에 저장되어 있으므로 이를 set메소드를 이용해서 결정
        
        rightPanel = Tree[nNextMatch];
        rightPanel.setPtX(720);
        rightPanel.setPtY(60);
        rightPanel.setLocation(rightPanel.getPtX(),rightPanel.getPtY());
        rightPanel.repaint();
        this.add(rightPanel);
        //오른쪽 패널의 초기 배정 위치의 경우 Point 변수로 패널의 속성에 저장되어 있으므로 이를 set메소드를 이용해서 결정

        btnH = new JButton("HISTORY");
        btnH.setBounds(1200,10,200,40);
        btnH.setFont(new Font("Consolas", Font.BOLD, 30));
        btnH.setForeground(Color.WHITE);
        btnH.setBackground(Color.BLACK);
        this.add(btnH);
        //히스토리 패널의 생성
        
        btnL = new ButtonListener();
        btnH.addActionListener(btnL);
        //버튼 클릭 리스너의 생성후 버튼에 애드
        
        resetTimerRight = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a){
            	//타이머의 액션 상세 구현
                resetTimerRight.start();
                //리셋 타이머를 시작하고
                if((nNextMatch)/2 == 0){
                    signal = 1;
                }
                //만약 다음 라운드로 진행하기 위해 인덱스를 조정할때 그 값이 0이 된다면 게임이 끝난것이므로 
                //게임이 끝났다는 플래그로 signal을 1로 바꿔준다
                reset(nNextMatch - 1, nNextMatch, signal, eTree[1]);
                //reset()에 다음 라운드로 들어가는 변수들을 파라미터로 집어넣고 reset메소드 실행
                resetTimerRight.stop();
                //타이머를 종료
            }
        });
        //오른쪽 패널을 선택하고 다음 단계로 들어가기 위한 타이머 
        
        resetTimerLeft = new Timer(2500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //타이머의 액션 상세 구현
            	resetTimerLeft.start();
            	//타이머를 시작하고
                if((nNextMatch)/2 == 0){
                    signal = 1;
                }
                //만약 다음 라운드로 진행하기 위해 인덱스를 조정할때 그 값이 0이 된다면 게임이 끝난것이므로 
                //게임이 끝났다는 플래그로 signal을 1로 바꿔준다
                reset(nNextMatch - 1, nNextMatch, signal, eTree[1]);
              //reset()에 다음 라운드로 들어가는 변수들을 파라미터로 집어넣고 reset메소드 실행
                resetTimerLeft.stop();
                //타이머를 종료
            }
        });
        //왼쪽 패널을 선택하고 다음 단계로 들어가기 위한 타이머
        
        //make Two reset Timer left and right side.
        
        aniTimerLeft = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	//타이머의 액션을 상세구현
                aniTimerLeft.start();
                //애니메이션 타이머를 시작
                if(leftPanel.getPtX() < 400){
                    leftPanel.setPtX(leftPanel.getPtX() + leftPanel.getVelocity());
                    leftPanel.setLocation(leftPanel.getPtX(), leftPanel.getPtY());
                    leftPanel.repaint();
                    aniTimerLeft.setRepeats(true);
                }
                //만약 왼쪽 패널이 중앙으로 오기 전인 x 좌표가 400이 되기전일때 왼쪽 패널의 속도를 더해서 x좌표를 재 조정 하고 repaint()를 하고 
                //repeat() 메소드를 실행
                else if(leftPanel.getPtX() >= 400){
                    resetTimerLeft.start();
                    aniTimerLeft.stop();
                    //만약 패널이 중앙으로 왔다면 resetTimer를 실행 이떄는 왼쪽 패널이므로 왼쪽 리셋 타이머를 실행한다.
                }
            }
        });
        //왼쪽 패널이 움직일때를 구현하는 타이머
        
        aniTimerRight = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	//상세 액션을 구현
                aniTimerRight.start();
                //타이머를 시작
                if(rightPanel.getPtX() > 400){
                    rightPanel.setPtX(rightPanel.getPtX() - rightPanel.getVelocity());
                    rightPanel.setLocation(rightPanel.getPtX(), rightPanel.getPtY());
                    rightPanel.repaint();
                    aniTimerLeft.setRepeats(true);
                }
                //만약 오른쪽 패널이 중앙으로 오기 전인 x 좌표가 400이 되기전일때 오른쪽 패널의 속도를 빼서 x좌표를 재 조정 하고 repaint()를 하고 
                //repeat() 메소드를 실행
                else if(rightPanel.getPtX() <= 400){
                    resetTimerRight.start();
                    aniTimerRight.stop();
                }
                //패널이 중앙으로 도달하면 reset타이머를 시작 이때 오른쪽 패널이므로 오른쪽 리셋 타이머를 시작함
            }
        });
        //오른쪽 패널이 움직일때를 구현하는 타이머
        //make Two animation Timer left and right side.

    }

    public void reset(int nLeft, int nRight, int signal, EntryComponent E) {
        //다음 라운드로 갈지 게임을 종료할지에 대한 플래그를 받아 리셋하는 메소드
    	if(signal == 0) {
    		//만약 게임이 종료된것이 아니라면
            leftPanel.setVisible(false);
            rightPanel.setVisible(false);
            //일단 양쪽 패널을 안보이게 조정하고
            leftPanel = null;
            //왼쪽 패널의 지정 메모리 주소를 null로 바꾸고
            leftPanel = Tree[nLeft];
            //다음 라운드에 사용할 트리패널을 지정하고
            leftPanel.setPtX(10);
            leftPanel.setPtY(60);
            leftPanel.setLocation(leftPanel.getPtX(), leftPanel.getPtY());
            //위치를 초기에 설정했던 위치로 조정해서 setLocation을 한다
            this.add(leftPanel);
            leftPanel.setVisible(true);
            rightPanel = null;
            rightPanel = Tree[nRight];
            rightPanel.setPtX(720);
            rightPanel.setPtY(60);
            rightPanel.setLocation(rightPanel.getPtX(), rightPanel.getPtY());
            //위의 왼쪽 패널과 같은 방식으로 오른쪽 패널을 재 조정
            this.add(rightPanel);
            rightPanel.setVisible(true);
            this.repaint();
            //모든게 끝나면 각각의 패널을 다시 메인게임패널에 애드하고 setVisible을 true로 고친 후 repaint를 한다
        }
        else{
        	this.parentPanel.disableMGP();
        	this.parentPanel.addEDP(E, imString, parentPanel);
        	//만약 게임이 종료되는 플래그로 들어서면 부모패널의 메소드인 disableMGP()와 addEDP()를 실행한다.
        }
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Object obj = e.getSource();
            if(obj == btnH){
            	new UserDefinedHistoryFrame(nElem + 1, eTree);
            	//히스토리 프래임을 만들어야 할때 이벤트를 상세구현 재조정된 패널의 속성 트리와 트리의 크기를 파라미터로 줘서 히스토리 프레임을 재생성
            }
        }
    }

    private class PanelClickDetect implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e){
            Object obj = e.getSource();
            if(obj == rightPanel){
               if( !aniTimerRight.isRunning() && !resetTimerRight.isRunning()) {
               //만약 애니메이션 타이머와 리셋타이머가 돌지 않는다면
                     nWinner = nNextMatch;
                     //승자의 인덱스를 저장하고
                     Tree[(nNextMatch)/2] = rightPanel;
                     eTree[(nNextMatch)/2] = rightPanel.getEntryComponent();
                     //패널트리와 패널속성 트리에 집어넣을 정보를 받아오고
                     nNextMatch = nNextMatch - 2;
                     //다음 라운드로 진입
                     rightPanel.setVelocity(8);
                     //오른쪽 패널에는 속도를 8로 주고
                     leftPanel.setVisible(false);
                     //선택받지 못한 패널은 보이지 않도록 바꾼 후
                     aniTimerRight.start();
                     //애니메이션 타이머를 사직한다
                  }
               //if - else
            }//if-else
            else if(obj == leftPanel){
               if( !aniTimerLeft.isRunning() && !resetTimerLeft.isRunning()) {
                   //만약 두 타이머가 돌지 않는다면  
            	   nWinner = nNextMatch + 1;
            	   //승자를 결정하고
                   Tree[(nNextMatch)/2] = leftPanel;
                   eTree[(nNextMatch)/2] = leftPanel.getEntryComponent();
                   //패널과 속성 트리에 정보를 집어넣고
                   nNextMatch = nNextMatch - 2;
                   //다음라운드로 진행할 인덱스를 조정하고
                   leftPanel.setVelocity(8);
                   //선택한 패널의 속도를 8로 준 다음
                   rightPanel.setVisible(false);
                   //선택받지 못한 패널을 보이지 않도록 조정한다.
                   aniTimerLeft.start();
                   //이후 타이머를 시작한다.
                  }
                  
               //if - else
            }//if-else
        }//mouseClicked()
        @Override
        public void mousePressed(MouseEvent e){}
        @Override
        public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseEntered(MouseEvent e){}
        @Override
        public void mouseExited(MouseEvent e){}
        //이외의 인터페이스들을 상세 구현하는 메소드를 선언만 해둔다.
    }
}
