package Assignment1;

public class CDRecord {

	private String cdAlbumTitle;
	private String cdArtist;
	private int numOfTracks;
	private long trackingNum;
	private int numOfCopies;
	private static int totalNumOfCopies;
	private CDRecord data;
	private CDRecord next, previous;

	public CDRecord(){

	}

	public CDRecord(String cdAlbumTitle, String cdArtist, int numOfTracks, long trackingNum, int numOfCopies){
		this.cdAlbumTitle = cdAlbumTitle;
		this.cdArtist = cdArtist;
		this.numOfTracks = numOfTracks;
		this.trackingNum = trackingNum;
		this.numOfCopies = numOfCopies;
	}
	
	public String toString(){
		String s = "The title is: " + cdAlbumTitle + "\n"
					+ "The artist is: " + cdArtist + "\n" 
					+ "Number of tracks is: " + numOfTracks + "\n"
					+ "The tracking number is: " + trackingNum + "\n"
					+ "The number of copies is: " + numOfCopies + "\n";
		return s;
	}
	
	public String getCdAlbumTitle() {
		return cdAlbumTitle;
	}

	public String getCdArtist() {
		return cdArtist;
	}

	public int getNumOfTracks() {
		return numOfTracks;
	}

	public long getTrackingNum() {
		return trackingNum;
	}

	public int getNumOfCopies() {
		return numOfCopies;
	}
	public int getTotalNumOfCopies(){
		return totalNumOfCopies;
	}

	public void setCdAlbumTitle(String cdAlbumTitle) {
		this.cdAlbumTitle = cdAlbumTitle;
	}

	public void setCdArtist(String cdArtist) {
		this.cdArtist = cdArtist;
	}

	public void setNumOfTracks(int numOfTracks) {
		this.numOfTracks = numOfTracks;
	}

	public void setTrackingNum(long trackingNum) {
		this.trackingNum = trackingNum;
	}

	public void setNumOfCopies(int numOfCopies) {
		this.numOfCopies = numOfCopies;
	}

	public void setData(CDRecord data) {
		this.data = data;
	}

	public void setNext(CDRecord c){
		next = c;
	}
	
	public void setTotalNumberOfCopies(int n){
		totalNumOfCopies = n;
	}
	
	public CDRecord getNext(){
		return next;
	}
	
	public CDRecord getData(){
		return data;
	}
	
	public void setPrevious(CDRecord c){
		this.previous = c;
	}
	
	public CDRecord getPrevious(){
		return previous;
	}
}
