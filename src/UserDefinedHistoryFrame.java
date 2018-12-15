import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserDefinedHistoryFrame extends JFrame{

    private UserDefinedHistoryPanel primary;
    private EntryPanel Tree[];
    private JScrollPane p;
    public UserDefinedHistoryFrame(int entryNum, EntryComponent[] parameter) {

    	
        this.setPreferredSize(new Dimension(1440, 900));

    
        primary = new UserDefinedHistoryPanel(entryNum, parameter, this);
        //Frame에 올라갈 panel을 생성해 준다.
        //인자로 대진 생성에 쓰이는 Tree를 넘겨준다. parent도 같이 넘겨준다
        
        add(primary);
        //프레임에 panel 올려주기

        
        p = new JScrollPane(primary);
        add(p);
        //화면에 필요한 스크롤 생성해서 올려주기
        
        primary.setScroll(p);
        //primary에서 스크롤의 정보를 이용해 panel의 위치를 지정하기 대문에 primary에 스크롤의 정보를 넘겨주는 함수

        pack();
        setVisible(true);
        }

}
