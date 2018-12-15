import javax.swing.*;
//클래스의 구현을 위해 필수적인 라이브러리 임포트

public class Driver {
    public static void main(String[] args){
        JFrame f = new JFrame("이상형 월드컵");
        //게임창을 만들기 위한 프레임을 선언하고 생성 이름은 "이상형 월드컵"
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //창을 닫으면 프로그램이 종료하도록 만들기 위한 메소드
        
        PrimaryPanel primary = new PrimaryPanel();
        f.getContentPane().add(primary);
        //이 게임의 모든 화면을 받아줄 근원 패널 primary를 선언하고 생성한 후 프레임에 애드

        f.pack();
        f.setVisible(true);
        //프레임을 보이도록 setVisible(true)로 변경
    }
}
//change MS949
