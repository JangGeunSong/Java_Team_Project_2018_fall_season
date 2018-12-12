import java.awt.Image;
import javax.swing.ImageIcon;

public class EntryComponent implements Comparable<EntryComponent>{
    public ImageIcon image, image2;
    public Image cdImage;
    public String name;
    public int victoryNum;

    public EntryComponent(String imageDir, String imageDir2, String name, int victoryNum){
        image = new ImageIcon(imageDir);
        image2 = new ImageIcon(imageDir2);
        this.name = name;
        this.victoryNum = victoryNum;
        cdImage = image.getImage();
        image.setImage(cdImage.getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        /*
        cdImage = image2.getImage();
        image2.setImage(cdImage.getScaledInstance(700, 800, Image.SCALE_SMOOTH));
    	*/
    }
    
    public EntryComponent(EntryComponent E) {
    	this.image = E.image;
    	this.image2 = E.image2;
    	this.name = E.name;
    	this.victoryNum = E.victoryNum;
    	cdImage = image.getImage();
    }

	@Override
	public int compareTo(EntryComponent arg) {
		return victoryNum - arg.victoryNum;

	}
}
