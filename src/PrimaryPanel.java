import java.awt.*;
import java.sql.SQLException;
import javax.swing.JPanel;
//클래스 구현을 위한 외부 라이브러리 호출

public class PrimaryPanel extends JPanel{
	//게임의 모든 화면을 포괄하는 primaryPanel
    private UserDefinedInstalizingWorldCupPanel startPanel;
    private MainGamePanel MGP;
    private EndingPanel EDP;
    private String nSex;
    private int nRound;
    //모든 패널을 가지고 있어야 하므로 3개의 게임진행중 맞닥드릴 패널 3개를 선언
    //nSex는 게임의 주제 즉 남성인지 여성인지 받아오는 변수
    //nRound는 몇강을 할것인지 선택하는 변수

    public PrimaryPanel() {
        this.setBackground(Color.black);
        this.setBounds(0,0,1440,900);
        this.setPreferredSize(new Dimension(1440,900));
        this.setLayout(null);
        //패널의 기초적인 구성 요소를 선언함
        startPanel = new UserDefinedInstalizingWorldCupPanel(this);
        //처음에는 시작 화면이 나와야 하므로 시작화면을 생성
        this.add(startPanel);
        //시작화면은 primaryPanel에 애드한다.
    }

    public void addMGP() throws SQLException {
    	//MainGamePanel을 만들어 주는 메소드
        MGP = new MainGamePanel(nSex, nRound, this);
        MGP.setBounds(0,0,1440,900);
        this.add(MGP);
        //메인게임패널을 시작화면에서 받아와 선택한 성별과 몇강인지의 숫자를 파라미터로 생성하고
        //메인게임패널의 사이즈를 setBounds로 설정한 뒤 현재 패널에 애드한다.
    }

    public void addEDP(EntryComponent E, FILEDB imString, PrimaryPanel p) {
    	//게임이 종료되면 최종 화면을 불러와야 하므로 이에 대한 일을 담당하는 메소드
    	EDP = new EndingPanel(E, imString, p);
    	EDP.setBounds(0,0,1440,900);
    	this.add(EDP);
    	//엔딩 패널을 메인 게임 패널에서 알게된 정보를 통해 생성하고 패널의 사이즈를 setBounds로 설정한 뒤
    	//현재 패널에 애드한다.
    }

    public MainGamePanel getMGP(){
        return this.MGP;
        //메인게임패널을 받아오는 메소드
    }

    public UserDefinedInstalizingWorldCupPanel getStartPanel() {
        return startPanel;
        //게임 시작화면은 받아오는 메소드
    }
    
    public void disableUDIpanel(){
        this.startPanel.setVisible(false);
        //게임 시작화면을 꺼버리는 메소드
    }
    
    public void enableUDIpanel(){
    	this.startPanel.setVisible(true);
    	//게임 시작화면을 켜는 메소드
    }

    public void disableMGP() {
    	this.MGP.setVisible(false);
    	//메인 게임 패널을 꺼버리는 메소드
    }

    public void disableEndingPanel() {
    	this.EDP.setVisible(false);
    	//엔딩 패널을 꺼버리는 메소드
    }

    public void setnRound(int nRound) {
        this.nRound = nRound;
        //몇강인지 설정하는 메소드
    }

    public void setnSex(String nSex) {
        this.nSex = nSex;
        //주제가 뭔지 설정하는 메소드
    }

}
