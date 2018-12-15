import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;
import java.sql.ResultSet;
import java.sql.SQLException;



public class FILEDB{
	
	String driver = "org.sqlite.JDBC";
	String url = "jdbc:mysql:MySQLiteDB";
	String user = "admin";
	String password = "admin";// DB 연결 기본 정보
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs; //DB 연결용 객체 선언
	
	
	private EntryComponent allImgList[]; // 모든 사진 불러서 저장하는 객체
	private EntryComponent entryComp[]; // 실제 대진에서 사용할 객체 저장
	private int numOfImg=0; // 총 이미지의 개수
	private int nRound=0;  // 몇강인지(8강, 16강) 정보 저장
	private String gender;// DB로부터 이미지 정보 얻어서 
	
	
	FILEDB(String gender,int nRound)  throws SQLException{
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// DB 연결
		
		System.out.println("DB Connecting......");
		conn = DriverManager.getConnection("jdbc:sqlite:MySQLiteDB"); //DB 연결
		stmt = conn.createStatement(); // query문 실행할 객체
		this.nRound = nRound;
		
		System.out.println("Image Loading......");
		this.gender=gender;
		initImgList(gender); // DB 에 있는 모든 사진정보 allImgList 배열에 저장
		
		System.out.println("Init Entry......");
		initEntryComp(); // DB의 사진으로부터 실제 대진에 사용할 객체 저장
	}
	
	public EntryComponent[] getRandomImgList() {
		return entryComp; 
	}// 실제 대진에 사용할 객체 반환
	
	
	public EntryComponent[] getAllImgList() {
		return allImgList;
		
	}// 모든 이미지 리스트 반환(순위 출력할때 사용)
	
	public void closeDB() throws SQLException{
		rs.close();
		stmt.close();
		conn.close();
	} // DB 닫기
	
	public void sortAllImgList() {
		Arrays.sort(allImgList);
	} // 배열에 저장된 사진 리스트 정렬하기
	
	private int getNumOfImg(String gender) throws SQLException {
		rs = stmt.executeQuery("select num from NUMOFPEOPLE where fieldname=\'"+gender+"\'");
		rs.next();
		return rs.getInt(1);
	} // DB로부터 전체 이미지가 몇개 있는지 반환해주는 함수
	
	public void writeEditedValues(EntryComponent winner) throws SQLException{
		System.out.println(gender);
		System.out.println("update "+gender+" set victoryNum="+(winner.victoryNum)+" where addr=\'"+winner.image.getDescription()+"\'");
		stmt.executeUpdate("update "+gender+" set victoryNum="+(winner.victoryNum)+" where addr=\'"+winner.image.getDescription()+"\'");
	} // 월드컵이 끝난 이후에 우승한 객체를 받아 해당 객체의 승리 횟수를 DB의 저장
	
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
	}// O(n)시간에 Random한 이미지들의 "중복되지 않는" index를 얻기 위해 만든 함수
	
	
	private void initImgList(String gender) throws SQLException {
		
		int index=0;
		
		numOfImg=getNumOfImg(gender);
		allImgList = new EntryComponent[numOfImg];
		
		rs = stmt.executeQuery("select * from "+gender);
		
		
		
		while ( rs.next() ) {
			allImgList[index++] = new EntryComponent(
					rs.getString("addr"),
					rs.getString("addr"), 
					rs.getString("name"), 
					rs.getInt("victoryNum"));
			
		}
	} // DB로부터 이미지 정보를 가져오는 객체. 
	// 성별에 따라서 테이블에서 가져오는 이미지의 정보가 다르다.
	
	private void initEntryComp() {
		entryComp = new EntryComponent[nRound];
		
		
		int randIndex[] = getRandArr();
		for(int i=0;i<nRound;i++) {
			entryComp[i]=allImgList[randIndex[i]];
		}
	}// 전체 사진으로부터 랜덤한 이미지를 뽑아오는 함수
	
	
	

	public int getNumofImg() {
		return numOfImg;
	} // DB에 저장된 사진의 개수 반환하는 함수
	
	
}
