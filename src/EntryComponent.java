/*
 * 		패널의 속성을 저장하는 자료구조
 */
import java.awt.Image;
import javax.swing.ImageIcon;
//클래스 구현에 필수적인 라이브러리를 import

public class EntryComponent implements Comparable<EntryComponent>{
    public ImageIcon image, image2;
    //이미지를 저장하는 ImageIcon 2개 image는 700x800의 사이즈로 image2는 100x100의 사이즈로 이미지 저장
    public Image cdImage;
    //이미지의 사이즈를 조정하기 위한 Image 변수를 하나 준비
    public String name;
    //이미지의 패널에 올리게된 대상의 이름을 저장하는 변수
    public int victoryNum;
    //대상의 이전 토너먼트 승수를 저장하는 변수

    public EntryComponent(String imageDir, String imageDir2, String name, int victoryNum){
        //패널 속성을 직접적으로 정보를 넣어 받아오는 생성자
    	image = new ImageIcon(imageDir);
        image2 = new ImageIcon(imageDir2);
        //이미지 2개를 생성
        this.name = name;
        //이름을 받아와서 저장
        this.victoryNum = victoryNum;
        //받아온 승수를 저장
        cdImage = image.getImage();
        //이미지의 크기를 재조정 하기 위해 cdImage에 정보를 받아온
        
        
        
        
        int imageWidth=image.getIconWidth();
        int imageHeight=image.getIconHeight();
        //이미지으 높이와 폭을 조정하기 위해 저장
        
        
        if(imageWidth>imageHeight) {
        	//만약 폭이 높이보다 클때는
        	imageHeight = (int)(imageHeight*(float)700/imageWidth);
        	imageWidth = 700;
        	//700으로 폭을 조정하고 높이는 그에 맞춰서 딱 맞게 조정
        }
        else {
        	imageWidth = (int)(imageWidth*(float)800/imageHeight);
        	imageHeight = 800;
        	//만약 높이가 폭보다 클때는 높이를 800으로 조정하고 폭을 비율에 맞춰 조정
        }
        
        image.setImage(cdImage.getScaledInstance(
        		imageWidth, 
        		imageHeight, 
        		Image.SCALE_SMOOTH));
        //이미지의 사이즈를 새로 조정한 image2의 폭과 높이 변수로 재조정한다

        cdImage = image2.getImage();
        //이미지의 크기를 재조정 하기 위해 cdImage에 정보를 받아온
        
        if(imageWidth>imageHeight) {
        	//만약 폭이 높이보다 클때는
        	imageHeight = (int)(imageHeight*(float)100/imageWidth);
        	imageWidth = 100;
        	//100으로 폭을 조정하고 높이는 그에 맞춰서 딱 맞게 조정
        }
        else {
        	imageWidth = (int)(imageWidth*(float)100/imageHeight);
        	imageHeight = 100;
        	//만약 높이가 폭보다 클때는 높이를 100으로 조정하고 폭을 비율에 맞춰 조정
        }
        
        image2.setImage(cdImage.getScaledInstance(
        		imageWidth, 
        		imageHeight, 
        		Image.SCALE_SMOOTH));
        //이미지의 사이즈를 새로 조정한 image2의 폭과 높이 변수로 재조정한다
        
    }
    
    public EntryComponent(EntryComponent E) {
    	this.image = E.image;
    	this.image2 = E.image2;
    	this.name = E.name;
    	this.victoryNum = E.victoryNum;
    	cdImage = image.getImage();
    	//패널 속성자체를 받아와서 생성할때 이미 이미지를 크기 조정한 상태이므로 그대로 정보들을 받아온다.
    }

	@Override
	public int compareTo(EntryComponent arg) {
		return victoryNum - arg.victoryNum;
		//comparable을 인터페이스로 받아왔고 이를 이용해서 승수에 대한 조정을 하는 compareTo()메소드를 상세구현
	}
}
