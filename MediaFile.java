/**
 * This class represents a media file with the properties for title, artist, duration in seconds, and file type.
 * 
 */
public class MediaFile {
	// Instance variables of the class.
	private String title;
	private String artist;
	private int durationSeconds;
	private String fileType;
	
	/**
	 * This is the constructor of the class and it initializes the instance variables.
	 * 
	 * @param title
	 * @param artist
	 * @param durationSeconds
	 * @param fileType
	 */
	public MediaFile(String title, String artist, int durationSeconds, String fileType) {
		this.title = title;
		this.artist = artist;
		this.durationSeconds = durationSeconds;
		this.fileType = fileType;
	}
	
	// Returns the title of the media file.
	public String getTitle() {
		return title;
	}
	
	// Returns the artist of the media file.
	public String getArtist() {
		return artist;
	}
	
	// Returns the duration of the media file in seconds.
	public int getDurationSeconds() {
		return durationSeconds;
	}
	
	// Returns the file type of the media file.
	public String getFileType() {
		return fileType;
	}
	
	// Returns a formatted string following a specific format.
	public String toString() {
		int minutes = durationSeconds / 60;
		int seconds = durationSeconds % 60;
		String secondsSpecified = String.format("%02d", seconds);
		return title + " - " + artist + " (" + minutes + ":" + secondsSpecified + ")";
	}
}