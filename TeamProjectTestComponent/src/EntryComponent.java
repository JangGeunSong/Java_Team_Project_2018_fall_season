import javax.swing.ImageIcon;

public class EntryComponent implements Comparable<EntryComponent>{
    public ImageIcon image, image2;
    public String name;
    public int victoryNum;

    public EntryComponent(String imageDir, String imageDir2, String name, int victoryNum){
        image = new ImageIcon(imageDir);
        image2 = new ImageIcon(imageDir2);
        this.name = name;
        this.victoryNum = victoryNum;
    }

	@Override
	public int compareTo(EntryComponent arg) {
		return victoryNum - arg.victoryNum;

	}
}
