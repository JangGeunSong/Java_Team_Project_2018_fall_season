import java.awt.*;
import javax.swing.*;
//클래스 구현에 필수적인 라이브러리를 import

public class EntryPanel extends JPanel {
    private Point pt;
    private JLabel normal;
    private int velocity;
    private JLabel nameLabel;
    private EntryComponent E;
    /*
     * pt : 패널의 위치정보
     * normal : 패널의 들어갈 이미지를 붙어놓은 JLabel
     * velocity : 패널이 가지고 있는 속도
     * nameLabel : 패널이 저장한 대상의 이름
     * E : 패널의 속성정보
     * 
     */

    public EntryPanel(int x, int y, EntryComponent E){
    	//파라미터로 위치와 패널 속성을 받을때
        pt = new Point();
        //pt를 생성하고
        pt.x = x;
        pt.y = y;
        //pt에 x y좌표를 준다
        this.E = E;
        //E를 저장한다
        this.setLayout(null);
        this.setBounds(pt.x,pt.y,700,800);
        this.setBackground(Color.BLACK);
        //패널 자체의 크기와 위치 레이아웃 방식을 설정한다.
        nameLabel = new JLabel(this.E.name);
        normal = new JLabel(this.E.image);
        //이미지와 이름을 패널속성 E를 통해 생성한다.
        velocity = 0;
        //속도는 처음에 없으므로 0으로 저장한다.
        normal.setBounds(
        		this.getWidth()/2-E.image.getIconWidth()/2,
        		this.getHeight()/2-E.image.getIconHeight()/2,
        		E.image.getIconWidth(),
        		E.image.getIconHeight());
        //이미지의 위치와 크기를 각각 이미지의 사이즈를 받아와서 설정한다.

        nameLabel.setFont(new Font("궁서체", Font.BOLD, 30));
        nameLabel.setSize(700, 50);
        nameLabel.setLocation(this.getWidth()/2-nameLabel.getWidth()/2,600);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setForeground(Color.orange);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(0,0,0,150));
        /*
         * 이름라벨의 경우 사진의 색에 따라 글씨가 보이거나 안보이는 경우가 많아
         * 이를 해결하기 위해 setOpaque를 사용해서 반투명하게 설정한 후
         * setBackground를 통해 알파값을 조정해서 뒤에가 보일 수 있도록 
         * 조정했다. 이외의 위치와 크기 글씨체는 위 코드에서 설정한 대로 집어 
         * 넣었다.
         * 
         */
        this.add(nameLabel);
        this.add(normal);
        //생성 완료한 두개의 라벨을 패널에 애드한다.
    }

    public EntryPanel(){
    	//파라미터없이 패널을 생성할때
    	setLayout(null);
    	setBackground(Color.BLACK);
    	nameLabel = new JLabel();
        normal = new JLabel();
        //저장할 속성이 없으므로 이미지나 이름없이 바로 생성만 한다.
        velocity = 0;
        //속도는 0으로 초기화 한다.
        normal.setBounds(0,0,700,800);
        //이미지의 위치를 잡아준다.

        nameLabel.setFont(new Font("궁서체", Font.BOLD, 30));
        nameLabel.setBounds(300,710,180,40);
        nameLabel.setForeground(Color.ORANGE);
        //이름 라벨의 경우 글씨체와 위치 크기만 잡아주고 나머지는 존재하지 않으므로
        //설정하지 않는다.
        this.add(nameLabel);
        this.add(normal);
        //만든 라벨들을 패널에 애드한다.
    }
    
    public EntryPanel(EntryComponent E){
    	//패널 속성만 받아서 패널이 생성될때
    	setBackground(Color.BLACK);
    	this.E = E;
    	nameLabel = new JLabel(this.E.name);
        normal = new JLabel(this.E.image);
        velocity = 0;
        normal.setBounds(
        		this.getWidth()/2-E.image.getIconWidth()/2,
        		this.getHeight()/2-E.image.getIconHeight()/2,
        		E.image.getIconWidth(),
        		E.image.getIconHeight());
        //위에서 생성했던 패널과 마찬가지로 패널의 정보들에 대한 기초적인 속성을 잡아서 이미지를 만들어준다.

        nameLabel.setFont(new Font("궁서체", Font.BOLD, 30));
        nameLabel.setBounds(300,710,180,40);
        nameLabel.setForeground(Color.ORANGE);
        //글씨의 경우도 마찬가지로 이전의 패널을 생성할때와 마찬가지의 양식을 유지하면서 만들어준다.
        this.add(nameLabel);
        this.add(normal);
        //2개의 라벨을 애드한다.
    }

    public int getVelocity(){
        return this.velocity;
    }
    //패널의 속도를 받아오는 메소드 : 사용용도는 패널이 이동하는 애니메이션에서 쓰인다.

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
    //패널의 속도를 정하는 메소드
    
    public int getPtX(){
        return this.pt.x;
    }
    //패널의 x좌표를 구하는 메소드
    
    public void setPtX(int x){
        this.pt.x = x;
    }
    //패널의 x좌표를 정하는 메소드
    
    public int getPtY(){
        return this.pt.y;
    }
    //패널의 y좌표를 구하는 메소드

    public void setPtY(int y){
        this.pt.y = y;
    }
    //패널의 y좌표를 정하는 메소드

    public Point getPt(){
        return this.pt;
    }
    //패널의 pt를 얻는 메소드
    
    public void setEntryComponent(EntryComponent param){
        this.E = param;
        this.nameLabel.setText(E.name);
        this.normal.setIcon(E.image);
        normal.setBounds(
        		this.getWidth()/2-E.image.getIconWidth()/2,
        		this.getHeight()/2-E.image.getIconHeight()/2,
        		E.image.getIconWidth(),
        		E.image.getIconHeight());
        repaint();
    }
    //패널의 속성을 정하는 메소드
    
    public EntryComponent getEntryComponent() {
    	return this.E;
    }
    //패널의 속성을 얻는 메소드
    
    public void setSizeofElement() {
    	normal.setVisible(false);
    	nameLabel.setVisible(false);
    }
    //패널 라벨의 사이즈를 재조정할때 우선적으로 화면에 보이지 않게 해주는 메소드
    
    public void setReturnSize() {
    	normal.setVisible(true);
    	nameLabel.setVisible(true);
    }
    //패널의 사이즈를 재조정하고 나서 다시 화면에 보여줄때 사용하는 메소드
    
}
