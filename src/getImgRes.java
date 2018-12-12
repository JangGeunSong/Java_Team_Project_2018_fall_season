import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

public class getImgRes {
	
	private String fileAddr;
	private FileReader fileReader =null;
	private BufferedReader bufReader =null;
	
	private EntryComponent allImgList[];
	private EntryComponent entryComp[];
	private int numOfImg=0;
	private int nRound=0; 
	
	
	
	public getImgRes(String fileAddr,int nRound){
		
		this.fileAddr=fileAddr;
		this.nRound=nRound;
		
		try {
			initFileReader();
		}catch(FileNotFoundException e) {}
		
		try {
			initImgList();
		} catch (IOException e) {}
		
		
		
		
		
		try {
			writeEditedValues();
		} catch (IOException e) {}

		initEntryComp();
		
		printImgList();
		sortAllImgList();
		printImgList();
	}
	
	public EntryComponent[] getRandomImgList() {
		
		return entryComp;
	}
	
	
	public EntryComponent[] getAllImgList() {
		return allImgList;
		
	}
	
	public void writeEditedValues() throws IOException {
		
		String editedLines = null;
		String line;
		FileWriter fw=new FileWriter(fileAddr);
		fw.write(Integer.toString(numOfImg));
		fw.write("\r\n");
		for(int i=0;i<numOfImg;i++) {
			fw.write(allImgList[i].image.getDescription()+" "
						+allImgList[i].name+" "
						+allImgList[i].victoryNum
						);
			fw.write("\r\n");
		}
		
		fw.close();
	}
	
	
	
	private void initFileReader() throws FileNotFoundException{
		
		fileReader = new FileReader(fileAddr);
		bufReader = new BufferedReader(fileReader);
	
	}
	
	private void initImgList() throws IOException {
		String line;
		String lineComponent[];
		
		numOfImg=Integer.parseInt(bufReader.readLine());
		allImgList = new EntryComponent[numOfImg];
		
		for(int i=0;i<numOfImg;i++) {
			line=bufReader.readLine();
			lineComponent=line.split(" ");
			allImgList[i]= new EntryComponent(lineComponent[0], lineComponent[0], lineComponent[1]
					, Integer.parseInt(lineComponent[2]));
		}
	}
	
	private void printImgList() {
		for(int i=0;i<numOfImg;i++) {
			System.out.println(allImgList[i].name+":"+allImgList[i].victoryNum);
		}
		
		
		for(int i=nRound/2;i<nRound;i++) {
			System.out.println(entryComp[i].name);
		}
	}
	
	private int[] getRandArr() {
		
		Random rand = new Random();
		
		int last=numOfImg;
		int randNum;
		
		int[] randIndex = new int[nRound];
		int[] randChckArr=new int[last];
		for(int i=0;i<last;i++) randChckArr[i]=i;
		for(int i=0;i<nRound;i++) {
			randNum=rand.nextInt(last);
			randIndex[i]=randChckArr[randNum];
			randChckArr[randNum]=randChckArr[--last];
		}



		return randIndex;
	}
	
	private void initEntryComp() {
		entryComp = new EntryComponent[nRound];
		
		
		int randIndex[] = getRandArr();
		for(int i=0;i<nRound;i++) {
			entryComp[i]=allImgList[randIndex[i]];
		}
		

	}
	
	public void sortAllImgList() {
		Arrays.sort(allImgList);
	}
	
	public void sendInformation(EntryComponent E) {
		for(int i = 0; i < numOfImg; i++) {
			if(E == allImgList[i]) {
				allImgList[i] = E;
			}
		}
	}
	
	public int getNumofImg() {
		return this.numOfImg;
	}

}
