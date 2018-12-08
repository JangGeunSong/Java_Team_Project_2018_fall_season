import java.awt.*;
import javax.swing.JPanel;

public class PrimaryPanel extends JPanel{

    private UserDefinedInstalizingWorldCupPanel startPanel;
    private MainGamePanel MGP;
    private int isRunningGame;
    private String nSex;
    private int nRound;


    public PrimaryPanel() {
        this.setBackground(Color.black);
        this.setBounds(0,0,1440,900);
        this.setPreferredSize(new Dimension(1440,900));
        this.setLayout(null);

        startPanel = new UserDefinedInstalizingWorldCupPanel(this);

        this.add(startPanel);

    }

    public void addMGP() {
        MGP = new MainGamePanel(nSex, nRound);
        MGP.setBounds(0,0,1440,900);
        this.add(MGP);
    }

    public MainGamePanel getMGP(){
        return this.MGP;
    }

    public void disableUDIpanel(){
        this.startPanel.setVisible(false);
    }

    public void setnRound(int nRound) {
        this.nRound = nRound;
    }

    public void setnSex(String nSex) {
        this.nSex = nSex;
    }

    public UserDefinedInstalizingWorldCupPanel getStartPanel() {
        return startPanel;
    }

    public void setIsRunningGame(int isRunning){
        isRunningGame = isRunning;
    }
}
