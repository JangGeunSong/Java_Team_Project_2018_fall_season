import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserDefinedHistoryPanel extends JPanel{

    private EntryPanel[] entryTree;
    //private JScrollPane scroll;
    private EntryPanel viewingPanel;

    final int compoWidth=100;
    final int compoHeight=100;
    final int gapOfCompoY=70;
    final int gapOfCompoX=20; 
    //트리를 그릴때 각 요소의 크기, 간격을 설정해주는 상수값

    
   
    private int entryNum; // 트리를 그리기 위해서 생성해야할 트리 노드의 개수를 저장하는 변수
    private int heightBound, widthBound; // panel의 전체 범위 저장하는 변수(트리의 모든 요소들을 포함했을 때 패널의 크기)
    //private Point savePt;
    private MouseHoveringListener HoverL; //각각의 요소에 마우스가 올라갔을때 어떤 행동을 취해야 하는지 저장하는 리스너 객체
    
    private JScrollPane scroll; //부모(JFrame)의 스크롤을 가져오는 변수 
    private JFrame parent; //부모(JFrame)을 저장하는 변수
    
    
    
    public UserDefinedHistoryPanel(int entryNum, EntryComponent[] compoData,JFrame parent) {

        widthBound = (compoWidth+gapOfCompoX) * ((entryNum)/2); // 화면의 가로 길이(entryNum==
        heightBound = (compoHeight+gapOfCompoY) * (retLog2(entryNum)); //화면의 세로 높이(트리구조라서 log 사용) 
        //tree가 구성됐을 때 tree의 구성요소들을 모두 화면에 담는다면 구성돼야 할 화면의 width, height

        HoverL = new MouseHoveringListener();// 각각의 객체가 호버링 됐을때 효과를 주는 리스너 할당


        setLayout(null);
        setPreferredSize(new Dimension(widthBound,heightBound));
        setBackground(Color.white);//화면의 사이즈, 색 설정

        viewingPanel = new EntryPanel();
        viewingPanel.setVisible(false);
        add(viewingPanel);
        // 트리에서 요소들을 호버링 했을때 화면에 해당 요소의 사진을 출력해주는 패널 생성
        
        this.entryNum=entryNum;
        //entryNum(:트리에 그려야 할 요소들(검정색 panel)의 개수) 변수 값 설정
        this.parent=parent;
        //본인의 부모인 UnserDefinedHistroyFrame 본인에게 가져오기

        entryTree = new EntryPanel[entryNum+1];
        //HistoryPanel에 사용할 트리를 새로 구성하기 위한 배열을 크기에 맞춰 생성한다.
        //받아온 entryNum의 경우 MainGamePanel에서 사용했던 nElem과 같은 숫자로 배열을 트리로 보고
        //연산할때 계산의 편의를 위해 인덱스를 1일때가 루트로 보는것이 편하므로 이를 위한 받아온 갯수 + 1을 실제
        //배열의 크기로 결정해서 생성한다.


        for(int i=1;i<entryNum;i++) {
        	JLabel normal = new JLabel(compoData[i].image2);//각각의 요소(검은색 상자)에 들어갈 이미지 라벨 생성
        	normal.setHorizontalAlignment(JLabel.CENTER);//라벨 중앙 정렬
        	normal.setBounds(0,0,compoWidth,compoHeight);//라벨의 위치와 사이즈를 결정 이때 width와 height가 100x100인점을 감안하여 라벨 생성시 패널 요소의 이미지를 100x100으로 만들었던 image2를 불러옴

        	JLabel nameLabel = new JLabel(compoData[i].name);//각각의 요소(검은색 상자)에 들어갈 이름 라벨 생성
        	nameLabel.setHorizontalAlignment(JLabel.CENTER);//라벨 중앙정렬
        	nameLabel.setForeground(Color.YELLOW);//라벨 색
        	nameLabel.setFont(new Font("궁서체", Font.BOLD, 25));//라벨 폰트
        	nameLabel.setBounds(0,compoHeight/2,compoWidth, compoHeight/2);
        	
            entryTree[i]= new EntryPanel(compoData[i]);
            //각각의 요소(검은색 상자)를 compoData(각각 요소(사람들)의 정보) 기준으로 생성
            entryTree[i].setLayout(null);
            entryTree[i].setSize(new Dimension(compoWidth,compoHeight));
            //요소 사이즈 
            entryTree[i].setBackground(Color.black);//요소 색
            entryTree[i].add(nameLabel);//요소에 이름 라벨 붙이기
            entryTree[i].add(normal);//요소에 이미지 라벨 붙이기
            entryTree[i].setSizeofElement();//모르겠음-----------------------------------------------코드 정리하고 주석 달아주세요(addRemark)
            entryTree[i].addMouseListener(HoverL); //마우스 호버링 됐을때 리스너 달기
            add(entryTree[i]); // 각각의 요소 패널에 추가
        }
        drawEntry(); // 선 그리기

        

    }
    
    private int retLog2(int n) {
    	
    	int cnt=0;
 
    	while(n>1) {
    		n=n/2;
    		cnt++;
    	}	
    	return cnt;
    }// log를 구하는 함수(정수값으로 출력 가능하게 만듦 -> 기본 Math 함수도 있기는 한데 캐스팅 할때 값이 내려갈까봐 별도로 구성)
    
    public void setScroll(JScrollPane scroll) {
    	this.scroll=scroll;
    }//부모의 스크롤 얻어오기 -> 이거로 호버링된 이미지의 위치를 정해줌

    private void drawEntry() {

        int level=0;
        
        level=retLog2(entryNum)-1;

        for(int i=entryNum/2;i<entryNum;i++) {
            entryTree[i].setLocation((gapOfCompoX+compoWidth)*(i-entryNum/2), level*(compoHeight+gapOfCompoY));
        } // 제일 밑에있는 외부노드들 위치 설정
        for(int i=entryNum/2-1;i>0;i--) {
            entryTree[i].setLocation((int) (entryTree[i * 2].getLocation().getX() + entryTree[i * 2 + 1].getLocation().getX()) / 2, (int) (entryTree[i * 2].getLocation().getY() - (compoHeight + gapOfCompoY)));
        }//외부노드 기준으로 그 중간에 부모 노드들 위치 선정
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1,x2,y1,y2;
        Graphics2D g2=(Graphics2D)g;
        
        for(int i=entryNum-1;i>1;i--) {
        	if(entryTree[i].getEntryComponent().image.getDescription() != "Images/QuestionMark/a.jpg"
        			&& entryTree[i].getEntryComponent() == entryTree[i/2].getEntryComponent()) {
        		//만약 자식이랑 부모가 같으면 대진에서 승리한 사람이므로 해당 선은 빨간색 굵은 선으로 표시해주기
        		// "Images/QuestionMark/a.jpg" -> 아직 누가 올라갔는지 결정되지 않은 요소같은 경우에는 ? 사진을 띄워줌으로 예외처리
        		g2.setStroke(new BasicStroke(4));
        		g2.setColor(Color.RED);
        	}
        	else {
        		//아니면 평범한 검정선
        		g2.setStroke(new BasicStroke(1));
        		g2.setColor(Color.black);
        	}
            x1=entryTree[i].getLocation().x+compoWidth/2;
            y1=entryTree[i].getLocation().y;
            x2=entryTree[i/2].getLocation().x+compoWidth/2;
            y2=entryTree[i/2].getLocation().y+compoHeight;
            g.drawLine(x1, y1, x2, y2);
            //각각의 요소의 중간 기준으로 선 그려주기
        }
    }
    //paintComponent에서 트리들의 요소를 이어주는 길(빨간선, 검정선)을 그려준다.
    
    private class MouseHoveringListener implements MouseListener{
    	@Override
    	public void mouseEntered(MouseEvent e) {
    		EntryPanel target = (EntryPanel)e.getSource();
    		//호버링된 컴포넌트 자신의 요소를 가져온다,
    		
    		Point savePt = target.getLocation();
    		//자신의 원래 위치를 저장한다.

    		int XonScreen= 
    					savePt.x-(int)scroll.getViewport().getViewPosition().getX();
    		int YonScreen=
    					savePt.y-(int)scroll.getViewport().getViewPosition().getY();
    		//Frame위에서의 위치를 얻는다 -> 
    		//panel의 사이즈가 크고 Frame에 스크롤이 달려서 화면을 전환할 수 있는 방식으로 구현이 돼있다.
    		//때문에 Frame좌표계 기준에서 각각의 요소의 위치를 얻는 구문이다.
    		
    		int x,y;
    		//본인이 panel 좌표계 기준으로 놓일 위치를 설정해주는 변수
    		
    		
    		
    		if(XonScreen >= parent.getWidth()-700) {
    			x = savePt.x - ((savePt.x + 700)-((int)scroll.getViewport().getViewPosition().getX()+parent.getWidth())) - 50;
    			// 사진이 작은frame 사이즈 때문에 잘려서 안보이는 부분이 없게
    			//본인이 생성돼야 할 위치를 결정해주는 구문.
    		}
    		else {
    			x=savePt.x;
    			//frame 사이즈 때문에 사진이 잘려서 안보이는 경우가 없을때.
    		}
    		
    		if(YonScreen >= parent.getHeight()-800) {
    			y = savePt.y - ((savePt.y + 800)-((int)scroll.getViewport().getViewPosition().getY()+parent.getHeight())) - 70;
    			// 사진이 작은frame 사이즈 때문에 잘려서 안보이는 부분이 없게
    			//본인이 생성돼야 할 위치를 결정해주는 구문.
    		}
    		else {
    			y = savePt.y;
    			//frame 사이즈 때문에 사진이 잘려서 안보이는 경우가 없을때.
    		}
    		
    		
    		viewingPanel.setBounds(x,y,700,800);
    		//본인의 위치와 사이즈 결정
    		
    		viewingPanel.setEntryComponent(target.getEntryComponent());
    		//viewingPanel은 호버링됐을때 이미지를 띄워주는 역할을 하는 패널(EntryPanel)이다.
    		//해당 패널을 호버링된 요소의 이미지 정보로 초기화 해준다.
    		
    		viewingPanel.setVisible(true);
    		//해당 패널을 보여준다.

    	}
    	@Override
    	public void mouseExited(MouseEvent e) {
    		viewingPanel.setVisible(false);
    		//호버링 해제되면 viewingPanel 안보이게 바꾸기
    	}
    	@Override
    	public void mousePressed(MouseEvent e) {

    	}
    	@Override
    	public void mouseReleased(MouseEvent e) {

    	}
    	@Override
    	public void mouseClicked(MouseEvent e) {

    	}
    }
//마우스가 호버링 됐을때 사진 띄워주는 이벤트를 처리해주는 리스너

}
